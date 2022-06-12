package pt.ipvc.backend.data.db.entity.users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Administrador extends Utilizador {

    public Administrador(String username, String password, String email) {
        super(username, password, email);
    }

    public Administrador() {
        super();
    }
}