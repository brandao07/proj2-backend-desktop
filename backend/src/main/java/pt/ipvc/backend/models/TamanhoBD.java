package pt.ipvc.backend.models;

public class TamanhoBD {
    private String tamanho;

    public TamanhoBD(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "TamanhoBD{" +
                "tamanho='" + tamanho + '\'' +
                '}';
    }

}
