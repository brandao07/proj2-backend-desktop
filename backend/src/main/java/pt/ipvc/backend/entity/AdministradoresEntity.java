package pt.ipvc.backend.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Administradores.readById", query = "SELECT a FROM AdministradoresEntity a WHERE a.id = ?1")
@NamedQuery(name = "Administradores.readAll", query = "SELECT a FROM AdministradoresEntity a")
@NamedQuery(name="JOINQUERY", query="SELECT a FROM AdministradoresEntity a INNER JOIN UtilizadoresEntity u ON a.idUtilizador = ?1 and a.idUtilizador = u.id")


@Table(name = "administradores", schema = "public", catalog = "postgres")
public class AdministradoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_utilizador")
    private Integer idUtilizador;
    @ManyToOne
    @JoinColumn(name = "id_utilizador", referencedColumnName = "id", insertable = false, updatable = false)
    private UtilizadoresEntity utilizadoresByIdUtilizador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(Integer idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministradoresEntity that = (AdministradoresEntity) o;

        if (id != that.id) return false;
        if (idUtilizador != null ? !idUtilizador.equals(that.idUtilizador) : that.idUtilizador != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idUtilizador != null ? idUtilizador.hashCode() : 0);
        return result;
    }

    public UtilizadoresEntity getUtilizadoresByIdUtilizador() {
        return utilizadoresByIdUtilizador;
    }

    public void setUtilizadoresByIdUtilizador(UtilizadoresEntity utilizadoresByIdUtilizador) {
        this.utilizadoresByIdUtilizador = utilizadoresByIdUtilizador;
    }

    @Override
    public String toString() {
        return "AdministradoresEntity{" +
                "id=" + id +
                ", idUtilizador=" + idUtilizador +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(int idUtilizador) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AdministradoresEntity entity = new AdministradoresEntity();
            entity.setIdUtilizador(idUtilizador);
            entityManager.persist(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static int read(int id) {
        int id_return;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<AdministradoresEntity> query = entityManager.createNamedQuery("Administradores.readById", AdministradoresEntity.class);
            AdministradoresEntity administradores = query.setParameter(1, id).getSingleResult();
            id_return = administradores.getIdUtilizador();
            System.out.println(administradores.toString());
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }

        return id_return;
    }

    public static boolean join(int id) {
        int id_return = -1;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<AdministradoresEntity> query = entityManager.createNamedQuery("JOINQUERY", AdministradoresEntity.class);
            AdministradoresEntity administradores = query.setParameter(1, id).getSingleResult();
            id_return = administradores.getId();
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        if (id_return >= 0){
            return true;
        }

        return false;
    }

    public static void readAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<AdministradoresEntity> query = entityManager.createNamedQuery("Administradores.readAll", AdministradoresEntity.class);
            for(AdministradoresEntity administradores : query.getResultList()){
                System.out.println(administradores.toString());
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
            TypedQuery<AdministradoresEntity> query = entityManager.createNamedQuery("Administradores.readById", AdministradoresEntity.class);
            AdministradoresEntity administradores = query.setParameter(1, id).getSingleResult();
            entityManager.remove(administradores);
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
            TypedQuery<AdministradoresEntity> query = entityManager.createNamedQuery("Administradores.readAll", AdministradoresEntity.class);
            for(AdministradoresEntity administradores : query.getResultList()){
                entityManager.remove(administradores);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, int idUtilizador){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        try {
            transaction.begin();
            TypedQuery<AdministradoresEntity> query = entityManager.createNamedQuery("Administradores.readById", AdministradoresEntity.class);
            AdministradoresEntity administradores = query.setParameter(1, id).getSingleResult();
            administradores.setIdUtilizador(idUtilizador);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }


}
