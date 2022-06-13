package pt.ipvc.backend.data.db.entity.users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "gestor")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Gestor extends Utilizador {

    public Gestor(String username, String password, String email) {
        super(username, password, email);
    }

    public Gestor() {
        super();
    }
}