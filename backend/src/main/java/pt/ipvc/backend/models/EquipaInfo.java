package pt.ipvc.backend.models;

public class EquipaInfo {

    private long id;

    private String nome;

    private String clube;

    private String modalidade;

    private long n_jogadores;

    public EquipaInfo(String nome, String clube, String modalidade, long n_jogadores) {
        this.nome = nome;
        this.clube = clube;
        this.modalidade = modalidade;
        this.n_jogadores = n_jogadores;
    }

    public EquipaInfo(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public long getN_jogadores() {
        return n_jogadores;
    }

    public void setN_jogadores(long n_jogadores) {
        this.n_jogadores = n_jogadores;
    }

    @Override
    public String toString() {
        return "EquipaInfo{" +
                "nome='" + nome + '\'' +
                ", clube='" + clube + '\'' +
                ", modalidade=" + modalidade +
                ", n_jogadores='" + n_jogadores + '\'' +
                '}';
    }
}
