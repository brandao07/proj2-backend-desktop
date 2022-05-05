package pt.ipvc.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "prova_arbitro", schema = "public", catalog = "postgres")
@NamedQuery(name = "ProvaArbitros.readById", query = "SELECT a FROM ProvaArbitroEntity a WHERE a.idArbitro = ?1")
@NamedQuery(name = "ProvaArbitros.readAll", query = "SELECT a FROM ProvaArbitroEntity a")
@IdClass(ProvaArbitroEntityPK.class)
public class ProvaArbitroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_prova")
    private int idProva;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_arbitro")
    private int idArbitro;
    @Basic
    @Column(name = "designacao")
    private String designacao;
    @ManyToOne
    @JoinColumn(name = "id_prova", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ProvasEntity provasByIdProva;
    @ManyToOne
    @JoinColumn(name = "id_arbitro", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ArbitrosEntity arbitrosByIdArbitro;

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

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvaArbitroEntity that = (ProvaArbitroEntity) o;

        if (idProva != that.idProva) return false;
        if (idArbitro != that.idArbitro) return false;
        if (designacao != null ? !designacao.equals(that.designacao) : that.designacao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProva;
        result = 31 * result + idArbitro;
        result = 31 * result + (designacao != null ? designacao.hashCode() : 0);
        return result;
    }

    public ProvasEntity getProvasByIdProva() {
        return provasByIdProva;
    }

    public void setProvasByIdProva(ProvasEntity provasByIdProva) {
        this.provasByIdProva = provasByIdProva;
    }

    public ArbitrosEntity getArbitrosByIdArbitro() {
        return arbitrosByIdArbitro;
    }

    public void setArbitrosByIdArbitro(ArbitrosEntity arbitrosByIdArbitro) {
        this.arbitrosByIdArbitro = arbitrosByIdArbitro;
    }

    @Override
    public String toString() {
        return "ProvaArbitroEntity{" +
                "designacao='" + designacao + '\'' +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(int idProva, int idArbitro, String designacao) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ProvaArbitroEntity entity = new ProvaArbitroEntity();
            entity.setIdArbitro(idArbitro);
            entity.setIdProva(idProva);
            entity.setDesignacao(designacao);
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
            TypedQuery<ProvaArbitroEntity> query = entityManager.createNamedQuery("ProvaArbitros.readById", ProvaArbitroEntity.class);
            ProvaArbitroEntity provaArbitro = query.setParameter(1, id).getSingleResult();

            System.out.println(provaArbitro.toString());
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
            TypedQuery<ProvaArbitroEntity> query = entityManager.createNamedQuery("ProvaArbitros.readAll", ProvaArbitroEntity.class);
            for(ProvaArbitroEntity provaArbitro : query.getResultList()){
                System.out.println(provaArbitro.toString());
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
            TypedQuery<ProvaArbitroEntity> query = entityManager.createNamedQuery("ProvaArbitros.readById", ProvaArbitroEntity.class);
            ProvaArbitroEntity provaArbitro = query.setParameter(1, id).getSingleResult();
            entityManager.remove(provaArbitro);
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
            TypedQuery<ProvaArbitroEntity> query = entityManager.createNamedQuery("ProvaArbitros.readAll", ProvaArbitroEntity.class);
            for(ProvaArbitroEntity provaArbitro : query.getResultList()){
                entityManager.remove(provaArbitro);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        int idProva = 1;
        int idArbitro = 1;
        String designacao = "Designação CONA";

        try {
            transaction.begin();
            TypedQuery<ProvaArbitroEntity> query = entityManager.createNamedQuery("ProvaArbitros.readById", ProvaArbitroEntity.class);
            ProvaArbitroEntity provaArbitro = query.setParameter(1, id).getSingleResult();
            provaArbitro.setDesignacao(designacao);
            provaArbitro.setIdProva(idProva);
            provaArbitro.setIdArbitro(idArbitro);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
