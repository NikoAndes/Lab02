// Nicolas Isaza Sierra (7004625)");
// Julián David Galindo Hernández (7004600)");
// Saúl Alejandro Pérez Estupiñán (7004631)");

package org.laboratorioordenamiento;

import java.util.Scanner;

public class ComparadorOrdenamiento {
    
    private Scanner scanner = new Scanner(System.in);
    
    // Tamaños de arreglos para la comparación
    private final int[] TAMAÑOS = {100, 500, 1000, 5000, 10000};
    
    // Nombres de los algoritmos
    private final String[] ALGORITMOS = {"Burbuja", "Inserción", "Selección", "MergeSort"};
    
    /**
     * Ejecuta la comparación completa de algoritmos de ordenamiento
     */
    public void ejecutarComparacion() {
        System.out.println("\\n=== COMPARACIÓN DE ALGORITMOS DE ORDENAMIENTO ===");
        System.out.println("\\nEste módulo comparará el rendimiento de 4 algoritmos:");
        System.out.println("• Burbuja (Bubble Sort)");
        System.out.println("• Inserción (Insertion Sort)");
        System.out.println("• Selección (Selection Sort)");
        System.out.println("• MergeSort");
        
        System.out.println("\\nTamaños de prueba: 100, 500, 1000, 5000 y 10000 números");
        System.out.print("\\n¿Desea continuar con la comparación? (s/n): ");
        
        String respuesta = scanner.nextLine().trim().toLowerCase();
        if (!respuesta.equals("s") && !respuesta.equals("si")) {
            System.out.println("Comparación cancelada.");
            return;
        }
        
        System.out.println("\\n🔄 Iniciando comparación...");
        System.out.println("Esto puede tomar unos momentos para los arreglos más grandes.\\n");
        
        // Realizar la comparación y generar tabla
        realizarComparacionCompleta();
    }
    
    /**
     * Realiza la comparación completa y genera la tabla de resultados
     */
    private void realizarComparacionCompleta() {
        // Matriz para almacenar los tiempos (algoritmo, tamaño)
        long[][] tiempos = new long[ALGORITMOS.length][TAMAÑOS.length];
        
        // Ejecutar pruebas para cada tamaño
        for (int t = 0; t < TAMAÑOS.length; t++) {
            int tamaño = TAMAÑOS[t];
            System.out.printf("📊 Probando con %d elementos...\\n", tamaño);
            
            // Generar arreglo aleatorio base
            double[] arregloBase = AlgoritmosOrdenamiento.generarArregloAleatorio(tamaño);
            
            // Probar cada algoritmo
            for (int a = 0; a < ALGORITMOS.length; a++) {
                // Crear copia del arreglo para cada algoritmo
                double[] copia = AlgoritmosOrdenamiento.copiarArreglo(arregloBase);
                
                // Medir tiempo de ejecución
                long tiempo = medirTiempoAlgoritmo(a, copia);
                tiempos[a][t] = tiempo;
                
                // Verificar que el ordenamiento fue correcto
                if (!AlgoritmosOrdenamiento.estaOrdenado(copia)) {
                    System.err.println("❌ Error: " + ALGORITMOS[a] + " no ordenó correctamente!");
                }
                
                System.out.printf("   ✓ %s: %s\\n", ALGORITMOS[a], formatearTiempo(tiempo));
            }
            System.out.println();
        }
        
        // Generar y mostrar tabla comparativa
        mostrarTablaComparativa(tiempos);
        
        // Análisis de resultados
        analizarResultados(tiempos);
    }
    
    /**
     * Mide el tiempo de ejecución de un algoritmo específico
     * 
     * @param algoritmo índice del algoritmo
     * @param arreglo arreglo a ordenar
     * @return tiempo de ejecución en nanosegundos
     */
    private long medirTiempoAlgoritmo(int algoritmo, double[] arreglo) {
        switch (algoritmo) {
            case 0: // Burbuja
                return AlgoritmosOrdenamiento.burbuja(arreglo);
            case 1: // Inserción
                return AlgoritmosOrdenamiento.insercion(arreglo);
            case 2: // Selección
                return AlgoritmosOrdenamiento.seleccion(arreglo);
            case 3: // MergeSort
                return AlgoritmosOrdenamiento.mergeSort(arreglo);
            default:
                return 0;
        }
    }
    
    /**
     * Muestra la tabla comparativa de tiempos
     * 
     * @param tiempos matriz de tiempos [algoritmo][tamaño]
     */
    private void mostrarTablaComparativa(long[][] tiempos) {
        System.out.println("\\n" + "=".repeat(80));
        System.out.println("                      TABLA COMPARATIVA DE TIEMPOS");
        System.out.println("=".repeat(80));
        
        // Encabezados
        System.out.printf("%-15s", "Algoritmo");
        for (int tamaño : TAMAÑOS) {
            System.out.printf("%12s", tamaño + " elem.");
        }
        System.out.println();
        
        System.out.println("-".repeat(80));
        
        // Datos de cada algoritmo
        for (int a = 0; a < ALGORITMOS.length; a++) {
            System.out.printf("%-15s", ALGORITMOS[a]);
            for (int t = 0; t < TAMAÑOS.length; t++) {
                System.out.printf("%12s", formatearTiempoCorto(tiempos[a][t]));
            }
            System.out.println();
        }
        
        System.out.println("=".repeat(80));
        System.out.println("Tiempos en: ms = milisegundos, μs = microsegundos, ns = nanosegundos");
    }
    
