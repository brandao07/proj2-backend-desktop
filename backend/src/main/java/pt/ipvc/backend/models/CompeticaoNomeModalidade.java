package pt.ipvc.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Date;

public class CompeticaoNomeModalidade {

    @Basic
    @Column(name = "nome")
    private String nome;

    @Basic
    @Column(name = "data_inicio")
    private Date dataInicio;

    @Basic
    @Column(name = "data_fim")
    private Date dataFim;

    @Basic
    @Column(name = "genero")
    private String genero;

    @Basic
    @Column(name = "modalidade")
    private String modalidade;

    public CompeticaoNomeModalidade(String nome, Date dataInicio, Date dataFim, String genero, String modalidade) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.genero = genero;
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
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
