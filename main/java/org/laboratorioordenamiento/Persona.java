package org.laboratorioordenamiento;
// Nicolas Isaza Sierra (7004625)");
// Julián David Galindo Hernández (7004600)");
// Saúl Alejandro Pérez Estupiñán (7004631)");

public class Persona implements Comparable<Persona> {
    
    // Atributos privados según especificaciones
    private String cedula;
    private String nombre;
    private double estatura; // en metros
    private int edad;        // en años
    
    // Criterio actual de ordenamiento (estático para compartir entre instancias)
    private static CriterioOrdenamiento criterioActual = CriterioOrdenamiento.ESTATURA;
    
    /**
     * Enumeración para definir los criterios de ordenamiento
     */
    public enum CriterioOrdenamiento {
        ESTATURA, EDAD
    }
    
    /**
     * Constructor por defecto
     */
    public Persona() {
        this("", "", 0.0, 0);
    }
    
    /**
     * Constructor con parámetros
     * 
     * @param cedula cédula de la persona
     * @param nombre nombre completo
     * @param estatura estatura en metros
     * @param edad edad en años
     */
    public Persona(String cedula, String nombre, double estatura, int edad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.estatura = estatura;
        this.edad = edad;
    }
    
    // Métodos getter y setter
    
    /**
     * Obtiene la cédula
     * @return cédula
     */
    public String getCedula() {
        return cedula;
    }
    
    /**
     * Establece la cédula
     * @param cedula nueva cédula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    /**
     * Obtiene el nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la estatura
     * @return estatura en metros
     */
    public double getEstatura() {
        return estatura;
    }
    
    /**
     * Establece la estatura
     * @param estatura nueva estatura en metros
     */
    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }
    
    /**
     * Obtiene la edad
     * @return edad en años
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * Establece la edad
     * @param edad nueva edad en años
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Establece el criterio de ordenamiento para todas las instancias
     * 
     * @param criterio nuevo criterio de ordenamiento
     */
    public static void setCriterioOrdenamiento(CriterioOrdenamiento criterio) {
        criterioActual = criterio;
    }
    
    /**
     * Obtiene el criterio de ordenamiento actual
     * 
     * @return criterio actual
     */
    public static CriterioOrdenamiento getCriterioOrdenamiento() {
        return criterioActual;
    }
    
    /**
     * Implementación del método compareTo para ordenamiento
     * El ordenamiento es de MAYOR a MENOR según especificaciones
     * 
     * @param otra persona a comparar
     * @return resultado de la comparación
     */
    @Override
    public int compareTo(Persona otra) {
        switch (criterioActual) {
            case ESTATURA:
                // De mayor a menor estatura
                return Double.compare(otra.estatura, this.estatura);
            case EDAD:
                // De mayor a menor edad
                return Integer.compare(otra.edad, this.edad);
            default:
                return 0;
        }
    }
    
    /**
     * Valida si los datos de la persona son correctos
     * 
     * @return true si los datos son válidos, false en caso contrario
     */
    public boolean esValida() {
        return !cedula.trim().isEmpty() && 
               !nombre.trim().isEmpty() && 
               estatura > 0 && estatura <= 3.0 && // Estatura razonable
               edad > 0 && edad <= 150; // Edad razonable
    }
    
    /**
     * Representación en cadena de la persona
     * 
     * @return representación textual
     */
    @Override
    public String toString() {
        return String.format("Persona{cédula='%s', nombre='%s', estatura=%.2fm, edad=%d años}", 
                           cedula, nombre, estatura, edad);
    }
    
    /**
     * Genera una representación para mostrar en tabla
     * 
     * @return arreglo con los datos para mostrar
     */
    public Object[] toTableRow() {
        return new Object[]{cedula, nombre, String.format("%.2f m", estatura), edad + " años"};
    }
    
    /**
     * Método equals para comparar personas
     * 
     * @param obj objeto a comparar
     * @return true si son iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Persona persona = (Persona) obj;
        return cedula.equals(persona.cedula);
    }
    
    /**
     * Método hashCode basado en la cédula
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return cedula.hashCode();
    }
    
    /**
     * Crea una copia de la persona
     * 
     * @return nueva instancia con los mismos datos
     */
    public Persona clonar() {
        return new Persona(this.cedula, this.nombre, this.estatura, this.edad);
    }
    
    /**
     * Formatea la estatura para mostrar
     * 
     * @return estatura formateada
     */
    public String getEstaturaFormateada() {
        return String.format("%.2f m", estatura);
    }
    
    /**
     * Formatea la edad para mostrar
     * 
     * @return edad formateada
     */
    public String getEdadFormateada() {
        return edad + " años";
    }
}