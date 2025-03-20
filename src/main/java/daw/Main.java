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
        int[][] tablero = new int[numero][numero];
        JuegoVida juego = new JuegoVida(numero);

         String menu = """
            Menu
            1. Cargar una partida desde un fichero de texto plano
            2. Iniciar un nuevo juego
            3. Mostrar siguiente generacion
            4. Guardar partida
            5. Salir
            Elige una opcion: 
            """;

        String opcion;
        do {
            System.out.print(menu);
            opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> System.out.println("Cargando partida desde archivo...");

                case "2" -> {
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
                                juego.inicializarAleatoriamente(porcentaje);
                                System.out.println("Tablero inicializado");
                                repetir = false;
                            }
                        }
                    } while (repetir);
              }

                case "3" -> {
                    System.out.println("Mostrando siguiente generacion...");
                    juego.mostrarTablero();
              }

                case "4" -> System.out.println("Guardando partida...");

                case "5" -> System.out.println("Saliendo...");
            }
        } while (!opcion.equals("5"));
    }
}