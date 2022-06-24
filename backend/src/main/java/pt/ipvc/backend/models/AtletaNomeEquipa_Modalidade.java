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
