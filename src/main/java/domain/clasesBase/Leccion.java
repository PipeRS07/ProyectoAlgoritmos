package domain.clasesBase;

public class Leccion {
    private int id;
    private String title;
    private Object content;
    private String cursoNombre; // Nuevo campo para el nombre del curso

    public Leccion(int id, String title, Object content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Leccion(int id, String title, Object content, String cursoNombre) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cursoNombre = cursoNombre;
    }

    public Leccion(int id, String title, Object content, boolean estado) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    @Override
    public String toString() {
        return "Leccion{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content=" + content +
                '}';
    }
}
