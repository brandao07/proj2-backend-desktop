package pt.ipvc.backend.models;

public class UserTypeModel {

    private long id;

    private String username;

    private String email;

    private String type;

    public UserTypeModel() {
    }

    public UserTypeModel(long id, String username, String email, String type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserTypeModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
