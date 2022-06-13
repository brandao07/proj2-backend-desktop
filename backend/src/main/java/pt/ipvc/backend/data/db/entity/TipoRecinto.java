package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tipo_recinto")
public class TipoRecinto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "tipos")
    private Set<Recinto> recintos = new HashSet<>();

    public Set<Recinto> getRecintos() {
        return recintos;
    }

    public void setRecintos(Set<Recinto> recintos) {
        this.recintos = recintos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TipoRecinto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public void addRecinto(Recinto recinto) {
        if (recintos.stream().anyMatch(r -> r.getId().equals(recinto.getId()))) return;
        if (recintos.add(recinto)) recinto.getTipos().add(this);
    }

    public void removeRecinto(Recinto recinto) {
        if (recintos.remove(recintos.stream().
                filter(r -> Objects.equals(r.getId(), recinto.getId()))
                .findAny()
                .orElse(null)))
            recinto.getTipos().remove(recinto.getTipos().stream().
                    filter(t -> Objects.equals(t.getId(), this.getId())).
                    findAny().
                    orElse(null));
    }
}