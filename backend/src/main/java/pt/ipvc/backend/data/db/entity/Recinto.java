package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "recinto")
public class Recinto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "capacidade")
    private Long capacidade;

    @Column(name = "pais")
    private String pais;


    @ManyToMany
    private Set<TipoRecinto> tipos = new HashSet<>();

    @OneToMany(mappedBy = "recinto", orphanRemoval = true)
    private Set<Prova> provas = new LinkedHashSet<>();

    public Set<Prova> getProvas() {
        return provas;
    }

    public void setProvas(Set<Prova> provas) {
        this.provas = provas;
    }

    public Set<TipoRecinto> getTipos() {
        return tipos;
    }

    public void setTipos(Set<TipoRecinto> tipos) {
        this.tipos = tipos;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Long capacidade) {
        this.capacidade = capacidade;
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
        return "Recinto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", capacidade=" + capacidade +
                ", pais='" + pais + '\'' +
                '}';
    }

    public void addTipo(TipoRecinto tipo) {
        if (tipos.stream().anyMatch(t -> t.getId().equals(tipo.getId()))) return;
        if (tipos.add(tipo)) tipo.getRecintos().add(this);
    }

    public void removeTipo(TipoRecinto tipo) {
        if (tipos.remove(tipos.stream().
                filter(t -> Objects.equals(t.getId(), tipo.getId())).
                findAny().
                orElse(null)))
            tipo.getRecintos().remove(tipo.getRecintos().stream().
                    filter(r -> Objects.equals(r.getId(), this.getId())).
                    findAny().
                    orElse(null));
    }
}