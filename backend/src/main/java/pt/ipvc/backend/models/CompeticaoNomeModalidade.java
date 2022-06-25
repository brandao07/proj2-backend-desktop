package pt.ipvc.backend.models;


import java.util.Date;

public class CompeticaoNomeModalidade {

    private Long id;

    private String nome;

    private Date dataInicio;


    private Date dataFim;


    private String genero;


    private String modalidade;

    public CompeticaoNomeModalidade(String nome, Date dataInicio, Date dataFim, String genero, String modalidade, Long id) {
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.genero = genero;
        this.modalidade = modalidade;
        this.id = id;
    }

    public CompeticaoNomeModalidade() {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CompeticaoNomeModalidade{" +
                "nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", genero='" + genero + '\'' +
                ", modalidade='" + modalidade + '\'' +
                ", id=" + id +
                '}';
    }
}
