package pt.ipvc.backend.data.db.entity;

import pt.ipvc.backend.data.db.entity.users.Gestor;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "competicao")
public class Competicao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "genero")
    private String genero;

    @ManyToOne
    @JoinColumn(name = "modalidade_id")
    private Modalidade modalidade;

    @ManyToMany
    private Set<Equipa> equipas = new HashSet<>();


    @OneToMany(mappedBy = "competicao", orphanRemoval = true)
    private Set<Prova> provas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "competicao", orphanRemoval = true)
    private Set<Premio> premios = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "gestor_id")
    private Gestor gestor;

    public Competicao() {
    }

    public Competicao(String nome, Date dataInicio, Date dataFim, String genero, Modalidade modalidade) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.genero = genero;
        this.modalidade = modalidade;
    }

    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }

    public Set<Premio> getPremios() {
        return premios;
    }

    public void setPremios(Set<Premio> premios) {
        this.premios = premios;
    }

    public Set<Prova> getProvas() {
        return provas;
    }

    public void setProvas(Set<Prova> provas) {
        this.provas = provas;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
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

    public Set<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Set<Equipa> equipas) {
        this.equipas = equipas;
    }

    @Override
    public String toString() {
        return "Competicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", genero='" + genero + '\'' +
                ", modalidade=" + modalidade +
                '}';
    }

    public void addEquipa(Equipa equipa) {
        if (equipas.stream().anyMatch(t -> t.getId().equals(equipa.getId()))) return;
        if (equipas.add(equipa)) equipa.getCompeticoes().add(this);
    }

    public void removeEquipa(Equipa equipa) {
        if (equipas.remove(equipas.stream().
                filter(t -> Objects.equals(t.getId(), equipa.getId())).
                findAny().
                orElse(null)))
            equipa.getCompeticoes().remove(equipa.getCompeticoes().stream().
                    filter(r -> Objects.equals(r.getId(), this.getId())).
                    findAny().
                    orElse(null));
    }
}
