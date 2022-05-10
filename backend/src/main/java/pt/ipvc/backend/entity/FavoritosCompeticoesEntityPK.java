package pt.ipvc.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class FavoritosCompeticoesEntityPK implements Serializable {
    @Column(name = "id_cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;
    @Column(name = "id_competicoes")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompeticoes;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCompeticoes() {
        return idCompeticoes;
    }

    public void setIdCompeticoes(int idCompeticoes) {
        this.idCompeticoes = idCompeticoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritosCompeticoesEntityPK that = (FavoritosCompeticoesEntityPK) o;

        if (idCliente != that.idCliente) return false;
        if (idCompeticoes != that.idCompeticoes) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente;
        result = 31 * result + idCompeticoes;
        return result;
    }
}
