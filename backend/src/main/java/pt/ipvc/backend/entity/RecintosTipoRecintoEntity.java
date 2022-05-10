package pt.ipvc.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "recintos_tipo_recinto", schema = "public", catalog = "postgres")
@NamedQuery(name = "RecintosTipoRecintos.readById", query = "SELECT a FROM RecintosTipoRecintoEntity a WHERE a.idTipoRecinto = ?1")
@NamedQuery(name = "RecintosTipoRecintos.readAll", query = "SELECT a FROM RecintosTipoRecintoEntity a")


@IdClass(RecintosTipoRecintoEntityPK.class)
public class RecintosTipoRecintoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_recinto")
    private int idRecinto;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo_recinto")
    private int idTipoRecinto;
    @ManyToOne
    @JoinColumn(name = "id_recinto", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private RecintosEntity recintosByIdRecinto;
    @ManyToOne
    @JoinColumn(name = "id_tipo_recinto", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private TipoRecintoEntity tipoRecintoByIdTipoRecinto;

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

        RecintosTipoRecintoEntity that = (RecintosTipoRecintoEntity) o;

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

    public RecintosEntity getRecintosByIdRecinto() {
        return recintosByIdRecinto;
    }

    public void setRecintosByIdRecinto(RecintosEntity recintosByIdRecinto) {
        this.recintosByIdRecinto = recintosByIdRecinto;
    }

    public TipoRecintoEntity getTipoRecintoByIdTipoRecinto() {
        return tipoRecintoByIdTipoRecinto;
    }

    public void setTipoRecintoByIdTipoRecinto(TipoRecintoEntity tipoRecintoByIdTipoRecinto) {
        this.tipoRecintoByIdTipoRecinto = tipoRecintoByIdTipoRecinto;
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */



    public static void create(int idRecinto, int idTipoRecinto) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RecintosTipoRecintoEntity entity = new RecintosTipoRecintoEntity();
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

    public static void read(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<RecintosTipoRecintoEntity> query = entityManager.createNamedQuery("RecintosTipoRecintos.readById", RecintosTipoRecintoEntity.class);
            RecintosTipoRecintoEntity recintosTipoRecinto = query.setParameter(1, id).getSingleResult();

            System.out.println(recintosTipoRecinto.toString());
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void readAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<RecintosTipoRecintoEntity> query = entityManager.createNamedQuery("RecintosTipoRecintos.readAll", RecintosTipoRecintoEntity.class);
            for( RecintosTipoRecintoEntity recintosTipoRecinto : query.getResultList()){
                System.out.println(recintosTipoRecinto.toString());
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void delete(int id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<RecintosTipoRecintoEntity> query = entityManager.createNamedQuery("RecintosTipoRecintos.readById", RecintosTipoRecintoEntity.class);
            RecintosTipoRecintoEntity recintosTipoRecinto = query.setParameter(1, id).getSingleResult();
            entityManager.remove(recintosTipoRecinto);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void deleteAll(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<RecintosTipoRecintoEntity> query = entityManager.createNamedQuery("RecintosTipoRecintos.readAll", RecintosTipoRecintoEntity.class);
            for(RecintosTipoRecintoEntity recintosTipoRecinto : query.getResultList()){
                entityManager.remove(recintosTipoRecinto);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, int idRecinto, int idTipoRecinto){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<RecintosTipoRecintoEntity> query = entityManager.createNamedQuery("RecintosTipoRecintos.readById", RecintosTipoRecintoEntity.class);
            RecintosTipoRecintoEntity recintosTipoRecinto = query.setParameter(1, id).getSingleResult();
            recintosTipoRecinto.setIdTipoRecinto(idRecinto);
            recintosTipoRecinto.setIdRecinto(idTipoRecinto);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
