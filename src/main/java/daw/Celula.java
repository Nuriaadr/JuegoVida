/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

/**
 *
 * @author rocio
 */
public class Celula {
    private int fila;
    private int columna;
    private boolean viva;

    public Celula(int fila, int columna, boolean viva) {
        this.fila = fila;
        this.columna = columna;
        this.viva = viva;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }
}

