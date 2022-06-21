package pt.ipvc.backend.models;

import javax.persistence.Basic;
import javax.persistence.Column;

public class NomeTipoPremio {

    @Basic
    @Column(name = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NomeTipoPremio(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "NomeTipoPremio{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
