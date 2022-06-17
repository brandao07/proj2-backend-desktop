package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tipo_premio")
public class TipoPremio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "tipoPremio", orphanRemoval = true)
    private Set<Premio> premios = new LinkedHashSet<>();

    public Set<Premio> getPremios() {
        return premios;
    }

    public void setPremios(Set<Premio> premios) {
        this.premios = premios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TipoPremio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}