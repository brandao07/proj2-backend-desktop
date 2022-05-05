package pt.ipvc.backend.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "VendaBilhetes.readById", query = "SELECT a FROM VendaBilhetesEntity a WHERE a.id = ?1")
@NamedQuery(name = "VendaBilhetes.readAll", query = "SELECT a FROM VendaBilhetesEntity a")
@Table(name = "venda_bilhetes", schema = "public", catalog = "postgres")
public class VendaBilhetesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "numero_lugar")
    private int numeroLugar;
    @Basic
    @Column(name = "id_prova")
    private Integer idProva;
    @Basic
    @Column(name = "preco")
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "id_prova", referencedColumnName = "id", insertable = false, updatable = false)
    private ProvasEntity provasByIdProva;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroLugar() {
        return numeroLugar;
    }

    public void setNumeroLugar(int numeroLugar) {
        this.numeroLugar = numeroLugar;
    }

    public Integer getIdProva() {
        return idProva;
    }

    public void setIdProva(Integer idProva) {
        this.idProva = idProva;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendaBilhetesEntity that = (VendaBilhetesEntity) o;

        if (id != that.id) return false;
        if (numeroLugar != that.numeroLugar) return false;
        if (idProva != null ? !idProva.equals(that.idProva) : that.idProva != null) return false;
        if (preco != null ? !preco.equals(that.preco) : that.preco != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numeroLugar;
        result = 31 * result + (idProva != null ? idProva.hashCode() : 0);
        result = 31 * result + (preco != null ? preco.hashCode() : 0);
        return result;
    }

    public ProvasEntity getProvasByIdProva() {
        return provasByIdProva;
    }

    public void setProvasByIdProva(ProvasEntity provasByIdProva) {
        this.provasByIdProva = provasByIdProva;
    }

    @Override
    public String toString() {
        return "VendaBilhetesEntity{" +
                "numeroLugar=" + numeroLugar +
                ", idProva=" + idProva +
                ", preco=" + preco +
                ", provasByIdProva=" + provasByIdProva +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(int numeroLugar, int idProva, double preco) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            VendaBilhetesEntity entity = new VendaBilhetesEntity();
            entity.setNumeroLugar(numeroLugar);
            entity.setPreco(preco);
            entity.setIdProva(idProva);
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
            TypedQuery<VendaBilhetesEntity> query = entityManager.createNamedQuery("VendaBilhetes.readById", VendaBilhetesEntity.class);
            VendaBilhetesEntity vendaBilhetes = query.setParameter(1, id).getSingleResult();

            System.out.println(vendaBilhetes.toString());
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
            TypedQuery<VendaBilhetesEntity> query = entityManager.createNamedQuery("VendaBilhetes.readAll", VendaBilhetesEntity.class);
            for(VendaBilhetesEntity vendaBilhetes : query.getResultList()){
                System.out.println(vendaBilhetes.toString());
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
            TypedQuery<VendaBilhetesEntity> query = entityManager.createNamedQuery("VendaBilhetes.readById", VendaBilhetesEntity.class);
            VendaBilhetesEntity vendaBilhetes = query.setParameter(1, id).getSingleResult();
            entityManager.remove(vendaBilhetes);
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
            TypedQuery<VendaBilhetesEntity> query = entityManager.createNamedQuery("VendaBilhetes.readAll", VendaBilhetesEntity.class);
            for(VendaBilhetesEntity vendaBilhetes : query.getResultList()){
                entityManager.remove(vendaBilhetes);
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
        int numeroLugar = 5;
        double preco = 5.99;
        int idProva = 1;

        try {
            transaction.begin();
            TypedQuery<VendaBilhetesEntity> query = entityManager.createNamedQuery("VendaBilhetes.readById", VendaBilhetesEntity.class);
            VendaBilhetesEntity vendaBilhetes = query.setParameter(1, id).getSingleResult();
            vendaBilhetes.setNumeroLugar(numeroLugar);
            vendaBilhetes.setPreco(preco);
            vendaBilhetes.setIdProva(idProva);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
