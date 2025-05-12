package programa.clases;

import java.io.Serializable;
import java.util.Objects;

public class Aldeano implements Comparable<Aldeano>, Serializable {
    String nombre;
    String profesion;
    int numeroRomanosAsesinados;

    public Aldeano(String nombre, String profesion, int numeroRomanosAsesinados) {
        this.nombre = nombre;
        this.profesion = profesion;
        this.numeroRomanosAsesinados = numeroRomanosAsesinados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getNumeroRomanosAsesinados() {
        return numeroRomanosAsesinados;
    }

    public void setNumeroRomanosAsesinados(int numeroRomanosAsesinados) {
        this.numeroRomanosAsesinados = numeroRomanosAsesinados;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Aldeano aldeano = (Aldeano) o;
        return numeroRomanosAsesinados == aldeano.numeroRomanosAsesinados && Objects.equals(nombre, aldeano.nombre) && Objects.equals(profesion, aldeano.profesion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, profesion, numeroRomanosAsesinados);
    }

    @Override
    public int compareTo(Aldeano a) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-20s | %-6d", nombre, profesion, numeroRomanosAsesinados);
    }
}
