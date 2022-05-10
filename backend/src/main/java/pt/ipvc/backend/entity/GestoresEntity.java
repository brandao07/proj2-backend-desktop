package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "Gestores.readById", query = "SELECT a FROM GestoresEntity a WHERE a.id = ?1")
@NamedQuery(name = "Gestores.readAll", query = "SELECT a FROM GestoresEntity a")

@Table(name = "gestores", schema = "public", catalog = "postgres")
public class GestoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_utilizador")
    private Integer idUtilizador;
    @OneToMany(mappedBy = "gestoresByIdGestor")
    private Collection<CompeticoesEntity> competicoesById;
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

        GestoresEntity that = (GestoresEntity) o;

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

    public Collection<CompeticoesEntity> getCompeticoesById() {
        return competicoesById;
    }

    public void setCompeticoesById(Collection<CompeticoesEntity> competicoesById) {
        this.competicoesById = competicoesById;
    }

    public UtilizadoresEntity getUtilizadoresByIdUtilizador() {
        return utilizadoresByIdUtilizador;
    }

    public void setUtilizadoresByIdUtilizador(UtilizadoresEntity utilizadoresByIdUtilizador) {
        this.utilizadoresByIdUtilizador = utilizadoresByIdUtilizador;
    }

    @Override
    public String toString() {
        return "GestoresEntity{" +
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
            GestoresEntity entity = new GestoresEntity();
            entity.setIdUtilizador(idUtilizador);
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
            TypedQuery<GestoresEntity> query = entityManager.createNamedQuery("Gestores.readById", GestoresEntity.class);
            GestoresEntity gestores = query.setParameter(1, id).getSingleResult();

            System.out.println(gestores.toString());
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
            TypedQuery<GestoresEntity> query = entityManager.createNamedQuery("Gestores.readAll", GestoresEntity.class);
            for(GestoresEntity gestores : query.getResultList()){
                System.out.println(gestores.toString());
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
            TypedQuery<GestoresEntity> query = entityManager.createNamedQuery("Gestores.readById", GestoresEntity.class);
            GestoresEntity gestores = query.setParameter(1, id).getSingleResult();
            entityManager.remove(gestores);
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
            TypedQuery<GestoresEntity> query = entityManager.createNamedQuery("Gestores.readAll", GestoresEntity.class);
            for(GestoresEntity gestores : query.getResultList()){
                entityManager.remove(gestores);
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
            TypedQuery<GestoresEntity> query = entityManager.createNamedQuery("Gestores.readById", GestoresEntity.class);
            GestoresEntity gestores = query.setParameter(1, id).getSingleResult();
            gestores.setIdUtilizador(idUtilizador);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
