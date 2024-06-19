package domain.clasesBase;

public class Leccion {
    private int id;
    private String title;
    private Object context;
    private boolean estado;
    private BST evaluaciones;


    public Leccion(int id, String title, Object context) {
        this.id = id;
        this.title = title;
        this.context = context;
    }

    public Leccion(int id, String title, Object context, boolean estado, BST evaluaciones) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.estado = estado;
        this.evaluaciones = evaluaciones;
    }

    public BST getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(BST evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
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
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }
}
