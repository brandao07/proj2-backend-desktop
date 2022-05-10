package pt.ipvc.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "premios", schema = "public", catalog = "postgres")
@NamedQuery(name = "Premios.readById", query = "SELECT a FROM PremiosEntity a WHERE a.idTipoPremio = ?1")
@NamedQuery(name = "Premios.readAll", query = "SELECT a FROM PremiosEntity a")
@IdClass(PremiosEntityPK.class)
public class PremiosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_tipo_premio")
    private int idTipoPremio;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_competicao")
    private int idCompeticao;
    @ManyToOne
    @JoinColumn(name = "id_tipo_premio", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private TipoPremioEntity tipoPremioByIdTipoPremio;
    @ManyToOne
    @JoinColumn(name = "id_competicao", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private CompeticoesEntity competicoesByIdCompeticao;

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

        PremiosEntity that = (PremiosEntity) o;

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

    public TipoPremioEntity getTipoPremioByIdTipoPremio() {
        return tipoPremioByIdTipoPremio;
    }

    public void setTipoPremioByIdTipoPremio(TipoPremioEntity tipoPremioByIdTipoPremio) {
        this.tipoPremioByIdTipoPremio = tipoPremioByIdTipoPremio;
    }

    public CompeticoesEntity getCompeticoesByIdCompeticao() {
        return competicoesByIdCompeticao;
    }

    public void setCompeticoesByIdCompeticao(CompeticoesEntity competicoesByIdCompeticao) {
        this.competicoesByIdCompeticao = competicoesByIdCompeticao;
    }



    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(int idCompeticao, int idTipoPremio) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            PremiosEntity entity = new PremiosEntity();
            entity.setIdCompeticao(idCompeticao);
            entity.setIdTipoPremio(idTipoPremio);
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
            TypedQuery<PremiosEntity> query = entityManager.createNamedQuery("Premios.readById", PremiosEntity.class);
            PremiosEntity premio = query.setParameter(1, id).getSingleResult();

            System.out.println(premio.toString());
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
            TypedQuery<PremiosEntity> query = entityManager.createNamedQuery("Premios.readAll", PremiosEntity.class);
            for(PremiosEntity premio : query.getResultList()){
                System.out.println(premio.toString());
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
            TypedQuery<PremiosEntity> query = entityManager.createNamedQuery("Premios.readById", PremiosEntity.class);
            PremiosEntity premio = query.setParameter(1, id).getSingleResult();
            entityManager.remove(premio);
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
            TypedQuery<PremiosEntity> query = entityManager.createNamedQuery("Premios.readAll", PremiosEntity.class);
            for(PremiosEntity premio : query.getResultList()){
                entityManager.remove(premio);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, int idCompeticao, int idTipoPremio){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<PremiosEntity> query = entityManager.createNamedQuery("Premios.readById", PremiosEntity.class);
            PremiosEntity premio = query.setParameter(1, id).getSingleResult();
            premio.setIdTipoPremio(idTipoPremio);
            premio.setIdCompeticao(idCompeticao);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
