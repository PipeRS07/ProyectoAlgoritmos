package Data;

import domain.clasesBase.Curso;
import domain.clasesBase.TreeException;
import domain.clasesBase.User;
import util.Fabrica;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import domain.bTree.AVLTree;
import util.Ruta;
import util.Utility;

public class CursoData {
    private RandomAccessFile raf;
    private int cantidadDeRegistros;
    private int tamanioRegistro;

    // nombre:100, descripcion:200, duracion:10 (en horas), dificultad:50, siglas:10
    // 100*2 + 200*2 + 10*2 + 50*2 + 10*2 = 740 bytes

    public CursoData() throws IOException {
        init();
    }

    public void guardarCursos() throws TreeException, IOException, domain.bTree.TreeException {
        int size=Utility.cursosRegistrados.size();
        for (int i = 0; i < size; i++) {
            if(!Utility.cursosRegistrados.isEmpty()){
                registrarCurso(i, (Curso) (Utility.cursosRegistrados.root.data));
                Utility.cursosRegistrados.remove(Utility.cursosRegistrados.root.data);
            }

        }
    }

    private void init() throws IOException {
        this.tamanioRegistro = 740;
        File file = new File(Ruta.RUTACURSO);
        if (file.exists() && !file.isFile()) {  // si el archivo existe y está dañado
            throw new IOException(file.getName() + " no es un archivo válido (001)");
        } else {
            this.raf = new RandomAccessFile(Ruta.RUTACURSO, "rw");
            this.cantidadDeRegistros = (int) Math.ceil(this.raf.length() / (double) this.tamanioRegistro);
        }
    }

    public boolean agregarAlFinal(Curso curso) throws IOException {
        if (registrarCurso(this.cantidadDeRegistros, curso)) {
            ++cantidadDeRegistros;
            return true;
        }
        return false;
    }

    private boolean registrarCurso(int posicion, Curso curso) throws IOException {
        boolean respuesta = false;
        if (posicion >= 0 && posicion <= cantidadDeRegistros) {
            if (!(curso.size() > this.tamanioRegistro)) {
                this.raf.seek(posicion * this.tamanioRegistro);
                writeFixedString(raf, curso.getNombre(), 100);
                writeFixedString(raf, curso.getDescripcion(), 200);
                writeFixedString(raf, curso.getDuracion(), 10); // Cambiado a String
                writeFixedString(raf, curso.getDificultad(), 50);
                writeFixedString(raf, curso.getSiglas(), 10);
                respuesta = true;
            }
        }
        return respuesta;
    }

    private void writeFixedString(RandomAccessFile raf, String s, int length) throws IOException {
        int i = 0;
        for (; i < s.length(); i++) {
            raf.writeChar(s.charAt(i));
        }
        for (; i < length; i++) {
            raf.writeChar(' ');
        }
    }

    private String readFixedString(RandomAccessFile raf, int length) throws IOException {
        char[] s = new char[length];
        for (int i = 0; i < length; i++) {
            s[i] = raf.readChar();
        }
        return new String(s).trim();
    }

    public ArrayList<Curso> getCursos() throws IOException {
        ArrayList<Curso> cursos = new ArrayList<>();
        String nombre;
        String descripcion;
        String duracion; // Cambiado a String
        String dificultad;
        String siglas;
        for (int i = 0; i < cantidadDeRegistros; i++) {
            this.raf.seek(i * tamanioRegistro);
            nombre = readFixedString(raf, 100);
            descripcion = readFixedString(raf, 200);
            duracion = readFixedString(raf, 10); // Cambiado a String
            dificultad = readFixedString(raf, 50);
            siglas = readFixedString(raf, 10);
            if (Fabrica.fabricaCursos(nombre, descripcion, duracion, dificultad, siglas) != null)
                cursos.add(Fabrica.fabricaCursos(nombre, descripcion, duracion, dificultad, siglas));
        }
        return cursos;
    }

    public void cargarCursos() throws IOException {
        ArrayList<Curso> cursos = getCursos();
        for (int i = 0; i < cursos.size(); i++) {
            Utility.cursosRegistrados.add(cursos.get(i));
        }
    }

}

