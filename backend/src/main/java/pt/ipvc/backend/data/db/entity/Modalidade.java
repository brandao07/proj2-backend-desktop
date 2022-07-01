package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "modalidade")
public class Modalidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;


    @OneToMany(mappedBy = "modalidade", orphanRemoval = true)
    private Set<Arbitro> arbitros = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modalidade", orphanRemoval = true)
    private Set<Competicao> competicoes = new LinkedHashSet<>();


    @ManyToMany(mappedBy = "modalidades")
    private Set<Equipa> equipas = new LinkedHashSet<>();

    @ManyToMany
    private Set<Posicao> posicoes = new HashSet<>();

    public Set<Posicao> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(Set<Posicao> posicoes) {
        this.posicoes = posicoes;
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

    public Set<Arbitro> getArbitros() {
        return arbitros;
    }

    public void setArbitros(Set<Arbitro> arbitros) {
        this.arbitros = arbitros;
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

}