/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.util.Random;

/**
 *
 * @author nuria
 */
public class JuegoVida {

    private int[][] tablero;
    private int numero;
    private int generacion;

    public JuegoVida(int numero) {
        this.numero = numero;
        this.tablero = new int[numero][numero];
        this.generacion = 0;
    }

    public void inicializarAleatoriamente(int porcentaje) {
        Random rd = new Random();
        int totalCeldas = numero * numero;
        int celVivas = (totalCeldas * porcentaje) / 100;

        //coloca células hasta que alcanza el numero de vivas que hemos puesto
        for (int i = 0; i < celVivas; i++) {
            int fila;
            int columna;
            //genera las coordenadas aleatorias mirando que no esté ocupada ya
            do {
                fila = rd.nextInt(numero);
                columna = rd.nextInt(numero);
            } while (tablero[fila][columna] == 1);
            //coloca un 1 en la casilla donde hay celulas vivas
            tablero[fila][columna] = 1;
        }
    }

}
