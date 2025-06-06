package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "equipa")
public class Equipa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "clube_id")
    private Clube clube;

    @ManyToOne
    @JoinColumn(name = "modalidade_id")
    private Modalidade modalidade;



    @OneToMany(mappedBy = "equipa", orphanRemoval = true)
    private Set<Atleta> atletas = new LinkedHashSet<>();
    @OneToMany(mappedBy = "equipaCasa", orphanRemoval = true)
    private Set<Prova> provasCasa = new LinkedHashSet<>();
    @OneToMany(mappedBy = "equipaFora", orphanRemoval = true)
    private Set<Prova> provasFora = new LinkedHashSet<>();
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "equipas")
    private Set<Competicao> competicoes = new HashSet<>();


    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    public Equipa() {
    }

    public Equipa(String nome) {
        this.nome = nome;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }



    public Set<Prova> getProvasFora() {
        return provasFora;
    }

    public void setProvasFora(Set<Prova> provasFora) {
        this.provasFora = provasFora;
    }

    public Set<Prova> getProvasCasa() {
        return provasCasa;
    }

    public void setProvasCasa(Set<Prova> provasCasa) {
        this.provasCasa = provasCasa;
    }

    public Set<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(Set<Atleta> atletas) {
        this.atletas = atletas;
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

    public Set<Competicao> getCompeticoes() {
        return competicoes;
    }

    public void setCompeticoes(Set<Competicao> competicoes) {
        this.competicoes = competicoes;
    }


    public void addCompeticao(Competicao competicao) {
        if (competicoes.stream().anyMatch(t -> t.getId().equals(competicao.getId()))) return;
        if (competicoes.add(competicao)) competicao.getEquipas().add(this);
    }

    public void removeCompeticao(Competicao competicao) {
        if (competicoes.remove(competicoes.stream().
                filter(t -> Objects.equals(t.getId(), competicao.getId())).
                findAny().
                orElse(null)))
            competicao.getEquipas().remove(competicao.getEquipas().stream().
                    filter(r -> Objects.equals(r.getId(), this.getId())).
                    findAny().
                    orElse(null));
    }


}