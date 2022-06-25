package pt.ipvc.backend.models;

import pt.ipvc.backend.data.db.entity.Competicao;

public class PremioNomeTipoPremio {

    private Long id;

    private Integer lugar;

    private String valor;

    private String tipoPremio;

    private Competicao competicao;

    public PremioNomeTipoPremio(Integer lugar, String valor, String tipoPremio){
        this.lugar = lugar;
        this.valor = valor;
        this.tipoPremio = tipoPremio;
    }

    public PremioNomeTipoPremio(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLugar() {
        return lugar;
    }

    public void setLugar(Integer lugar) {
        this.lugar = lugar;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipoPremio() {
        return tipoPremio;
    }

    public void setTipoPremio(String tipoPremio) {
        this.tipoPremio = tipoPremio;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    public void setCompeticao(Competicao competicao) {
        this.competicao = competicao;
    }

    @Override
    public String toString() {
        return "PremioNomeTipoPremio{" +
                "id='" + id +
                ",lugar='" + lugar +
                ",valor='" + valor + '\'' +
                ",tipoPremio='" + tipoPremio + '\'' +
                ",competicao='" + competicao + '\'' +
                '}';
    }
}
