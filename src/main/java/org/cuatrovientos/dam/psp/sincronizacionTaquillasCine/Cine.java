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

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
}