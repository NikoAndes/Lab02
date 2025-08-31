# Sistema Ordenamiento LAB02

Sistema Ordenamiento LAB 2 es una aplicación completa de gestión y análisis de algoritmos de ordenamiento desarrollada en Java que combina una interfaz gráfica moderna (Swing) con módulos de consola interactiva. Este proyecto fue creado como parte del **Laboratorio 2** de Programación 3 en la Universidad Militar Nueva Granada, con el objetivo de aplicar conceptos de arreglos, algoritmos de ordenamiento, programación orientada a objetos y diseño de interfaces gráficas.

## 🧠 Descripción del Proyecto

SistemaOrdenamiento permite realizar operaciones con matrices, comparaciones de rendimiento entre algoritmos de ordenamiento, y gestión visual de personas con capacidades de ordenamiento por diferentes criterios. El sistema incluye pruebas unitarias completas, una interfaz gráfica moderna inspirada en dashboards profesionales, y herramientas de análisis de complejidad computacional.

## ✨ Características Principales

### 📊 Punto 3.1 - Operaciones con Matrices
✅ **Suma de matrices** con validación de dimensiones  
✅ **Producto de matrices** con verificación de compatibilidad  
✅ **Inversa de matriz** usando eliminación de Gauss-Jordan  
✅ **Producto matriz por vector** con control de errores  
✅ **Interfaz de consola** intuitiva con menús navegables  
✅ **Validación completa** de entrada de datos  

### 🔄 Punto 3.2 - Análisis Comparativo de Algoritmos
✅ **Algoritmo Burbuja (Bubble Sort)** implementado  
✅ **Algoritmo Inserción (Insertion Sort)** implementado  
✅ **Algoritmo Selección (Selection Sort)** implementado  
✅ **Algoritmo MergeSort** implementado  
✅ **Tabla comparativa** de tiempos de ejecución  
✅ **Análisis con diferentes tamaños** (100, 500, 1000, 5000, 10000 elementos)  
✅ **Generación de números aleatorios** tipo double  
✅ **Medición precisa** en nanosegundos  

### 🖥️ Punto 3.3 - GUI de Ordenamiento de Personas
✅ **Interfaz gráfica moderna** inspirada en dashboards profesionales  
✅ **Clase Persona** con campos: cédula, nombre, estatura, edad  
✅ **Ordenamiento por estatura** de mayor a menor  
✅ **Ordenamiento por edad** de mayor a menor  
✅ **Selección de algoritmo** (MergeSort o Burbuja)  
✅ **Tema claro/oscuro** intercambiable  
✅ **Botones con degradados** y efectos hover  
✅ **Ventanas redondeadas** tipo card  
✅ **Validación de datos** en tiempo real  
✅ **Gestión completa** (agregar, eliminar, limpiar)  

## 🛠️ Tecnologías Utilizadas

- **Java 17** - Lenguaje de programación principal
- **Swing** - Framework de interfaz gráfica
- **JUnit 5** - Framework de pruebas unitarias  
- **NetBeans IDE** - Entorno de desarrollo integrado
- **Maven/Gradle** - Gestión de dependencias
- **UML** - Modelado de arquitectura

## 📋 Cumplimiento de Requerimientos

### ✅ Requisitos Generales Cumplidos

| Requerimiento | Estado | Implementación |
|---------------|--------|----------------|
| **Menú principal navegable** | ✅ Completo | MenuPrincipal con opciones 1, 2, 3 y 0 para salir |
| **No cierre automático** | ✅ Completo | Bucle while hasta que usuario seleccione "0. Salir" |
| **Submenús específicos** | ✅ Completo | Submenús para puntos 3.1 y 3.2 |
| **Programación orientada a objetos** | ✅ Completo | Una clase por archivo, encapsulación correcta |
| **Código funcional** | ✅ Completo | Todas las funcionalidades operativas |

### ✅ Punto 3.1 - Operaciones Matriciales

| Operación | Estado | Detalles |
|-----------|--------|----------|
| **Suma de matrices** | ✅ Implementado | Validación de dimensiones compatibles |
| **Producto de matrices** | ✅ Implementado | Verificación m×n y n×p para resultado m×p |
| **Inversa de matriz** | ✅ Implementado | Método Gauss-Jordan con manejo de singularidad |
| **Matriz por vector** | ✅ Implementado | Producto con validación de dimensiones |

