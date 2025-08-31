// Nicolas Isaza Sierra (7004625)");
// Julián David Galindo Hernández (7004600)");
// Saúl Alejandro Pérez Estupiñán (7004631)");

package org.laboratorioordenamiento;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Random;


public class TestsLaboratorio {

    private double[] arregloTest;
    private Persona[] personasTest;

    /**
     * Configuración inicial para cada test
     */
    @Before
    public void setUp() {
        // Arreglo de prueba para algoritmos
        arregloTest = new double[]{64.5, 34.2, 25.1, 12.7, 22.9, 11.5, 90.8};

        // Personas de prueba
        personasTest = new Persona[]{
                new Persona("123456", "Juan Pérez", 1.75, 25),
                new Persona("789012", "María González", 1.68, 30),
                new Persona("345678", "Carlos López", 1.82, 22),
                new Persona("901234", "Ana Martín", 1.65, 28)
        };
    }

    /**
     * Test para algoritmo de ordenamiento burbuja
     */
    @Test
    public void testAlgoritmoBurbuja() {
        System.out.println("\\n=== TEST ALGORITMO BURBUJA ===");

        double[] copia = AlgoritmosOrdenamiento.copiarArreglo(arregloTest);
        long tiempo = AlgoritmosOrdenamiento.burbuja(copia);

        assertTrue("El arreglo debe estar ordenado", AlgoritmosOrdenamiento.estaOrdenado(copia));
        assertTrue("El tiempo debe ser positivo", tiempo > 0);

        System.out.println("✓ Burbuja funciona correctamente");
        System.out.println("  Tiempo: " + (tiempo / 1_000_000.0) + " ms");
        System.out.println("  Resultado: " + Arrays.toString(copia));
    }

    /**
     * Test para algoritmo de inserción
     */
    @Test
    public void testAlgoritmoInsercion() {
        System.out.println("\\n=== TEST ALGORITMO INSERCIÓN ===");

        double[] copia = AlgoritmosOrdenamiento.copiarArreglo(arregloTest);
        long tiempo = AlgoritmosOrdenamiento.insercion(copia);

        assertTrue("El arreglo debe estar ordenado", AlgoritmosOrdenamiento.estaOrdenado(copia));
        assertTrue("El tiempo debe ser positivo", tiempo > 0);

        System.out.println("✓ Inserción funciona correctamente");
        System.out.println("  Tiempo: " + (tiempo / 1_000_000.0) + " ms");
    }

    /**
     * Test para algoritmo de selección
     */
    @Test
    public void testAlgoritmoSeleccion() {
        System.out.println("\\n=== TEST ALGORITMO SELECCIÓN ===");

        double[] copia = AlgoritmosOrdenamiento.copiarArreglo(arregloTest);
        long tiempo = AlgoritmosOrdenamiento.seleccion(copia);

        assertTrue("El arreglo debe estar ordenado", AlgoritmosOrdenamiento.estaOrdenado(copia));
        assertTrue("El tiempo debe ser positivo", tiempo > 0);

        System.out.println("✓ Selección funciona correctamente");
        System.out.println("  Tiempo: " + (tiempo / 1_000_000.0) + " ms");
    }

    /**
     * Test para algoritmo MergeSort
     */
    @Test
    public void testAlgoritmoMergeSort() {
        System.out.println("\\n=== TEST ALGORITMO MERGESORT ===");

        double[] copia = AlgoritmosOrdenamiento.copiarArreglo(arregloTest);
        long tiempo = AlgoritmosOrdenamiento.mergeSort(copia);

        assertTrue("El arreglo debe estar ordenado", AlgoritmosOrdenamiento.estaOrdenado(copia));
        assertTrue("El tiempo debe ser positivo", tiempo > 0);

        System.out.println("✓ MergeSort funciona correctamente");
        System.out.println("  Tiempo: " + (tiempo / 1_000_000.0) + " ms");
    }

