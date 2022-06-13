package pt.ipvc.backend.data.db.entity.users;

import pt.ipvc.backend.data.db.entity.Competicao;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "gestor")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Gestor extends Utilizador {
    @OneToMany(mappedBy = "gestor", orphanRemoval = true)
    private Set<Competicao> competicoes = new LinkedHashSet<>();

    public Set<Competicao> getCompeticoes() {
        return competicoes;
    }

    public void setCompeticoes(Set<Competicao> competicoes) {
        this.competicoes = competicoes;
    }

    public Gestor(String username, String password, String email) {
        super(username, password, email);
    }

    public Gestor() {
        super();
    }


}