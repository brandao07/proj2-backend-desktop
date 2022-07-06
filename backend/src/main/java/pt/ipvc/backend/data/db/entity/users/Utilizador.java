package pt.ipvc.backend.data.db.entity.users;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "utilizador")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilizador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true, length = 25)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    public Utilizador() {

    }

    public Utilizador(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        dataCriacao = new Date(System.currentTimeMillis());
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }


}