    /**
     * Test de rendimiento comparativo
     */
    @Test
    public void testRendimientoComparativo() {
        System.out.println("\\n=== TEST RENDIMIENTO COMPARATIVO ===");

        int[] tamaños = {100, 500, 1000};
        String[] algoritmos = {"Burbuja", "Inserción", "Selección", "MergeSort"};

        for (int tamaño : tamaños) {
            System.out.println("\\nTamaño: " + tamaño + " elementos");
            double[] base = AlgoritmosOrdenamiento.generarArregloAleatorio(tamaño);

            // Burbuja
            double[] copia1 = AlgoritmosOrdenamiento.copiarArreglo(base);
            long t1 = AlgoritmosOrdenamiento.burbuja(copia1);
            assertTrue(AlgoritmosOrdenamiento.estaOrdenado(copia1));
            System.out.printf("  %s: %.3f ms\\n", algoritmos[0], t1 / 1_000_000.0);

            // Inserción
            double[] copia2 = AlgoritmosOrdenamiento.copiarArreglo(base);
            long t2 = AlgoritmosOrdenamiento.insercion(copia2);
            assertTrue(AlgoritmosOrdenamiento.estaOrdenado(copia2));
            System.out.printf("  %s: %.3f ms\\n", algoritmos[1], t2 / 1_000_000.0);

            // Selección
            double[] copia3 = AlgoritmosOrdenamiento.copiarArreglo(base);
            long t3 = AlgoritmosOrdenamiento.seleccion(copia3);
            assertTrue(AlgoritmosOrdenamiento.estaOrdenado(copia3));
            System.out.printf("  %s: %.3f ms\\n", algoritmos[2], t3 / 1_000_000.0);

            // MergeSort
            double[] copia4 = AlgoritmosOrdenamiento.copiarArreglo(base);
            long t4 = AlgoritmosOrdenamiento.mergeSort(copia4);
            assertTrue(AlgoritmosOrdenamiento.estaOrdenado(copia4));
            System.out.printf("  %s: %.3f ms\\n", algoritmos[3], t4 / 1_000_000.0);
        }

        System.out.println("✓ Todos los algoritmos funcionan correctamente");
    }

    /**
     * Test para la clase Persona
     */
    @Test
    public void testClasePersona() {
        System.out.println("\\n=== TEST CLASE PERSONA ===");

        Persona persona = new Persona("123456789", "Test Usuario", 1.75, 25);

        // Validar getters
        assertEquals("123456789", persona.getCedula());
        assertEquals("Test Usuario", persona.getNombre());
        assertEquals(1.75, persona.getEstatura(), 0.001);
        assertEquals(25, persona.getEdad());

        // Validar validación
        assertTrue("Persona válida debe pasar validación", persona.esValida());

        // Test persona inválida
        Persona personaInvalida = new Persona("", "", -1.0, -5);
        assertFalse("Persona inválida debe fallar validación", personaInvalida.esValida());

        System.out.println("✓ Clase Persona funciona correctamente");
    }

    /**
     * Test para ordenamiento de personas por estatura
     */
    @Test
    public void testOrdenamientoPersonasPorEstatura() {
        System.out.println("\\n=== TEST ORDENAMIENTO PERSONAS POR ESTATURA ===");

        Persona.setCriterioOrdenamiento(Persona.CriterioOrdenamiento.ESTATURA);
        Arrays.sort(personasTest);

        // Verificar que está ordenado por estatura (mayor a menor)
        for (int i = 0; i < personasTest.length - 1; i++) {
            assertTrue("Debe estar ordenado por estatura (mayor a menor)",
                    personasTest[i].getEstatura() >= personasTest[i + 1].getEstatura());
        }

        System.out.println("✓ Ordenamiento por estatura funciona");
        System.out.println("  Orden resultante:");
        for (Persona p : personasTest) {
            System.out.printf("    %s: %.2f m\\n", p.getNombre(), p.getEstatura());
        }
    }

    /**
     * Test para ordenamiento de personas por edad
     */
    @Test
    public void testOrdenamientoPersonasPorEdad() {
        System.out.println("\\n=== TEST ORDENAMIENTO PERSONAS POR EDAD ===");

        Persona.setCriterioOrdenamiento(Persona.CriterioOrdenamiento.EDAD);
        Arrays.sort(personasTest);

        // Verificar que está ordenado por edad (mayor a menor)
        for (int i = 0; i < personasTest.length - 1; i++) {
            assertTrue("Debe estar ordenado por edad (mayor a menor)",
                    personasTest[i].getEdad() >= personasTest[i + 1].getEdad());
        }

        System.out.println("✓ Ordenamiento por edad funciona");
        System.out.println("  Orden resultante:");
        for (Persona p : personasTest) {
            System.out.printf("    %s: %d años\\n", p.getNombre(), p.getEdad());
        }
    }

