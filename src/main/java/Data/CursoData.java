package Data;

import domain.clasesBase.Curso;
import util.Fabrica;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import domain.bTree.AVLTree;
import util.Ruta;

public class CursoData {
    private RandomAccessFile raf;
    private int cantidadDeRegistros;
    private int tamanioRegistro;

    // nombre:100, descripcion:200, duracion:10 (YYYY-MM-DD), dificultad:50, siglas:10
    // 100*2 + 200*2 + 10*2 + 50*2 + 10*2 = 740 bytes

    public CursoData() throws IOException {
        init();
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

    private boolean registrarCurso(int posicion, Curso curso) throws IOException {
        boolean respuesta = false;
        if (posicion >= 0 && posicion <= cantidadDeRegistros) {
            if (!(curso.size() > this.tamanioRegistro)) {
                this.raf.seek(posicion * this.tamanioRegistro);
                writeFixedString(raf, curso.getNombre(), 100);
                writeFixedString(raf, curso.getDescripcion(), 200);
                writeFixedString(raf, curso.getDuracion().format(DateTimeFormatter.ISO_LOCAL_DATE), 10);
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

    public boolean agregarAlFinal(Curso curso) throws IOException {
        if (registrarCurso(this.cantidadDeRegistros, curso)) {
            ++cantidadDeRegistros;
            return true;
        }
        return false;
    }

    public ArrayList<Curso> getCursos() throws IOException {
        ArrayList<Curso> cursos = new ArrayList<>();
        String nombre;
        String descripcion;
        LocalDate duracion;
        String dificultad;
        String siglas;
        for (int i = 0; i < cantidadDeRegistros; i++) {
            this.raf.seek(i * tamanioRegistro);
            nombre = readFixedString(raf, 100);
            descripcion = readFixedString(raf, 200);
            duracion = LocalDate.parse(readFixedString(raf, 10), DateTimeFormatter.ISO_LOCAL_DATE);
            dificultad = readFixedString(raf, 50);
            siglas = readFixedString(raf, 10);
            if (Fabrica.fabricaCursos(nombre, descripcion, duracion, dificultad, siglas) != null)
                cursos.add(Fabrica.fabricaCursos(nombre, descripcion, duracion, dificultad, siglas));
        }
        return cursos;
    }

    public Curso getCursoPorNombre(String nombre) throws IOException {
        ArrayList<Curso> cursos = this.getCursos();
        for (Curso curso : cursos) {
            if (curso.getNombre().equals(nombre)) {
                return curso;
            }
        }
        return null;
    }

    public void cargarCursos(Object cursosEnElSistema) throws IOException {
        for (Curso curso : getCursos()) {
            Fabrica.fabricaTDA(cursosEnElSistema, curso);
        }
    }
//    public class CursoData {
//        private RandomAccessFile raf;
//        private int cantidadDeRegistros;
//        private int tamanioRegistro;
//
//        // id:4   nombre:100   descripcion:200   instructorId:4
//        // 4+100+200+4 = 308
//
//        public CursoData() throws IOException {
//            init();
//        }
//
//        private void init() throws IOException {
//            this.tamanioRegistro = 308;
//            File file = new File(Ruta.RUTACURSO);
//            if (file.exists() && !file.isFile()) {  // si el archivo existe y está dañado
//                throw new IOException(file.getName() + " no es un archivo válido (001)");
//            } else {
//                this.raf = new RandomAccessFile(Ruta.RUTACURSO, "rw");
//                this.cantidadDeRegistros = (int) Math.ceil(this.raf.length() / (double) this.tamanioRegistro);
//            }
//        }
//
//        private boolean registrarCurso(int posicion, Curso curso) throws IOException {
//            boolean respuesta = false;
//            if (posicion >= 0 && posicion <= cantidadDeRegistros) {
//                if (!(curso.size() > this.tamanioRegistro)) {
//                    this.raf.seek(posicion * this.tamanioRegistro);
//                    raf.writeInt(curso.getId());
//                    raf.writeUTF(curso.getNombre());
//                    raf.writeUTF(curso.getDescripcion());
//                    respuesta = true;
//                }
//            }
//            return respuesta;
//        }
//
//        public boolean agregarAlFinal(Curso curso) throws IOException {
//            if (registrarCurso(this.cantidadDeRegistros, curso)) {
//                ++cantidadDeRegistros;
//                return true;
//            }
//            return false;
//        }
//
//        public ArrayList<Curso> getCursos() throws IOException {
//            ArrayList<Curso> cursos = new ArrayList<>();
//            int id;
//            String nombre;
//            String descripcion;
//            int instructorId;
//            for (int i = 0; i < cantidadDeRegistros; i++) {
//                this.raf.seek(i * tamanioRegistro);
//                id = raf.readInt();
//                nombre = raf.readUTF();
//                descripcion = raf.readUTF();
//                instructorId = raf.readInt();
//                if (Fabrica.fabricaCursos(id, nombre, descripcion, instructorId) != null)
//                    cursos.add(Fabrica.fabricaCursos(id, nombre, descripcion, instructorId));
//            }
//            return cursos;
//        }
//
//        public Curso getCursoPorNombre(String nombre) throws IOException {
//            ArrayList<Curso> cursos = this.getCursos();
//            for (Curso curso : cursos) {
//                if (curso.getNombre().equals(nombre)) {
//                    return curso;
//                }
//            }
//            return null;
//        }
//
//        public void cargarCursos(Object cursosEnElSistema) throws IOException {
//            for (Curso curso : getCursos()) {
//                Fabrica.fabricaTDA(cursosEnElSistema, curso);
//            }
//        }
//    }
}
