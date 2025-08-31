// Nicolas Isaza Sierra (7004625)");
// Julián David Galindo Hernández (7004600)");
// Saúl Alejandro Pérez Estupiñán (7004631)");

package org.laboratorioordenamiento;

import java.util.Scanner;

public class OperacionesMatriciales {
    
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * Ejecuta el submenú de operaciones matriciales
     */
    public void ejecutarSubMenu() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\\n=== OPERACIONES MATRICIALES ===");
            System.out.println("1. Suma de Matrices");
            System.out.println("2. Producto de Matrices");
            System.out.println("3. Inversa de una Matriz");
            System.out.println("4. Producto de Matriz por Vector");
            System.out.println("0. Volver al menú principal");
            System.out.print("\\nSeleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine().trim());
                
                switch (opcion) {
                    case 1:
                        realizarSumaMatrices();
                        break;
                    case 2:
                        realizarProductoMatrices();
                        break;
                    case 3:
                        realizarInversaMatriz();
                        break;
                    case 4:
                        realizarProductoMatrizVector();
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("\\n❌ Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\\n❌ Por favor, ingrese un número válido.");
            }
        }
    }
    
    /**
     * Realiza la suma de dos matrices A + B = C
     */
    private void realizarSumaMatrices() {
        System.out.println("\\n--- SUMA DE MATRICES ---");
        
        try {
            // Solicitar dimensiones
            System.out.print("Ingrese el número de filas: ");
            int filas = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Ingrese el número de columnas: ");
            int columnas = Integer.parseInt(scanner.nextLine().trim());
            
            if (filas <= 0 || columnas <= 0) {
                System.out.println("❌ Las dimensiones deben ser positivas.");
                return;
            }
            
            // Leer matrices
            double[][] matrizA = leerMatriz("A", filas, columnas);
            double[][] matrizB = leerMatriz("B", filas, columnas);
            
            // Calcular suma
            double[][] resultado = sumarMatrices(matrizA, matrizB);
            
            // Mostrar resultados
            System.out.println("\\n📊 RESULTADO DE LA SUMA:");
            System.out.println("Matriz A:");
            mostrarMatriz(matrizA);
            System.out.println("\\nMatriz B:");
            mostrarMatriz(matrizB);
            System.out.println("\\nMatriz A + B:");
            mostrarMatriz(resultado);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese números válidos.");
        } catch (Exception e) {
            System.out.println("❌ Error en la operación: " + e.getMessage());
        }
    }
    
    /**
     * Realiza el producto de dos matrices A × B = C
     */
    private void realizarProductoMatrices() {
        System.out.println("\\n--- PRODUCTO DE MATRICES ---");
        
        try {
            // Solicitar dimensiones de la primera matriz
            System.out.print("Matriz A - Ingrese número de filas: ");
            int filasA = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Matriz A - Ingrese número de columnas: ");
            int columnasA = Integer.parseInt(scanner.nextLine().trim());
            
            // Para el producto matricial, columnas de A = filas de B
            System.out.print("Matriz B - Ingrese número de columnas: ");
            int columnasB = Integer.parseInt(scanner.nextLine().trim());
            
            int filasB = columnasA; // Condición necesaria para el producto
            
            System.out.printf("\\n📝 Dimensiones: A(%dx%d) × B(%dx%d) = C(%dx%d)\\n", 
                            filasA, columnasA, filasB, columnasB, filasA, columnasB);
            
            // Leer matrices
            double[][] matrizA = leerMatriz("A", filasA, columnasA);
            double[][] matrizB = leerMatriz("B", filasB, columnasB);
            
            // Calcular producto
            double[][] resultado = multiplicarMatrices(matrizA, matrizB);
            
            // Mostrar resultados
            System.out.println("\\n📊 RESULTADO DEL PRODUCTO:");
            System.out.println("Matriz A:");
            mostrarMatriz(matrizA);
            System.out.println("\\nMatriz B:");
            mostrarMatriz(matrizB);
            System.out.println("\\nMatriz A × B:");
            mostrarMatriz(resultado);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese números válidos.");
        } catch (Exception e) {
            System.out.println("❌ Error en la operación: " + e.getMessage());
        }
    }
    
    /**
     * Calcula la inversa de una matriz usando eliminación de Gauss-Jordan
     */
    private void realizarInversaMatriz() {
        System.out.println("\\n--- INVERSA DE UNA MATRIZ ---");
        
        try {
            System.out.print("Ingrese el tamaño de la matriz cuadrada: ");
            int n = Integer.parseInt(scanner.nextLine().trim());
            
            if (n <= 0) {
                System.out.println("❌ El tamaño debe ser positivo.");
                return;
            }
            
            double[][] matriz = leerMatriz("", n, n);
            double[][] inversa = calcularInversa(matriz);
            
            if (inversa != null) {
                System.out.println("\\n📊 RESULTADO:");
                System.out.println("Matriz original:");
                mostrarMatriz(matriz);
                System.out.println("\\nMatriz inversa:");
                mostrarMatriz(inversa);
                
                // Verificación: A × A⁻¹ = I
                System.out.println("\\n✅ Verificación (A × A⁻¹):");
                double[][] verificacion = multiplicarMatrices(matriz, inversa);
                mostrarMatriz(verificacion);
            } else {
                System.out.println("❌ La matriz no es invertible (determinante = 0).");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese números válidos.");
        } catch (Exception e) {
            System.out.println("❌ Error en la operación: " + e.getMessage());
        }
    }
    
    /**
     * Realiza el producto de una matriz por un vector
     */
    private void realizarProductoMatrizVector() {
        System.out.println("\\n--- PRODUCTO MATRIZ × VECTOR ---");
        
        try {
            System.out.print("Ingrese número de filas de la matriz: ");
            int filas = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Ingrese número de columnas de la matriz: ");
            int columnas = Integer.parseInt(scanner.nextLine().trim());
            
            // Leer matriz
            double[][] matriz = leerMatriz("", filas, columnas);
            
            // Leer vector (debe tener tantas componentes como columnas de la matriz)
            double[] vector = leerVector(columnas);
            
            // Calcular producto
            double[] resultado = multiplicarMatrizVector(matriz, vector);
            
            // Mostrar resultados
            System.out.println("\\n📊 RESULTADO:");
            System.out.println("Matriz:");
            mostrarMatriz(matriz);
            System.out.println("\\nVector:");
            mostrarVector(vector);
            System.out.println("\\nResultado (Matriz × Vector):");
            mostrarVector(resultado);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Ingrese números válidos.");
        } catch (Exception e) {
            System.out.println("❌ Error en la operación: " + e.getMessage());
        }
    }
    
    /**
     * Lee una matriz desde la entrada del usuario
     * @param nombre nombre de la matriz para mostrar
     * @param filas número de filas
     * @param columnas número de columnas
     * @return matriz leída
     */
    private double[][] leerMatriz(String nombre, int filas, int columnas) {
        double[][] matriz = new double[filas][columnas];
        
        System.out.println("\\nIngrese los elementos de la matriz " + nombre + 
                          " (" + filas + "x" + columnas + "):");
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.printf("Elemento [%d][%d]: ", i + 1, j + 1);
                matriz[i][j] = Double.parseDouble(scanner.nextLine().trim());
            }
        }
        
        return matriz;
    }
    
    /**
     * Lee un vector desde la entrada del usuario
     * @param tamaño tamaño del vector
     * @return vector leído
     */
    private double[] leerVector(int tamaño) {
        double[] vector = new double[tamaño];
        
        System.out.println("\\nIngrese los elementos del vector (" + tamaño + " elementos):");
        for (int i = 0; i < tamaño; i++) {
            System.out.printf("Componente [%d]: ", i + 1);
            vector[i] = Double.parseDouble(scanner.nextLine().trim());
        }
        
        return vector;
    }
    
    /**
     * Suma dos matrices
     * @param a primera matriz
     * @param b segunda matriz
     * @return matriz resultado de la suma
     */
    private double[][] sumarMatrices(double[][] a, double[][] b) {
        int filas = a.length;
        int columnas = a[0].length;
        double[][] resultado = new double[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado[i][j] = a[i][j] + b[i][j];
            }
        }
        
        return resultado;
    }
    
    /**
     * Multiplica dos matrices
     * @param a primera matriz
     * @param b segunda matriz
     * @return matriz resultado del producto
     */
    private double[][] multiplicarMatrices(double[][] a, double[][] b) {
        int filasA = a.length;
        int columnasA = a[0].length;
        int columnasB = b[0].length;
        
        double[][] resultado = new double[filasA][columnasB];
        
        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                for (int k = 0; k < columnasA; k++) {
                    resultado[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        return resultado;
    }
    
    /**
     * Multiplica una matriz por un vector
     * @param matriz matriz
     * @param vector vector
     * @return vector resultado
     */
    private double[] multiplicarMatrizVector(double[][] matriz, double[] vector) {
        int filas = matriz.length;
        double[] resultado = new double[filas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < vector.length; j++) {
                resultado[i] += matriz[i][j] * vector[j];
            }
        }
        
        return resultado;
    }
    
    /**
     * Calcula la inversa de una matriz usando eliminación de Gauss-Jordan
     * @param matriz matriz original
     * @return matriz inversa o null si no es invertible
     */
    private double[][] calcularInversa(double[][] matriz) {
        int n = matriz.length;
        
        // Crear matriz aumentada [A|I]
        double[][] aumentada = new double[n][2 * n];
        
        // Copiar matriz original y crear matriz identidad
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aumentada[i][j] = matriz[i][j];
                aumentada[i][j + n] = (i == j) ? 1.0 : 0.0;
            }
        }
        
        // Eliminación de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            // Encontrar el pivote
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(aumentada[k][i]) > Math.abs(aumentada[maxRow][i])) {
                    maxRow = k;
                }
            }
            
            // Intercambiar filas si es necesario
            if (maxRow != i) {
                double[] temp = aumentada[i];
                aumentada[i] = aumentada[maxRow];
                aumentada[maxRow] = temp;
            }
            
            // Verificar si la matriz es singular
            if (Math.abs(aumentada[i][i]) < 1e-10) {
                return null; // Matriz no invertible
            }
            
            // Hacer el pivote igual a 1
            double pivote = aumentada[i][i];
            for (int j = 0; j < 2 * n; j++) {
                aumentada[i][j] /= pivote;
            }
            
            // Eliminar la columna
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = aumentada[k][i];
                    for (int j = 0; j < 2 * n; j++) {
                        aumentada[k][j] -= factor * aumentada[i][j];
                    }
                }
            }
        }
        
        // Extraer la matriz inversa
        double[][] inversa = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inversa[i][j] = aumentada[i][j + n];
            }
        }
        
        return inversa;
    }
    
    /**
     * Muestra una matriz en formato tabular
     * @param matriz matriz a mostrar
     */
    private void mostrarMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("│ ");
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%8.3f ", matriz[i][j]);
            }
            System.out.println(" │");
        }
    }
    
    /**
     * Muestra un vector
     * @param vector vector a mostrar
     */
    private void mostrarVector(double[] vector) {
        System.out.print("[ ");
        for (int i = 0; i < vector.length; i++) {
            System.out.printf("%.3f ", vector[i]);
        }
        System.out.println("]");
    }
}