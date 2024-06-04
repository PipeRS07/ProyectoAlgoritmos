package domain;

public abstract class User {
    private int id;
    private String name;
    private String email;
    private Object role;  //(student,instructor, administrative)
    private String contrasenia;

    public User(int id, String name, String email, String contrasenia) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public User(int id, String name, String email, Object role, String contrasenia) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
