package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "arbitro")
public class Arbitro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "genero")
    private String genero;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "naturalidade")
    private String naturalidade;


    @ManyToOne
    @JoinColumn(name = "modalidade_id")
    private Modalidade modalidade;

    @OneToMany(mappedBy = "arbitro", orphanRemoval = true)
    private Set<Prova> provas = new LinkedHashSet<>();

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Arbitro() {
    }

    public Arbitro(String nome, String genero, Date dataNascimento, String nacionalidade, String naturalidade) {
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
        return "Arbitro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", modalidade=" + modalidade +
                '}';
    }
}