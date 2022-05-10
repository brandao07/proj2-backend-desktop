package pt.ipvc.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class FavoritosEquipasEntityPK implements Serializable {
    @Column(name = "id_equipa")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEquipa;
    @Column(name = "id_cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCliente;

    public int getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(int idEquipa) {
        this.idEquipa = idEquipa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritosEquipasEntityPK that = (FavoritosEquipasEntityPK) o;

        if (idEquipa != that.idEquipa) return false;
        if (idCliente != that.idCliente) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEquipa;
        result = 31 * result + idCliente;
        return result;
    }
}