    /**
     * Realiza un análisis de los resultados obtenidos
     * 
     * @param tiempos matriz de tiempos
     */
    private void analizarResultados(long[][] tiempos) {
        System.out.println("\\n📈 ANÁLISIS DE RESULTADOS:\\n");
        
        // Encontrar el algoritmo más rápido para cada tamaño
        for (int t = 0; t < TAMAÑOS.length; t++) {
            int masRapido = 0;
            int masLento = 0;
            
            for (int a = 1; a < ALGORITMOS.length; a++) {
                if (tiempos[a][t] < tiempos[masRapido][t]) {
                    masRapido = a;
                }
                if (tiempos[a][t] > tiempos[masLento][t]) {
                    masLento = a;
                }
            }
            
            System.out.printf("🏆 %d elementos: %s fue el más rápido (%s)\\n", 
                            TAMAÑOS[t], ALGORITMOS[masRapido], 
                            formatearTiempo(tiempos[masRapido][t]));
            System.out.printf("🐌 %d elementos: %s fue el más lento (%s)\\n", 
                            TAMAÑOS[t], ALGORITMOS[masLento], 
                            formatearTiempo(tiempos[masLento][t]));
            System.out.println();
        }
        
        // Análisis de complejidad observada
        analizarComplejidad(tiempos);
        
        // Recomendaciones
        mostrarRecomendaciones();
    }
    
    /**
     * Analiza la complejidad observada comparando los tiempos
     * 
     * @param tiempos matriz de tiempos
     */
    private void analizarComplejidad(long[][] tiempos) {
        System.out.println("📊 ANÁLISIS DE COMPLEJIDAD OBSERVADA:\\n");
        
        for (int a = 0; a < ALGORITMOS.length; a++) {
            System.out.println("• " + ALGORITMOS[a] + ":");
            
            // Calcular ratio de crecimiento entre tamaños consecutivos
            for (int t = 1; t < TAMAÑOS.length; t++) {
                double ratioTamaño = (double) TAMAÑOS[t] / TAMAÑOS[t-1];
                double ratioTiempo = (double) tiempos[a][t] / tiempos[a][t-1];
                
                System.out.printf("  %d→%d elementos: %.1fx tamaño, %.1fx tiempo\\n", 
                                TAMAÑOS[t-1], TAMAÑOS[t], ratioTamaño, ratioTiempo);
            }
            
            // Interpretación teórica
            String complejidadTeorica = obtenerComplejidadTeorica(a);
            System.out.println("  Complejidad teórica: " + complejidadTeorica);
            System.out.println();
        }
    }
    
    /**
     * Obtiene la complejidad teórica de un algoritmo
     * 
     * @param algoritmo índice del algoritmo
     * @return descripción de la complejidad
     */
    private String obtenerComplejidadTeorica(int algoritmo) {
        switch (algoritmo) {
            case 0: return "O(n²) - Cuadrática";
            case 1: return "O(n²) promedio, O(n) mejor caso";
            case 2: return "O(n²) - Siempre cuadrática";
            case 3: return "O(n log n) - Logarítmica lineal";
            default: return "Desconocida";
        }
    }
    
    /**
     * Muestra recomendaciones basadas en los resultados
     */
    private void mostrarRecomendaciones() {
        System.out.println("💡 RECOMENDACIONES:\\n");
        System.out.println("• Para arreglos pequeños (< 100 elementos):");
        System.out.println("  - Inserción suele ser eficiente y simple");
        System.out.println();
        System.out.println("• Para arreglos medianos (100-1000 elementos):");
        System.out.println("  - MergeSort ofrece consistencia en el rendimiento");
        System.out.println();
        System.out.println("• Para arreglos grandes (> 1000 elementos):");
        System.out.println("  - MergeSort es generalmente la mejor opción");
        System.out.println("  - Evitar Burbuja y Selección debido a O(n²)");
        System.out.println();
        System.out.println("• Consideraciones especiales:");
        System.out.println("  - Si los datos están parcialmente ordenados: Inserción");
        System.out.println("  - Si se necesita estabilidad: MergeSort");
        System.out.println("  - Si la memoria es limitada: considerar QuickSort");
    }
    
    /**
     * Formatea un tiempo en nanosegundos a una unidad legible
     * 
     * @param nanosegundos tiempo en nanosegundos
     * @return tiempo formateado
     */
    private String formatearTiempo(long nanosegundos) {
        if (nanosegundos >= 1_000_000_000) {
            return String.format("%.3f s", nanosegundos / 1_000_000_000.0);
        } else if (nanosegundos >= 1_000_000) {
            return String.format("%.2f ms", nanosegundos / 1_000_000.0);
        } else if (nanosegundos >= 1_000) {
            return String.format("%.1f μs", nanosegundos / 1_000.0);
        } else {
            return nanosegundos + " ns";
        }
    }
    
    /**
     * Formatea un tiempo para la tabla (versión corta)
     * 
     * @param nanosegundos tiempo en nanosegundos
     * @return tiempo formateado corto
     */
    private String formatearTiempoCorto(long nanosegundos) {
        if (nanosegundos >= 1_000_000_000) {
            return String.format("%.2fs", nanosegundos / 1_000_000_000.0);
        } else if (nanosegundos >= 1_000_000) {
            return String.format("%.1fms", nanosegundos / 1_000_000.0);
        } else if (nanosegundos >= 1_000) {
            return String.format("%.0fμs", nanosegundos / 1_000.0);
        } else {
            return nanosegundos + "ns";
        }
    }
}