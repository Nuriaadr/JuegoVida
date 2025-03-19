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
        boolean repetir;
        int numero = 0;
        int[][] tablero = new int[numero][numero];
        JuegoVida juego = new JuegoVida(numero);
        do {

            System.out.println("Ingrese tamaño del tablero (maximo 25x25):");
            numero = sc.nextInt();
            sc.nextLine();

            if (numero < 1 || numero > 25) {
                System.out.println("Introduzca un número válido");
                repetir = true;
            } else {
                JuegoVida juego = new JuegoVida(numero);
                System.out.println("Ingrese el porcentaje de células vivas:");
                int porcentaje = sc.nextInt();
                if (porcentaje < 1 || porcentaje > 100) {
                    System.out.println("Introduzca un porcentaje válido");
                    repetir = true;
                } else {
                    juego.inicializarAleatoriamente(porcentaje);
                    System.out.println("Tablero inicializado");
                    repetir = false;
                }
            }

        } while (repetir == true);

        String opcion;
        do {
            System.out.println("""
          Menu
    1. Mostrar siguiente generacion
    2. Salir
    Elige una opcion: 
                     """);
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":

                    juego.mostrarTablero();
                    break;
                case "2":
                    System.out.println("Saliendo...");
                    break;
            }
        } while (!opcion.equals("2"));
    }
}