### ✅ Punto 3.2 - Análisis de Algoritmos

| Algoritmo | Estado | Complejidad | Tamaños Probados |
|-----------|--------|-------------|------------------|
| **Burbuja** | ✅ Implementado | O(n²) | 100, 500, 1K, 5K, 10K |
| **Inserción** | ✅ Implementado | O(n²) | 100, 500, 1K, 5K, 10K |
| **Selección** | ✅ Implementado | O(n²) | 100, 500, 1K, 5K, 10K |
| **MergeSort** | ✅ Implementado | O(n log n) | 100, 500, 1K, 5K, 10K |

### ✅ Punto 3.3 - GUI de Personas

| Característica | Estado | Implementación |
|----------------|--------|----------------|
| **Clase Persona** | ✅ Completo | Cédula, nombre, estatura, edad |
| **Ordenamiento por estatura** | ✅ Completo | Mayor a menor con compareTo personalizado |
| **Ordenamiento por edad** | ✅ Completo | Mayor a menor con criterio seleccionable |
| **Algoritmo MergeSort** | ✅ Completo | Implementación optimizada |
| **Algoritmo Burbuja** | ✅ Completo | Implementación clásica |
| **Interfaz gráfica** | ✅ Completo | Dashboard moderno con tema claro/oscuro |

## 📦 Estructura del Proyecto

```
laboratorioordenamiento/
├── MenuPrincipal.java          # Punto de entrada principal
├── OperacionesMatriciales.java # Punto 3.1 - Operaciones con matrices
├── ComparadorOrdenamiento.java # Punto 3.2 - Análisis de algoritmos  
├── AlgoritmosOrdenamiento.java # Implementación de todos los algoritmos
├── GUIPersonas.java            # Punto 3.3 - Interfaz gráfica moderna
├── Persona.java                # Clase modelo para GUI
└── README.md                   # Documentación del proyecto
```

## 🖼️ Capturas de Pantalla

### Figura Empaquetado GUI
<img width="1365" height="728" alt="Figura Empaquetado GUI 1" src="https://github.com/user-attachments/assets/40490b12-6e52-4571-bd1e-5c9d89b24d88" />
<img width="1366" height="727" alt="Figura Empaquetado GUI 2" src="https://github.com/user-attachments/assets/9e59327c-9366-4499-992a-a80435452e93" />
<img width="1365" height="723" alt="Figura Empaquetado GUI 3" src="https://github.com/user-attachments/assets/09402065-97ec-474d-a019-d64c8a3f2eed" />
*Interfaz gráfica moderna con tema claro, cards redondeados y diseño tipo dashboard*

### Figura Empaquetado Consola  
<img width="539" height="379" alt="Figura Empaquetado Consola" src="https://github.com/user-attachments/assets/1317a335-658e-49f6-a4e8-0dbf809027e7" />

*Menú principal de consola con navegación intuitiva y banner universitario*

### Figura Tests
<img width="1236" height="435" alt="Figura Test" src="https://github.com/user-attachments/assets/0263aaf3-415b-4a9d-a9e5-dc31cbc90007" />
*Suite completa de pruebas unitarias - 11 tests pasados exitosamente*

## 📄 Diagrama UML
<img width="1260" height="1321" alt="Diagrama" src="https://github.com/user-attachments/assets/481a22c4-d3ab-4b1b-98f9-3ddc30355e67" />
*Arquitectura completa del sistema mostrando todas las relaciones entre clases*

## 🚀 Instalación y Ejecución

### Prerrequisitos
- **Java Development Kit (JDK) 8 o superior**
- **NetBeans IDE** (recomendado) o cualquier IDE compatible con Java
- **JUnit 5** para ejecutar las pruebas

### Pasos de Instalación
1. **Clonar o descargar** el proyecto
2. **Abrir en NetBeans** o tu IDE preferido
3. **Compilar** todas las clases Java
4. **Ejecutar** la clase `MenuPrincipal.java`

