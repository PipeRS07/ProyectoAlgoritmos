package domain.clasesBase;

import domain.list.CircularLinkedList;

import java.time.LocalDate;
import java.util.ArrayList;

public class Curso {

    private String nombre;
    private int id;
    private String descripcion;
    private String duracion; // Cambiado a String
    private String dificultad;
    private String siglas;
    private Instructor instructor;

    private BST evaluaciones;

    public Curso(String nombre, int id, String descripcion, String duracion, String dificultad, String siglas) {
        this.nombre = nombre;
        this.id=id;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.siglas = siglas;
        this.evaluaciones = new BST();
    }

    public Curso(String nombre, String descripcion, String duracion, String dificultad, String siglas, BST evaluaciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.siglas = siglas;
        this.evaluaciones = evaluaciones;


    }

    public Curso(String nombre, String descripcion, String duracion, String dificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
    }

    public Curso(String nombre, String descripcion, String duracion, String dificultad, String siglas) {
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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public BST getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(BST evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() { // Cambiado a String
        return duracion;
    }

    public void setDuracion(String duracion) { // Cambiado a String
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