package pt.ipvc.backend.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ProvaArbitroEntityPK implements Serializable {
    @Column(name = "id_prova")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProva;
    @Column(name = "id_arbitro")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArbitro;

    public int getIdProva() {
        return idProva;
    }

    public void setIdProva(int idProva) {
        this.idProva = idProva;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvaArbitroEntityPK that = (ProvaArbitroEntityPK) o;

        if (idProva != that.idProva) return false;
        if (idArbitro != that.idArbitro) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProva;
        result = 31 * result + idArbitro;
        return result;
    }
}
