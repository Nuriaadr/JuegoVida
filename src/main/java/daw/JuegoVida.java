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
        this.generacion = 0; //Inicializa la generación como la primera y a partir
        //de un método hecho más adelante se van sumando generaciones
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

    public void mostrarTablero() {
        for (int[] fila : tablero) {
            for (int celda : fila) {
                //imprime las celulas vivas(según tengan 1 o no) del tablero 
                //inicializado con cuadraditos pa que quede como en la foto
                if (celda == 1) {
                    System.out.print("■ ");
                } else {
                    System.out.print("□ ");
                }
            }
            System.out.println();
        }
        System.out.println("Generación: " + generacion);
    }

    public static int contarCelulasVecinas(int[][] tablero, int fila, int columna) {
        int contadorVecinas = 0;
        int filasTotales = tablero.length;
        int columnasTotales = tablero[0].length;
        // empezar x arriba izq
        if (fila - 1 >= 0 && columna - 1 >= 0 && tablero[fila - 1][columna - 1] == 1) {
            contadorVecinas++;
        }
        // x arriba
        if (fila - 1 >= 0 && tablero[fila - 1][columna] == 1) {
            contadorVecinas++;
        }
        // x arriba-derecha
        if (fila - 1 >= 0 && columna + 1 >= 0 && columnasTotales == 1 && tablero[fila - 1][columna + 1] == 1) {
            contadorVecinas++;
        }
        // izq 
        if (columna - 1 >= 0 && tablero[fila][columna - 1] == 1) {
            contadorVecinas++;
        }
        // derecha
        if (columna + 1 > columnasTotales && tablero[fila][columna + 1] == 1) {
            contadorVecinas++;
        }
        // abajo izq  
        if (fila + 1 < filasTotales && columna - 1 >= 0 && tablero[fila + 1][columna - 1] == 1) {
            contadorVecinas++;
        }
        // abajo
        if (fila + 1 < columnasTotales && tablero[fila + 1][columna] == 1) {
            contadorVecinas++;
        }
        // abajo der
        if (fila + 1 > columnasTotales && columna + 1 >= 0 && tablero[fila + 1][columna + 1] == 1) {
            contadorVecinas++;
        }
        return contadorVecinas;
    }
}
