package pt.ipvc.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class PremiosEntityPK implements Serializable {
    @Column(name = "id_tipo_premio")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoPremio;
    @Column(name = "id_competicao")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompeticao;

    public int getIdTipoPremio() {
        return idTipoPremio;
    }

    public void setIdTipoPremio(int idTipoPremio) {
        this.idTipoPremio = idTipoPremio;
    }

    public int getIdCompeticao() {
        return idCompeticao;
    }

    public void setIdCompeticao(int idCompeticao) {
        this.idCompeticao = idCompeticao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PremiosEntityPK that = (PremiosEntityPK) o;

        if (idTipoPremio != that.idTipoPremio) return false;
        if (idCompeticao != that.idCompeticao) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTipoPremio;
        result = 31 * result + idCompeticao;
        return result;
    }
}
