package org.cuatrovientos.dam.psp.sincronizacionTaquillasCine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        System.out.println("--- Inicio Simulación Cine V2 (Múltiples Colas) ---");

        Cine cine = new Cine(Configuracion.NOMBRE_CINE, Configuracion.NUMERO_ASIENTOS);
        
        List<Queue<Integer>> colas = new ArrayList<>();
        for (int i = 0; i < Configuracion.NUMERO_COLAS; i++) {
            colas.add(new LinkedList<>());
        }

        Taquilla[] taquillas = new Taquilla[Configuracion.NUMERO_TAQUILLAS];
        for (int i = 0; i < Configuracion.NUMERO_TAQUILLAS; i++) {
            taquillas[i] = new Taquilla(i + 1, cine, colas);
            taquillas[i].start();
        }

        long tiempoInicio = System.currentTimeMillis();
        long tiempoFin = tiempoInicio + (Configuracion.TIEMPO_SIMULACION_MINUTOS * 60 * 1000); 
        
        int contadorClientes = 1;
        int clientesRechazados = 0;
        Random random = new Random();

        try {
            while (System.currentTimeMillis() < tiempoFin && cine.getAsientosDisponibles() > 0) {
                Queue<Integer> mejorCola = null;
                int minTamano = Integer.MAX_VALUE;

                for (Queue<Integer> cola : colas) {
                    synchronized (cola) {
                        if (cola.size() < minTamano) {
                            minTamano = cola.size();
                            mejorCola = cola;
                        }
                    }
                }

                if (mejorCola != null && minTamano < Configuracion.MAX_PERSONAS_COLA) {
                    synchronized (mejorCola) {
                        mejorCola.add(contadorClientes);
                        System.out.println("Cliente " + contadorClientes + " entró en una cola (Tamaño: " + mejorCola.size() + ")");
                    }
                } else {
                    clientesRechazados++;
                    System.out.println("Cliente " + contadorClientes + " SE FUE (Colas llenas).");
                }
                
                contadorClientes++;

                int tiempoLlegada = random.nextInt(
                    Configuracion.TIEMPO_LLEGADA_MAX_MS - Configuracion.TIEMPO_LLEGADA_MIN_MS + 1
                ) + Configuracion.TIEMPO_LLEGADA_MIN_MS;
                
                Thread.sleep(tiempoLlegada);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--- Cerrando taquillas... ---");
        for (Taquilla t : taquillas) {
            t.cerrarTaquilla();
            try {
                t.join(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalEnColas = 0;
        for(Queue<Integer> q : colas) {
            totalEnColas += q.size();
        }

        System.out.println("\n--- RESULTADOS V2 ---");
        System.out.println("Entradas vendidas: " + cine.getEntradasVendidas());
        System.out.println("Clientes atendidos pero esperando en cola al cierre: " + totalEnColas);
        System.out.println("Clientes rechazados (Colas llenas): " + clientesRechazados);
        System.out.println("Asientos libres: " + cine.getAsientosDisponibles());
    }
}