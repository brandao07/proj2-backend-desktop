package pt.ipvc.backend.models;

import pt.ipvc.backend.data.db.entity.Modalidade;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class ArbitroNomeModalidade {
    private String nome;

    private String genero;

    private Date dataNascimento;

    private String nacionalidade;

    private String associacao;

    private String categoria;

    private String modalidade;


    public ArbitroNomeModalidade(String nome, String genero, Date dataNascimento, String nacionalidade, String associacao, String categoria, String modalidade) {
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
        this.associacao = associacao;
        this.categoria = categoria;
        this.modalidade = modalidade;
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

    public String getAssociacao() {
        return associacao;
    }

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public String toString(){
        return "ArbitroNomeModalidade{"+
                "nome='" + nome + '\'' +
                ",genero='" + genero + '\'' +
                ",dataNascimento='" + dataNascimento +
                ",nacionalidade='" + nacionalidade + '\'' +
                ",associacao='" + associacao + '\'' +
                ",categoria='" + categoria + '\'' +
                ",modalidade='" + modalidade + '\'' +
    '}';
    }
}