### Ejecución
```bash
# Compilar
javac *.java

# Ejecutar menú principal
java MenuPrincipal

# Ejecutar GUI directamente (opcional)
java GUIPersonas
```

## 🎯 Funcionalidades Destacadas

### 🎨 Interfaz Gráfica Moderna
- **Dashboard profesional** inspirado en aplicaciones modernas
- **Tema claro/oscuro** intercambiable dinámicamente  
- **Cards redondeados** con efectos visuales
- **Botones con degradados** púrpura/rosa
- **Animaciones suaves** de carga y hover
- **Validación en tiempo real** de formularios
- **Tabla estilizada** con filas alternadas

### ⚡ Optimizaciones de Rendimiento
- **SwingWorker** para operaciones de ordenamiento en background
- **Medición precisa** de tiempo en nanosegundos
- **Algoritmos optimizados** para diferentes tamaños de datos
- **Gestión eficiente** de memoria para arreglos grandes

### 🔧 Validaciones Robustas
- **Matrices**: Dimensiones compatibles y detecta matrices singulares
- **Personas**: Rangos válidos (estatura 0.5-3.0m, edad 1-150 años)  
- **Cédulas únicas**: Previene duplicados en el sistema
- **Entrada numérica**: Manejo de excepciones NumberFormatException

## 📊 Resultados de Testing

El sistema incluye una **suite completa de 11 pruebas unitarias** que validan:

- ✅ **Casos extremos** de algoritmos de ordenamiento
- ✅ **Operaciones matriciales** con diferentes dimensiones
- ✅ **Validaciones de la clase Persona**  
- ✅ **Integridad de datos** en operaciones complejas
- ✅ **Rendimiento comparativo** entre algoritmos

**Tiempo de ejecución de tests:** 267ms  
**Cobertura:** 11/11 tests pasados exitosamente

## 🎓 Objetivos Académicos Alcanzados

### Objetivo General
✅ **Diferenciación e implementación** de diferentes métodos de ordenamiento

### Objetivos Específicos  
✅ **Utilización de algoritmos** de ordenamiento con arreglos  
✅ **Comparación de algoritmos** a nivel de complejidad temporal  
✅ **Implementación de interfaces gráficas** modernas y funcionales  
✅ **Aplicación de principios** de programación orientada a objetos  

## 👥 Equipo de Desarrollo

**Desarrollado por:**
Nicolas Isaza Sierra (7004625)");
Julián David Galindo Hernández (7004600)");
Saúl Alejandro Pérez Estupiñán (7004631)");

**Universidad:** Militar Nueva Granada  
**Programa:** Ingeniería Mecatrónica  
**Asignatura:** Programación 3 - Semestre III  
**Año:** 2025

## 📚 Referencias Académicas

- **Cormen, T. H.** et al. (2009). *Introduction to algorithms*. MIT Press
- **Joyanes Aguilar, L.** & Zahonero, I. (2008). *Estructuras de datos en Java*. McGraw-Hill  
- **NetBeans Documentation** - GUI Design and Java Development
- **Universidad Militar Nueva Granada** - Guía de Laboratorio GL-AA-F-1

## 🏆 Características Técnicas Destacadas

### 🎨 Diseño Visual
- **Cards con esquinas redondeadas** usando `RoundedPanel` personalizado
- **Sistema de temas** con `TemaColor` para claro/oscuro
- **Degradados CSS-like** en botones usando `GradientPaint`
- **Tipografía Segoe UI** para apariencia profesional

### ⚙️ Arquitectura de Software  
- **Patrón MVC** implícito en la organización de clases
- **Enum CriterioOrdenamiento** para type-safe comparisons
- **Factory methods** para creación de componentes UI
- **Observer pattern** en eventos de la interfaz

### 🧪 Metodología de Testing
- **Test-Driven Development** con casos de prueba comprehensivos
- **Casos límite** para validar robustez
- **Benchmarking automatizado** de algoritmos
- **Cobertura de código** para todas las funcionalidades críticas

---

**© 2025 Universidad Militar Nueva Granada - Programación 3**  
*Este proyecto cumple integralmente con los requerimientos del Laboratorio 2 de Arreglos y Algoritmos de Ordenamiento*
