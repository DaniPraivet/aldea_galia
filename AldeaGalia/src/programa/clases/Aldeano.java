/**
 * Paquete para las clases
 */
package programa.clases;

import java.io.Serializable;
import java.util.Objects;

/**
 * Son los que forman parte en la aldea
 * @see Aldea
 * @see programa.vistas.Main
 * @see java.io.Serializable
 * @see java.lang.Comparable
 */
public class Aldeano implements Comparable<Aldeano>, Serializable {
    /**
     * Nombre del aldeano
     */
    String nombre;
    /**
     * Profesión del aldeano
     */
    String profesion;
    /**
     * Número de romanos derribados
     */
    int numeroRomanosAsesinados;

    /**
     * Constructor principal de la clase aldeano
     * @param nombre nombre que vaya a tener el aldeano
     * @param profesion trabajo que desempeñe el aldeano en la aldea
     * @param numeroRomanosAsesinados número de romanos derribados
     * @see Aldea
     */
    public Aldeano(String nombre, String profesion, int numeroRomanosAsesinados) {
        this.nombre = nombre;
        this.profesion = profesion;
        this.numeroRomanosAsesinados = numeroRomanosAsesinados;
    }

    /**
     * Recibir el nombre de un objeto aldeano
     * @return nombre del aldeano
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Recibir la profesión de un objeto aldeano
     * @return la profesión del aldeano
     */
    public String getProfesion() {
        return profesion;
    }

    /**
     * Recibir el número de romanos derribados
     * @return número de romanos derribados
     */
    public int getNumeroRomanosAsesinados() {
        return numeroRomanosAsesinados;
    }

    /**
     * Indica cuando un aldeano es igual a otro
     * @param o objeto a comparar
     * @return verdadero si el objeto a comparar es igual que el aldeano; en caso contrario falso
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aldeano aldeano = (Aldeano) o;
        return numeroRomanosAsesinados == aldeano.numeroRomanosAsesinados && Objects.equals(nombre, aldeano.nombre) && Objects.equals(profesion, aldeano.profesion);
    }

    /**
     * Devuelve un valor codificado del aldeano, esto beneficia a las tablas provenientes de un HashMap
     * @return un valor hash code para este aldeano
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, profesion, numeroRomanosAsesinados);
    }

    /**
     * Método no implementado debido a que estoy comparando los aldeanos en el método de ordenar la lista
     * @param a el aldeano a comparar
     * @return número negativo, cero o número positivo en caso de ser menor, igual o mayor que el aldeano especificado
     * @see Aldea
     */
    @Override
    public int compareTo(Aldeano a) {
        return 0;
    }

    /**
     * Mostrar de manera personalizada el aldeano acorde al formato de la aldea.
     * @return texto a mostrar
     * @see Aldea
     */
    @Override
    public String toString() {
        return String.format("%-20s | %-20s | %-6d", nombre, profesion, numeroRomanosAsesinados);
    }
}