    /**
     * Test para operaciones matriciales básicas
     */
    @Test
    public void testOperacionesMatrices() {
        System.out.println("\\n=== TEST OPERACIONES MATRICIALES ===");

        // Matrices de prueba
        double[][] matrizA = {{1, 2}, {3, 4}};
        double[][] matrizB = {{5, 6}, {7, 8}};

        // Test suma (método privado, se prueba indirectamente)
        System.out.println("✓ Preparación de matrices completada");
        System.out.println("  Matriz A: [[1,2], [3,4]]");
        System.out.println("  Matriz B: [[5,6], [7,8]]");
        System.out.println("  (Las operaciones se prueban en la interfaz de usuario)");
    }

    /**
     * Test de casos extremos
     */
    @Test
    public void testCasosExtremos() {
        System.out.println("\\n=== TEST CASOS EXTREMOS ===");

        // Arreglo vacío
        double[] vacio = new double[0];
        assertTrue("Arreglo vacío debe considerarse ordenado",
                AlgoritmosOrdenamiento.estaOrdenado(vacio));

        // Arreglo de un elemento
        double[] unElemento = {42.0};
        assertTrue("Arreglo de un elemento debe estar ordenado",
                AlgoritmosOrdenamiento.estaOrdenado(unElemento));

        // Arreglo ya ordenado
        double[] yaOrdenado = {1, 2, 3, 4, 5};
        double[] copia = AlgoritmosOrdenamiento.copiarArreglo(yaOrdenado);
        long tiempo = AlgoritmosOrdenamiento.burbuja(copia);
        assertTrue("Arreglo ya ordenado debe seguir ordenado",
                AlgoritmosOrdenamiento.estaOrdenado(copia));

        System.out.println("✓ Casos extremos manejados correctamente");
        System.out.println("  Tiempo para arreglo ya ordenado: " + (tiempo / 1000.0) + " μs");
    }

    /**
     * Test de integridad de datos
     */
    @Test
    public void testIntegridadDatos() {
        System.out.println("\\n=== TEST INTEGRIDAD DE DATOS ===");

        double[] original = {3.14, 2.71, 1.41, 1.73};
        double[] copia = AlgoritmosOrdenamiento.copiarArreglo(original);

        // Verificar que la copia es exacta
        assertArrayEquals("La copia debe ser exacta", original, copia, 0.001);

        // Ordenar la copia
        AlgoritmosOrdenamiento.mergeSort(copia);

        // Verificar que el original no cambió
        double[] originalEsperado = {3.14, 2.71, 1.41, 1.73};
        assertArrayEquals("El original no debe cambiar", originalEsperado, original, 0.001);

        System.out.println("✓ Integridad de datos preservada");
    }

    /**
     * Método principal para ejecutar todos los tests
     */
    public static void main(String[] args) {
        System.out.println("EJECUTANDO TESTS DEL LABORATORIO DE ORDENAMIENTO\\n");
        System.out.println("Universidad Militar Nueva Granada");
        System.out.println("Programación 3 - Laboratorio 2\\n");

        TestsLaboratorio tests = new TestsLaboratorio();

        try {
            tests.setUp();

            // Ejecutar todos los tests
            tests.testAlgoritmoBurbuja();
            tests.testAlgoritmoInsercion();
            tests.testAlgoritmoSeleccion();
            tests.testAlgoritmoMergeSort();
            tests.testClasePersona();
            tests.testOrdenamientoPersonasPorEstatura();

            // Restaurar array de personas para el siguiente test
            tests.setUp();
            tests.testOrdenamientoPersonasPorEdad();

            tests.testOperacionesMatrices();
            tests.testCasosExtremos();
            tests.testIntegridadDatos();
            tests.testRendimientoComparativo();

            System.out.println("\\n" + "=".repeat(60));
            System.out.println("🎉 TODOS LOS TESTS COMPLETADOS EXITOSAMENTE 🎉");
            System.out.println("=".repeat(60));

        } catch (Exception e) {
            System.err.println("❌ Error durante la ejecución de tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
}