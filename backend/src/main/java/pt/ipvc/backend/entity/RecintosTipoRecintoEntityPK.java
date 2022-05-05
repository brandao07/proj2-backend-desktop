package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.io.Serializable;

public class RecintosTipoRecintoEntityPK implements Serializable {
    @Column(name = "id_recinto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecinto;
    @Column(name = "id_tipo_recinto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoRecinto;

    public int getIdRecinto() {
        return idRecinto;
    }

    public void setIdRecinto(int idRecinto) {
        this.idRecinto = idRecinto;
    }

    public int getIdTipoRecinto() {
        return idTipoRecinto;
    }

    public void setIdTipoRecinto(int idTipoRecinto) {
        this.idTipoRecinto = idTipoRecinto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecintosTipoRecintoEntityPK that = (RecintosTipoRecintoEntityPK) o;

        if (idRecinto != that.idRecinto) return false;
        if (idTipoRecinto != that.idTipoRecinto) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRecinto;
        result = 31 * result + idTipoRecinto;
        return result;
    }

    public static void create(int idRecinto, int idTipoRecinto) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RecintosTipoRecintoEntityPK entity = new RecintosTipoRecintoEntityPK();
            entity.setIdTipoRecinto(idTipoRecinto);
            entity.setIdRecinto(idRecinto);
            entityManager.persist(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }


    }
}
