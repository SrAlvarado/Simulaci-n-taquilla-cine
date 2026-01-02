package org.cuatrovientos.dam.psp.sincronizacionTaquillasCine;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Taquilla extends Thread {
    private int idTaquilla;
    private Cine cine;
    private List<Queue<Integer>> colas;
    private boolean abierta;
    private Random random;

    public Taquilla(int id, Cine cine, List<Queue<Integer>> colas) {
        this.idTaquilla = id;
        this.cine = cine;
        this.colas = colas;
        this.abierta = true;
        this.random = new Random();
    }

    @Override
    public void run() {
        System.out.println("Taquilla " + idTaquilla + " abierta.");
        
        while (abierta && cine.getAsientosDisponibles() > 0) {
            Integer cliente = buscarCliente();

            if (cliente != null) {
                procesarVenta(cliente);
            } else {
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        System.out.println("Taquilla " + idTaquilla + " cerrando.");
    }

    private Integer buscarCliente() {
        for (Queue<Integer> cola : colas) {
            synchronized (cola) {
                if (!cola.isEmpty()) {
                    return cola.poll();
                }
            }
        }
        return null;
    }

    private void procesarVenta(int idCliente) {
        try {
            int tiempoProceso = random.nextInt(
                Configuracion.TIEMPO_VENTA_MAX_MS - Configuracion.TIEMPO_VENTA_MIN_MS + 1
            ) + Configuracion.TIEMPO_VENTA_MIN_MS;
            
            Thread.sleep(tiempoProceso);
            
            boolean exito = cine.venderEntrada();
            if (exito) {
                System.out.println("Taquilla " + idTaquilla + " vendió entrada al cliente " + idCliente + " en " + tiempoProceso + "ms");
            } else {
                System.out.println("Taquilla " + idTaquilla + ": No pudo vender a cliente " + idCliente + " (Cine lleno).");
                abierta = false;
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void cerrarTaquilla() {
        this.abierta = false;
    }
}