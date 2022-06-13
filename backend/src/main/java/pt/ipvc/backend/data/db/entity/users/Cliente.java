package pt.ipvc.backend.data.db.entity.users;

import pt.ipvc.backend.data.db.entity.Competicao;
import pt.ipvc.backend.data.db.entity.Equipa;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Cliente extends Utilizador {
    @ManyToMany
    @JoinTable(name = "favoritos_cliente_equipas",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "equipa_id"))
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "favoritos_cliente_competicoes",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "competicao_id"))
    private Set<Competicao> competicoes = new LinkedHashSet<>();


    public Cliente(String username, String password, String email) {
        super(username, password, email);
    }

    public Cliente() {
        super();
    }

    public Set<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Set<Equipa> equipas) {
        this.equipas = equipas;
    }

    public Set<Competicao> getCompeticoes() {
        return competicoes;
    }

    public void setCompeticoes(Set<Competicao> competicoes) {
        this.competicoes = competicoes;
    }

}