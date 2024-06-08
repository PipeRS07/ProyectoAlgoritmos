package domain.clasesBase;

import domain.list.CircularLinkedList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Curso {

    private String nombre;
    private String descripcion;
    private LocalDate duracion;
    private String dificultad;
    private String siglas;

    public Curso(String nombre, String descripcion, LocalDate duracion, String dificultad, String siglas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.siglas = siglas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalDate duracion) {
        this.duracion = duracion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public int size() {
        return 100 * 2 + descripcion.length() * 2 + 10 * 2 + dificultad.length() * 2 + 10 * 2;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion=" + duracion +
                ", dificultad='" + dificultad + '\'' +
                ", siglas='" + siglas + '\'' +
                '}';
    }
}
