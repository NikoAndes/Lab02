package org.laboratorioordenamiento;

import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Método principal del programa
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        mostrarBienvenida();

        boolean continuar = true;
        while (continuar) {
            continuar = mostrarMenuPrincipal();
        }

        System.out.println("\\n¡Gracias por usar el programa!");
        System.out.println("Universidad Militar Nueva Granada - Laboratorio de Programación 3");
        scanner.close();
    }

    /**
     * Muestra el mensaje de bienvenida del programa
     */
    private static void mostrarBienvenida() {
        System.out.println("==============================================");
        System.out.println("  ¡Bienvenido a el LAB 2 ");
        System.out.println("  Arreglos y Algoritmos de Ordenamiento");
        System.out.println("  Laboratorio de Programación 3 - 2025");
        System.out.println("  Creado por:");
        System.out.println("    Nicolas Isaza Sierra (7004625)");
        System.out.println("    Julián David Galindo Hernández (7004600)");
        System.out.println("    Saúl Alejandro Pérez Estupiñán (7004631)");
        System.out.println("==============================================\n");
    }

    /**
     * Muestra el menú principal y procesa la selección del usuario
     * @return true si el usuario desea continuar, false si desea salir
     */
    private static boolean mostrarMenuPrincipal() {
        System.out.println("\\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Operaciones con Matrices");
        System.out.println("2. Comparación de Algoritmos de Ordenamiento");
        System.out.println("3. GUI - Ordenamiento de Personas");
        System.out.println("0. Salir");
        System.out.print("\\nSeleccione una opción: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());

            switch (opcion) {
                case 1:
                    OperacionesMatriciales operaciones = new OperacionesMatriciales();
                    operaciones.ejecutarSubMenu();
                    break;
                case 2:
                    ComparadorOrdenamiento comparador = new ComparadorOrdenamiento();
                    comparador.ejecutarComparacion();
                    break;
                case 3:
                    System.out.println("\\nAbriendo interfaz gráfica...");
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new GUIPersonas().setVisible(true);
                        }
                    });
                    break;
                case 0:
                    return false;
                default:
                    System.out.println("\\n❌ Opción inválida. Intente nuevamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\\n❌ Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("\\n❌ Error: " + e.getMessage());
        }

        return true;
    }
}