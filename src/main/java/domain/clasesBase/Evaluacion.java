package domain.clasesBase;

public class Evaluacion {
    private String nombre;
    private String tipo;
    private int nota;
    private String enunciado;

    public Evaluacion(String nombre, String tipo, String enunciado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.enunciado = enunciado;
        this.nota =0;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
