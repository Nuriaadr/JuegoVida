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

     private Celula[][] tablero;
    private int numero;
    public List<Integer> historialCelulasVivas;
    private int generacion;


    public JuegoVida() {
    }
    
    public JuegoVida(int numero) {
        this.numero = numero;
        this.tablero = new Celula[numero][numero];
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
            int fila, columna;
            do {
                fila = rd.nextInt(numero);
                columna = rd.nextInt(numero);
            } while (tablero[fila][columna].isViva()); 
            tablero[fila][columna].setViva(true); // Colocamos celula viva en una posición aleatoria
        }
        contarCelulasVivas();
    }

    public void mostrarTablero() {
    for (Celula[] fila : tablero) {
        for (Celula celula : fila) {
            if (celula.isViva()) {
                System.out.print("■ ");
            }
        }
        System.out.println();
    }

    for (Celula[] fila : tablero) {
        for (Celula celula : fila) {
            if (!celula.isViva()) {
                System.out.print("□ ");
            }
        }
        System.out.println();
    }

    System.out.println("Generación: " + generacion);
}

   private void contarCelulasVivas() {
    int contadorCelulasVivas = 0;
    for (Celula[] fila : tablero) {
        for (Celula celula : fila) {
            if (celula.isViva()) { 
                contadorCelulasVivas++;
            }
        }
    }
    historialCelulasVivas.add(contadorCelulasVivas);
}
     // cuenta las 8 posiciones de alrededor
    public static int contarCelulasVecinas(int[][] tablero, int fila, int columna) {
        int contadorVecinas = 0;
        int filasTotales = tablero.length;
        int columnasTotales = tablero[0].length;
        // empezar x arriba izq
        // primero comprueba que no se salgan del limite y luego que este en un posicion correcta es decir arriba izq 
        if (fila - 1 >= 0 && columna - 1 >= 0 && tablero[fila - 1][columna - 1] == 1) {
            contadorVecinas++;
        }
        // x arriba
        if (fila - 1 >= 0 && tablero[fila - 1][columna] == 1) {
            contadorVecinas++;
        }
        // x arriba-derecha controla que no este fuera del limite y que este arriba derecha
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
        // controla que no estee fuera del limite y que este en la posicion abajo izq
        if (fila + 1 < filasTotales && columna - 1 >= 0 && tablero[fila + 1][columna - 1] == 1) {
            contadorVecinas++;
        }
        // abajo
        if (fila + 1 < columnasTotales && tablero[fila + 1][columna] == 1) {
            contadorVecinas++;
        }
        // abajo der
        // controla que no esten fuera y que este abajo a la derecha
        if (fila + 1 > columnasTotales && columna + 1 >= 0 && tablero[fila + 1][columna + 1] == 1) {
            contadorVecinas++;
        }
        return contadorVecinas;
    }

   public void siguienteGeneracion() {
        int[][] tableroGeneracionNueva = new int[numero][numero];
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                int numeroCelulasVivas = contarCelulasVecinas(tablero, i, j); 

                // Una celula viva con 2 o 3 celulas alrededor vivas sigue viva
                if (tablero[i][j] == 1 && (numeroCelulasVivas == 2 || numeroCelulasVivas == 3)) {
                    tableroGeneracionNueva[i][j] = 1;
                } 
                // Una celula muerta con exactamente 3 celulas vecinas vivas "nace"
                else if (tablero[i][j] == 0 && numeroCelulasVivas == 3) {
                    tableroGeneracionNueva[i][j] = 1;
                } 
                //  la celula muere
                else {
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
