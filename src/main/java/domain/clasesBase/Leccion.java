package domain.clasesBase;

public class Leccion {
    private int id;
    private String title;
    private Object content;

    public Leccion(int id, String title, Object context) {
        this.id = id;
        this.title = title;
        this.content = context;
    }

    public Leccion(int id, String title, Object context, boolean estado) {
        this.id = id;
        this.title = title;
        this.content = context;
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

    public Object getContext() {
        return content;
    }

    public void setContext(Object context) {
        this.content = context;
    }
}