//package Data;
//
//import domain.clasesBase.*;
//import domain.graph.GraphException;
//import domain.list.ListException;
//import util.Fabrica;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import domain.bTree.AVLTree;
//import util.Ruta;
//import util.Utility;
//
//public class CursoData {
//    private RandomAccessFile raf;
//    private int cantidadDeRegistros;
//    private int tamanioRegistro;
//
//    // nombre:100, descripcion:200, duracion:10 (en horas), dificultad:50, siglas:10
//    // 100*2 + 200*2 + 10*2 + 50*2 + 10*2 = 740 bytes
//
//    public CursoData() throws IOException {
//        init();
//    }
//
//    public void guardarCursos() throws TreeException, IOException, domain.bTree.TreeException, GraphException, ListException {
//        int size=Utility.cursosRegistrados.size();
//        for (int i = 0; i < size; i++) {
//            if(!Utility.cursosRegistrados.isEmpty()){
//                registrarCurso(i, (Curso) (Utility.cursosRegistrados.root.data));
//                Utility.cursosRegistrados.remove(Utility.cursosRegistrados.root.data);
//            }
//
//        }
//    }
//
//    private void init() throws IOException {
//        this.tamanioRegistro = 740;
//        File file = new File(Ruta.RUTACURSO);
//        if (file.exists() && !file.isFile()) {  // si el archivo existe y está dañado
//            throw new IOException(file.getName() + " no es un archivo válido (001)");
//        } else {
//            this.raf = new RandomAccessFile(Ruta.RUTACURSO, "rw");
//            this.cantidadDeRegistros = (int) Math.ceil(this.raf.length() / (double) this.tamanioRegistro);
//        }
//    }
//
//    public boolean agregarAlFinal(Curso curso) throws IOException, GraphException, ListException, TreeException {
//        if (registrarCurso(this.cantidadDeRegistros, curso)) {
//            ++cantidadDeRegistros;
//            return true;
//        }
//        return false;
//    }
//
////    private boolean registrarCurso(int posicion, Curso curso) throws IOException, ListException, TreeException, GraphException {
////        boolean respuesta = false;
////        if (posicion >= 0 && posicion <= cantidadDeRegistros) {
////            if (!(curso.size() > this.tamanioRegistro)) {
////                this.raf.seek(posicion * this.tamanioRegistro);
////                writeFixedString(raf, curso.getNombre(), 100);
////                writeFixedString(raf, curso.getDescripcion(), 200);
////                writeFixedString(raf, curso.getDuracion(), 10); // Cambiado a String
////                writeFixedString(raf, curso.getDificultad(), 50);
////                writeFixedString(raf, curso.getSiglas(), 10);
////                writeFixedString(raf, Integer.toString(curso.getInstructor().getId()), 20 );
////                writeFixedString(raf, curso.toString(), 500);
////
//////                int n = curso.getEvaluaciones().size();
//////
//////                for (int i = 0; i < n; i++) {
//////                    writeFixedString(raf, ((Evaluacion) curso.getEvaluaciones().root.data ).getNombre(), 30);
//////                    writeFixedString(raf, ((Evaluacion) curso.getEvaluaciones().root.data ).getEnunciado(), 100);
//////                    curso.getEvaluaciones().remove(curso.getEvaluaciones().root);
//////                }
//////                for (int i = 0; i < curso.getInscripcionesEstudiantes().size(); i++) {
//////
//////                    writeFixedString(raf, ((Estudiante) curso.getInscripcionesEstudiantes().getVertex(i)).getId()+"", 20);//aqui se agrega el id de los estudiantes registrados
//////
////////                    for (int j = 0; j < curso.getInscripcionesEstudiantes().size(); j++) {
////////                        for (int k = j; k < curso.getInscripcionesEstudiantes().size(); k++) {
////////                            if(curso.getInscripcionesEstudiantes().containsEdge(curso.getInscripcionesEstudiantes().getVertex(j), curso.getInscripcionesEstudiantes().getVertex(k))){
////////
////////                            }
////////                        }
////////                    }
//////
//////                }
////
////                respuesta = true;
////            }
////        }
////        return respuesta;
////    }
//
//    private boolean registrarCurso(int posicion, Curso curso) throws IOException, ListException, TreeException, GraphException {
//    boolean respuesta = false;
//    if (posicion >= 0 && posicion <= cantidadDeRegistros) {
//        if (!(curso.size() > this.tamanioRegistro)) {
//            this.raf.seek(posicion * this.tamanioRegistro);
//            writeFixedString(raf, curso.getNombre(), 100);
//            writeFixedString(raf, curso.getDescripcion(), 200);
//            writeFixedString(raf, curso.getDuracion(), 10); // Cambiado a String
//            writeFixedString(raf, curso.getDificultad(), 50);
//            writeFixedString(raf, curso.getSiglas(), 10);
//           // writeFixedString(raf, Integer.toString(curso.getInstructor().getId()), 20 );
//            //writeFixedString(raf, curso.toString(), 500);
//
//            respuesta = true;
//        }
//    }
//    return respuesta;
//    }
//
//    private void writeFixedString(RandomAccessFile raf, String s, int length) throws IOException {
//        int i = 0;
//        for (; i < s.length(); i++) {
//            raf.writeChar(s.charAt(i));
//        }
//        for (; i < length; i++) {
//            raf.writeChar(' ');
//        }
//    }
//
//    private String readFixedString(RandomAccessFile raf, int length) throws IOException {
//        char[] s = new char[length];
//        for (int i = 0; i < length; i++) {
//            s[i] = raf.readChar();
//        }
//        return new String(s).trim();
//    }
//
//    public ArrayList<Curso> getCursos() throws IOException {
//        ArrayList<Curso> cursos = new ArrayList<>();
//        String nombre;
//        String descripcion;
//        String duracion; // Cambiado a String
//        String dificultad;
//        String siglas;
//        String idInstructor;
//        for (int i = 0; i < cantidadDeRegistros; i++) {
//            this.raf.seek(i * tamanioRegistro);
//            nombre = readFixedString(raf, 100);
//            descripcion = readFixedString(raf, 200);
//            duracion = readFixedString(raf, 10); // Cambiado a String
//            dificultad = readFixedString(raf, 50);
//            siglas = readFixedString(raf, 10);
//            idInstructor=readFixedString(raf, 20);
//            if (Fabrica.fabricaCursos(nombre, descripcion, duracion, dificultad, siglas) != null)
//                cursos.add(Fabrica.fabricaCursos(nombre, descripcion, duracion, dificultad, siglas));
//        }
//        return cursos;
//    }
//
//    public void cargarCursos() throws IOException {
//        ArrayList<Curso> cursos = getCursos();
//        for (int i = 0; i < cursos.size(); i++) {
//           Utility.cursosRegistrados.add(cursos.get(i));
//        }
//    }
//
//}
