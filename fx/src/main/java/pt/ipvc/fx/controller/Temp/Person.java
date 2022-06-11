package pt.ipvc.fx.controller.Temp;

import java.util.Date;

public class Person {
    private String nome;
    private String inicio;
    private String fim;
    private String genero;
    private String modalidade;

    public Person(){
        this.nome = "";
        this.inicio = "";
        this.fim = "";
        this.genero = "";
        this.modalidade = "";
    }

    public Person (String nome, String inicio, String fim, String genero, String modalidade){
        this.nome = nome;
        this.inicio = inicio;
        this.fim = fim;
        this.genero = genero;
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
}
