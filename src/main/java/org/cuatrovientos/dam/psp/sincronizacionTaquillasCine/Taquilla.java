package org.cuatrovientos.dam.psp.sincronizacionTaquillasCine;

import java.util.Queue;
import java.util.Random;

public class Taquilla extends Thread {
    private int idTaquilla;
    private Cine cine;
    private Queue<Integer> colaClientes;
    private boolean abierta;
    private Random random;

    public Taquilla(int id, Cine cine, Queue<Integer> colaClientes) {
        this.idTaquilla = id;
        this.cine = cine;
        this.colaClientes = colaClientes;
        this.abierta = true;
        this.random = new Random();
    }

    public void cerrarTaquilla() {
        this.abierta = false;
    }
}