package domain.clasesBase;

public class Reporte {
    private String titulo;
    private String contenido;
    private String destinatario;

    public Reporte(String titulo, String contenido, String destinatario) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.destinatario = destinatario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
}
