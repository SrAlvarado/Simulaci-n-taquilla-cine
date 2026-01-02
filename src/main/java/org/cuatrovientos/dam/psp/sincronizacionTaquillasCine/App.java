package org.cuatrovientos.dam.psp.sincronizacionTaquillasCine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        System.out.println("--- Inicio Simulación Cine V1 ---");

        Cine cine = new Cine(Configuracion.NOMBRE_CINE, Configuracion.NUMERO_ASIENTOS);
        Queue<Integer> colaInfinita = new LinkedList<>(); 

        Taquilla[] taquillas = new Taquilla[Configuracion.NUMERO_TAQUILLAS];
        for (int i = 0; i < Configuracion.NUMERO_TAQUILLAS; i++) {
            taquillas[i] = new Taquilla(i + 1, cine, colaInfinita);
            taquillas[i].start();
        }

        long tiempoInicio = System.currentTimeMillis();
        long tiempoFin = tiempoInicio + (Configuracion.TIEMPO_SIMULACION_MINUTOS * 60 * 1000); 
        
        int contadorClientes = 1;
        Random random = new Random();

        try {
            while (System.currentTimeMillis() < tiempoFin && cine.getAsientosDisponibles() > 0) {
                synchronized (colaInfinita) {
                    colaInfinita.add(contadorClientes);
                    System.out.println("Nuevo cliente " + contadorClientes + " entró a la cola. (Cola: " + colaInfinita.size() + ")");
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

        System.out.println("--- Tiempo finalizado o Cine lleno. Cerrando taquillas... ---");
        for (Taquilla t : taquillas) {
            t.cerrarTaquilla();
            try {
                t.join(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("Entradas vendidas: " + cine.getEntradasVendidas());
        System.out.println("Gente que se quedó en la cola (sin entrada): " + colaInfinita.size());
        System.out.println("Asientos libres: " + cine.getAsientosDisponibles());
    }
}