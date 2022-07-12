package pt.ipvc.backend.models;

public class PosicaoModalidade {

    private String nomeModalidade;

    private String nomePosicao;

    public PosicaoModalidade(String nomeModalidade, String nomePosicao) {
        this.nomeModalidade = nomeModalidade;
        this.nomePosicao = nomePosicao;
    }

    public String getNomeModalidade() {
        return nomeModalidade;
    }

    public void setNomeModalidade(String nomeModalidade) {
        this.nomeModalidade = nomeModalidade;
    }

    public String getNomePosicao() {
        return nomePosicao;
    }

    public void setNomePosicao(String nomePosicao) {
        this.nomePosicao = nomePosicao;
    }

    @Override
    public String toString() {
        return "ArbitroNomeModalidade{" +
                "nomeModalidade='" + nomeModalidade + '\'' +
                ", nomePosicao='" + nomePosicao + '\'' +
                '}';
    }
}
