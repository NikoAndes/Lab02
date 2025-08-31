// Nicolas Isaza Sierra (7004625)");
// Julián David Galindo Hernández (7004600)");
// Saúl Alejandro Pérez Estupiñán (7004631)");

package org.laboratorioordenamiento;

public class AlgoritmosOrdenamiento {
    
    /**
     * Algoritmo de ordenamiento burbuja
     * Complejidad: O(n²) en el peor caso, O(n) en el mejor caso
     * 
     * @param arreglo arreglo a ordenar
     * @return tiempo de ejecución en nanosegundos
     */

    public static long burbuja(double[] arreglo) {
        long inicio = System.nanoTime();
        
        int n = arreglo.length;
        boolean intercambio;
        
        // Implementación optimizada del algoritmo burbuja
        for (int j = 0; j < n - 1; j++) {
            intercambio = false;
            
            // En cada pasada, el elemento más grande "burbujea" hacia el final
            for (int i = 0; i < n - j - 1; i++) {
                if (arreglo[i] > arreglo[i + 1]) {
                    // Intercambiar elementos adyacentes
                    intercambiar(arreglo, i, i + 1);
                    intercambio = true;
                }
            }
            
            // Si no hubo intercambios, el arreglo ya está ordenado
            if (!intercambio) {
                break;
            }
        }
        
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    /**
     * Algoritmo de ordenamiento por inserción
     * Complejidad: O(n²) en el peor caso, O(n) en el mejor caso
     * 
     * @param arreglo arreglo a ordenar
     * @return tiempo de ejecución en nanosegundos
     */
    public static long insercion(double[] arreglo) {
        long inicio = System.nanoTime();
        
        int n = arreglo.length;
        
        // Comenzar desde el segundo elemento
        for (int j = 1; j < n; j++) {
            double clave = arreglo[j];
            int i = j - 1;
            
            // Mover elementos mayores que 'clave' una posición adelante
            while (i >= 0 && arreglo[i] > clave) {
                arreglo[i + 1] = arreglo[i];
                i--;
            }
            
            // Insertar la clave en su posición correcta
            arreglo[i + 1] = clave;
        }
        
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    /**
     * Algoritmo de ordenamiento por selección
     * Complejidad: O(n²) en todos los casos
     * 
     * @param arreglo arreglo a ordenar
     * @return tiempo de ejecución en nanosegundos
     */
    public static long seleccion(double[] arreglo) {
        long inicio = System.nanoTime();
        
        int n = arreglo.length;
        
        // Encontrar el mínimo en cada iteración
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            
            // Buscar el elemento mínimo en el resto del arreglo
            for (int j = i + 1; j < n; j++) {
                if (arreglo[j] < arreglo[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }
            
            // Intercambiar el mínimo con el elemento actual
            if (indiceMinimo != i) {
                intercambiar(arreglo, i, indiceMinimo);
            }
        }
        
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    /**
     * Algoritmo MergeSort (ordenamiento por mezcla)
     * Complejidad: O(n log n) en todos los casos
     * 
     * @param arreglo arreglo a ordenar
     * @return tiempo de ejecución en nanosegundos
     */
    public static long mergeSort(double[] arreglo) {
        long inicio = System.nanoTime();
        
        if (arreglo.length > 1) {
            mergeSortRecursivo(arreglo, 0, arreglo.length - 1);
        }
        
        long fin = System.nanoTime();
        return fin - inicio;
    }
    
    /**
     * Implementación recursiva del algoritmo MergeSort
     * 
     * @param arreglo arreglo a ordenar
     * @param izquierda índice izquierdo
     * @param derecha índice derecho
     */
    private static void mergeSortRecursivo(double[] arreglo, int izquierda, int derecha) {
        if (izquierda < derecha) {
            // Encontrar el punto medio
            int medio = izquierda + (derecha - izquierda) / 2;
            
            // Ordenar recursivamente las dos mitades
            mergeSortRecursivo(arreglo, izquierda, medio);
            mergeSortRecursivo(arreglo, medio + 1, derecha);
            
            // Mezclar las mitades ordenadas
            merge(arreglo, izquierda, medio, derecha);
        }
    }
    
    /**
     * Mezcla dos subarreglos ordenados en uno solo
     * 
     * @param arreglo arreglo principal
     * @param izquierda índice izquierdo
     * @param medio índice medio
     * @param derecha índice derecho
     */
    private static void merge(double[] arreglo, int izquierda, int medio, int derecha) {
        // Calcular tamaños de los subarreglos
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;
        
        // Crear arreglos temporales
        double[] izquierdo = new double[n1];
        double[] derecho = new double[n2];
        
        // Copiar datos a arreglos temporales
        System.arraycopy(arreglo, izquierda, izquierdo, 0, n1);
        System.arraycopy(arreglo, medio + 1, derecho, 0, n2);
        
        // Mezclar los arreglos temporales de vuelta al arreglo principal
        int i = 0, j = 0, k = izquierda;
        
        // Comparar elementos y mezclar en orden
        while (i < n1 && j < n2) {
            if (izquierdo[i] <= derecho[j]) {
                arreglo[k] = izquierdo[i];
                i++;
            } else {
                arreglo[k] = derecho[j];
                j++;
            }
            k++;
        }
        
        // Copiar elementos restantes del arreglo izquierdo
        while (i < n1) {
            arreglo[k] = izquierdo[i];
            i++;
            k++;
        }
        
        // Copiar elementos restantes del arreglo derecho
        while (j < n2) {
            arreglo[k] = derecho[j];
            j++;
            k++;
        }
    }
    
    /**
     * Intercambia dos elementos en un arreglo
     * 
     * @param arreglo arreglo donde intercambiar
     * @param i primer índice
     * @param j segundo índice
     */
    private static void intercambiar(double[] arreglo, int i, int j) {
        double temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
    
    /**
     * Verifica si un arreglo está ordenado correctamente
     * 
     * @param arreglo arreglo a verificar
     * @return true si está ordenado, false en caso contrario
     */
    public static boolean estaOrdenado(double[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            if (arreglo[i] > arreglo[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Crea una copia de un arreglo
     * 
     * @param original arreglo original
     * @return copia del arreglo
     */
    public static double[] copiarArreglo(double[] original) {
        double[] copia = new double[original.length];
        System.arraycopy(original, 0, copia, 0, original.length);
        return copia;
    }
    
    /**
     * Genera un arreglo de números aleatorios
     * 
     * @param tamaño tamaño del arreglo
     * @return arreglo con números aleatorios
     */
    public static double[] generarArregloAleatorio(int tamaño) {
        double[] arreglo = new double[tamaño];
        java.util.Random random = new java.util.Random();
        
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = random.nextDouble() * 1000; // Números entre 0 y 1000
        }
        
        return arreglo;
    }
}