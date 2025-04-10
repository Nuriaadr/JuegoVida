/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nuria
 */
public class JuegoVida {

    private Celula[][] tablero;
    private int numero;
    public List<Celula[][]> historialTableros = new ArrayList<>();
    private List<Integer> historialCelulasVivas = new ArrayList<>();

    private int generacion;

    public JuegoVida() {
    }

    public JuegoVida(int numero) {
        this.numero = numero;
        this.tablero = new Celula[numero][numero];
        //inicializar todas las celdas del tablero con celulas
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                tablero[i][j] = new Celula(i, j, false);
            }
        }
        this.generacion = 0; //Inicializa la generación como la primera y a partir
        //de un método hecho más adelante se van sumando generaciones
        this.historialTableros = new ArrayList<>();
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
            do {
                fila = rd.nextInt(numero);
                columna = rd.nextInt(numero);
            } while (tablero[fila][columna].isViva());
            tablero[fila][columna].setViva(true); //colocamos celula viva en una posición aleatoria
        }
        guardarTablero();
        contarCelulasVivas();

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

    private void guardarTablero() {
        Celula[][] copia = new Celula[numero][numero];
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                copia[i][j] = new Celula(i, j, tablero[i][j].isViva());
            }
        }
        historialTableros.add(copia);
    }

    public void mostrarTablero() {
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                if (tablero[i][j].isViva()) {
                    System.out.print("■ ");
                } else {
                    System.out.print("□ ");
                }
            }
            System.out.println();

        }
        System.out.println("Generacion: " + generacion);
        System.out.println("Células vivas en la generación: " + historialCelulasVivas.toString());

    }

    private int contarCelulasVecinas(Celula[][] tablero, int fila, int columna) {
        int contadorVecinas = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                //Verificar si la posición está dentro del tablero y no es la celda actual
                if (i >= 0 && i < numero && j >= 0 && j < numero && !(i == fila && j == columna)) {
                    if (tablero[i][j] != null && tablero[i][j].isViva()) {
                        contadorVecinas++;
                    }
                }
            }
        }

        return contadorVecinas;
    }

    public void siguienteGeneracion() {
        Celula[][] tableroGeneracionNueva = new Celula[numero][numero];

        //inicializar el nuevo tablero con células muertas
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                tableroGeneracionNueva[i][j] = new Celula(i, j, false);
            }
        }

        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                int numeroCelulasVivas = contarCelulasVecinas(tablero, i, j);

                //Una célula viva con 2 o 3 vecinas vivas sobrevive
                if (tablero[i][j].isViva() && (numeroCelulasVivas == 2 || numeroCelulasVivas == 3)) {
                    tableroGeneracionNueva[i][j].setViva(true);
                }

                //Una célula muerta con exactamente 3 vecinas vivas nace
                if (!tablero[i][j].isViva() && numeroCelulasVivas == 3) {
                    tableroGeneracionNueva[i][j].setViva(true);
                }
            }
        }

        //actualizamos el tablero a la nueva generación
        tablero = tableroGeneracionNueva;
        generacion++; //suma una generacion
        contarCelulasVivas();
        guardarTablero(); //guarda en el historial de tableros el tablero actual
    }

    //lo mismo que habia antes en el main pero hecho método y con un comparador de tableros 
    public boolean MatrizGenIgual() {
        //verifica que el num de generaciones sea mayor a 3
        if (historialTableros.size() < 3) {
            return false;
        } else {
            //compara las 3 ultimas matrices de las generaciones guardadas en el historial
            Celula[][] ultima = historialTableros.get(historialTableros.size() - 1);
            Celula[][] penultima = historialTableros.get(historialTableros.size() - 2);
            Celula[][] antepenultima = historialTableros.get(historialTableros.size() - 3);
            return sonTablerosIguales(ultima, penultima) && sonTablerosIguales(penultima, antepenultima);//evuelve si las matrices son iguales o no
        }

    }

    private boolean sonTablerosIguales(Celula[][] matriz1, Celula[][] matriz2) {
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                //compara que la celula en la posicion ij de ambas matrices este viva/muerta, si no, las matrices no son iguales
                //y si si po si son iguales 
                if (matriz1[i][j].isViva() != matriz2[i][j].isViva()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void guardarPartida(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            //se escriben/guardan los datos del tablero
            writer.write(numero + "\n");
            writer.write(generacion + "\n");
            for (int i = 0; i < numero; i++) {
                for (int j = 0; j < numero; j++) {
                    //se asignan los valores que se van a escribir dependiendo de
                    //si están vivas o muertas
                    String valor = "□ ";
                    if (tablero[i][j].isViva()) {
                        valor = "■ ";
                    }
                    writer.write(valor);//se escribe el valor de la celula
                }
                writer.newLine(); //hace que cada fila de la matriz esté en una linea distinta
            }
            //se guarda el num de celulas vivas en el tablero que se está jugando
            //para que cuando se cargue el tablero se carguen tambien las células
            //que habían vivas directamenteé
            writer.write(historialCelulasVivas.size() + "\n");
            for (int celulas : historialCelulasVivas) {
                writer.write(celulas + "\n");
            }
            System.out.println("Partida guardada en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la partida");
        }
    }

    public static JuegoVida cargarPartida(String nombreArchivo) {
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            //carga los datos del tablero
            int numero = scanner.nextInt();
            int generacion = scanner.nextInt();
            JuegoVida juego = new JuegoVida(numero);
            juego.generacion = generacion;
            scanner.nextLine();
            //leer el tablero con los cuadraditos
            for (int i = 0; i < numero; i++) {
                String linea = scanner.nextLine(); //lee una línea del archivo
                String[] celdas = linea.split(" "); //divide la línea en partes
                for (int j = 0; j < numero; j++) {
                    if (celdas[j].equals("■")) {
                        juego.tablero[i][j].setViva(true); //esta viva
                    } else if (celdas[j].equals("□")) {
                        juego.tablero[i][j].setViva(false); //esta muerta
                    }
                }
            }
            //carga el historial de celulas vivas
            int historialSize = scanner.nextInt();
            for (int i = 0; i < historialSize; i++) {
                juego.historialCelulasVivas.add(scanner.nextInt());
            }
            System.out.println("Partida cargada desde " + nombreArchivo);
            return juego;
        } catch (IOException e) {
            System.out.println("Error al cargar la partida");
            return null;
        }
    }

}
