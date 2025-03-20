/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author nuria
 */
public class JuegoVida {

    private int[][] tablero;
    private int numero;
    public List<Integer> historialCelulasVivas;
    private int generacion;

    public JuegoVida(int numero) {
        this.numero = numero;
        this.tablero = new int[numero][numero];
        this.generacion = 0; //Inicializa la generación como la primera y a partir
        //de un método hecho más adelante se van sumando generaciones
        this.historialCelulasVivas = new ArrayList<>();
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
        contarCelulasVivas();
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

    private void contarCelulasVivas() {
        int contadorCelulasVivas = 0;
        for (int[] fila : tablero) {
            for (int celula : fila) {
                if (celula == 1) {
                    contadorCelulasVivas++;
                }
            }
        }
        historialCelulasVivas.add(contadorCelulasVivas);
    }

    public static int contarCelulasVecinas(int fila, int columna) {
        int contadorVecinasVivas = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {

            }
        }
        return contadorVecinasVivas;

    }

    public void siguienteGeneracion() {
        int[][] tableroGeneracionNueva = new int[numero][numero];
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                int numeroCelulasVivas = contarCelulasVecinas(i, j);
                //Una célula viva con 2 ó 3 células vecinas vivas sigue vivaé
                if (tablero[i][j] == 1 && (numeroCelulasVivas == 2 || numeroCelulasVivas == 3)) {
                    tableroGeneracionNueva[i][j] = 1;
                    //Una célula muerta con exactamente 3 células vecinas vivas "nace" (
                } else if (tablero[i][j] == 0 && numeroCelulasVivas == 3) {
                    tableroGeneracionNueva[i][j] = 1;
                    //en cualquiera de los otros dos casos la célula muere
                } else {
                    tableroGeneracionNueva[i][j] = 0;
                }
            }
        }
        //reemplazamos el tablero actual con el nuevo de la generacion actual
        tablero = tableroGeneracionNueva;

        //contamos una gen nueva
        generacion++;

        //contamos las células vivas en esta nueva generación y lo guardamos en la lista
        contarCelulasVivas();

    }
}
