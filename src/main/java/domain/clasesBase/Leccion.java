package domain.clasesBase;

public class Leccion {
    private int id;
    private String title;
    private Object content;

    public Leccion(int id, String title, Object content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    @Override
    public String toString() {
        return "Leccion{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content=" + content +
                '}';
    }
}
