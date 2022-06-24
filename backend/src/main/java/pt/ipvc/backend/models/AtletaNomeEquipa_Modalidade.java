package pt.ipvc.backend.models;

import pt.ipvc.backend.data.db.entity.Modalidade;

import java.util.Date;

public class AtletaNomeEquipa_Modalidade {
    private String nome;

    private String genero;

    private Date dataNascimento;

    private Double peso;

    private Double altura;

    private String nacionalidade;

    private String posicao;

    private String equipa;

    private String modalidade;

    public AtletaNomeEquipa_Modalidade(String nome, String genero, Date dataNascimento, Double peso, Double altura, String nacionalidade, String posicao, String equipa, String modalidade) {
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.nacionalidade = nacionalidade;
        this.posicao = posicao;
        this.equipa = equipa;
        this.modalidade = modalidade;
    }

    public AtletaNomeEquipa_Modalidade(){}

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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getEquipa() {
        return equipa;
    }

    public void setEquipa(String equipa) {
        this.equipa = equipa;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public String toString(){
        return "AtletaNomeEquipa_Modalidade{"+
                "nome='" + nome + '\'' +
                ",genero='" + genero + '\'' +
                ",dataNascimento='" + dataNascimento +
                ",peso='" + peso + '\'' +
                ",altura='" + altura + '\'' +
                ",nacionalidade='" + nacionalidade + '\'' +
                ",posicao='" + posicao + '\'' +
                ",equipa='" + equipa + '\'' +
                ",modalidade='" + modalidade + '\'' +
    '}';


    }
}
