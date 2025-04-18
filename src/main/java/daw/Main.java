/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.util.Scanner;

/**
 *
 * @author nuria
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean repetir = true;
        int numero = 0;
        int[][] tablero;
        JuegoVida juego = null;
        String archivo;
        String menu = """
            Menu
            1. Cargar una partida desde un fichero de texto 
            2. Iniciar un nuevo juego    
            3. Salir
            Elige una opcion: 
            """;

        int opcion;
        do {
            System.out.print(menu);
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Introduce el nombre del archivo para cargar: ");
                    archivo = sc.nextLine();
                    juego = JuegoVida.cargarPartida(archivo);
                    System.out.println("Cargando partida desde el archivo...");
                    if (juego != null) {
                        System.out.println("Partida cargada exitosamente.");
                        juego.mostrarTablero();
                    } else {
                        System.out.println("No se pudo cargar la partida.");
                    }
                }

                case 2 -> {
                    do {
                        System.out.println("Introduce tamaño del tablero (maximo 25x25)");
                        numero = sc.nextInt();
                        if (numero < 1 || numero > 25) {
                            System.out.println("Introduce el numero correctamente");
                        } else {
                            System.out.println("Introduce el porcentaje ");
                            int porcentaje = sc.nextInt();
                            if (porcentaje < 1 || porcentaje > 100) {
                                System.out.println("Introduce el porcentaje correctamente");
                            } else {
                                juego = new JuegoVida(numero);
                                juego.inicializarAleatoriamente(porcentaje);
                                System.out.println("Tablero inicializado");
                                repetir = false;
                                juego.mostrarTablero();

                                boolean repetir2 = true;
                                do {
                                    String menu2 = """
                                                   ¿Qué desea hacer?
                                                   1-.Mostrar siguiente gen
                                                   2-.Terminar el juego
                                                   3-.Guardar estado del juego
                                                   """;
                                    System.out.println(menu2);
                                    int opcion2 = sc.nextInt();

                                    switch (opcion2) {
                                        case 1 -> {
                                            juego.siguienteGeneracion();
                                            juego.mostrarTablero();
                                            int tamanio = juego.historialTableros.size();

                                            //verifica que el num de generaciones sea mayor a 3
                                            if (tamanio > 3) {
                                                if (juego.MatrizGenIgual() == true) {
                                                    System.out.println("No hay cambios en las últimas 3 generaciones. El juego ha terminado.");
                                                    repetir2 = false;
                                                    break;

                                                }

                                            }
                                        }
                                        case 2 -> {
                                            System.out.println("Terminando el juego...");
                                            repetir2 = false;

                                            break;
                                        }

                                        case 3 -> {
                                            System.out.println("Ingrese el nombre del archivo para guardar la partida: ");
                                            sc.nextLine();
                                            archivo = sc.nextLine();
                                            juego.guardarPartida(archivo);
                                        }

                                        default -> {
                                            System.out.println("Introduzca una opción válida.");
                                            repetir2 = true;
                                        }
                                    }

                                } while (repetir2 == true);
                            }
                        }
                    } while (repetir);
                }

                case 3 -> {
                    System.out.println("Saliendo...");
                    break;
                }
                default -> {
                    System.out.println("Introduzca una opción válida");
                }

            }

        } while (opcion != 3);
    }
}
