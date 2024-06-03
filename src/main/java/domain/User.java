package domain;

public class User { //: id(int), name(String), password(String), email(String), role (student,
    //instructor, administrative)
    private int id;
    private String name;
    private String email;
    private Object role;  //(student,instructor, administrative)

    public User(int id, String name, String email, Object role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
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
}
