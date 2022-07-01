package pt.ipvc.backend.models;

import java.util.Date;

public class ArbitroNomeModalidade {
    private String nome;

    private String genero;

    private Date dataNascimento;

    private String nacionalidade;

    private String naturalidade;

    private String modalidade;

    private long id;


    public ArbitroNomeModalidade(String nome, String genero, Date dataNascimento, String nacionalidade, String naturalidade, String modalidade, long id) {
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
        this.modalidade = modalidade;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArbitroNomeModalidade(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public String toString() {
        return "ArbitroNomeModalidade{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", naturalidade='" + naturalidade + '\'' +
                ", modalidade='" + modalidade + '\'' +
                '}';
    }
}
