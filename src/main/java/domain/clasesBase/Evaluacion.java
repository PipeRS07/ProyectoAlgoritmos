package domain.clasesBase;

public class Evaluacion {
    private String nombre;
    //private String tipo;

    private String enunciado;

    public Evaluacion(String nombre, String enunciado) {
        this.nombre = nombre;
        this.enunciado = enunciado;

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
