package domain.clasesBase;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String role;  //(student,instructor, administrative)
    private String contrasenia;

    public User(int id, String name, String email, String contrasenia) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public User(int id, String name, String email, String role, String contrasenia) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int size() {
        return 4+name.length()+this.role.length()+this.contrasenia.length()+this.email.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(role, user.role) && Objects.equals(contrasenia, user.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, role, contrasenia);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}
