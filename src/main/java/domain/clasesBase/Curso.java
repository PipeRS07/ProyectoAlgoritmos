package domain.clasesBase;

import domain.graph.SinglyLinkedListGraph;
import domain.list.CircularLinkedList;
import domain.list.SinglyLinkedList;

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
    private SinglyLinkedListGraph inscripcionesEstudiantes;
    private SinglyLinkedList materialesDeEstudio;
    private BST lecciones;

    private BST evaluaciones;

    public Curso(String nombre, int id, String descripcion, String duracion, String dificultad, String siglas) {
        this.nombre = nombre;
        this.id=id;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.siglas = siglas;
        this.evaluaciones = new BST();
        this.lecciones=new BST();
        this.inscripcionesEstudiantes = new SinglyLinkedListGraph();
        this.materialesDeEstudio = new SinglyLinkedList();
    }

    public Curso(String nombre, String descripcion, String duracion, String dificultad, String siglas, BST evaluaciones) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.siglas = siglas;
        this.evaluaciones = evaluaciones;
        this.lecciones = new BST();
    }

    public Curso(String nombre, String descripcion, String duracion, String dificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.lecciones = new BST();

    }

    public Curso(String nombre, String descripcion, String duracion, String dificultad, String siglas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.dificultad = dificultad;
        this.siglas = siglas;
        this.lecciones = new BST();

    }




    public SinglyLinkedListGraph getInscripcionesEstudiantes() {
        return inscripcionesEstudiantes;
    }

    public void setInscripcionesEstudiantes(SinglyLinkedListGraph inscripcionesEstudiantes) {
        this.inscripcionesEstudiantes = inscripcionesEstudiantes;
    }

    public Instructor getInstructor() {
        return instructor;
    }
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    public BST getLecciones() {
        return lecciones;
    }

    public void setLecciones(BST lecciones) {
        this.lecciones = lecciones;
    }

    public SinglyLinkedList getMaterialesDeEstudio() {
        return materialesDeEstudio;
    }


    public void setMaterialesDeEstudio(SinglyLinkedList materialesDeEstudio) {
        this.materialesDeEstudio = materialesDeEstudio;
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
                ", id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", siglas='" + siglas + '\'' +
                ", instructor=" + instructor +
                ", inscripcionesEstudiantes=" + inscripcionesEstudiantes +
                ", materialesDeEstudio=" + materialesDeEstudio +
                ", lecciones=" + lecciones +
                ", evaluaciones=" + evaluaciones +
                '}';
    }
}