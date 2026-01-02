package org.cuatrovientos.dam.psp.sincronizacionTaquillasCine;

public class Cine {
    private String nombre;
    private int asientosDisponibles;
    private int entradasVendidas;

    public Cine(String nombre, int asientosTotales) {
        this.nombre = nombre;
        this.asientosDisponibles = asientosTotales;
        this.entradasVendidas = 0;
    }

    public synchronized boolean venderEntrada() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--;
            entradasVendidas++;
            System.out.println("Entrada vendida. Quedan " + asientosDisponibles + " asientos.");
            return true;
        }
        return false;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
}