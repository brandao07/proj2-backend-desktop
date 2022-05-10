package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "TipoPremio.readById", query = "SELECT a FROM TipoPremioEntity a WHERE a.id = ?1")
@NamedQuery(name = "TipoPremio.readAll", query = "SELECT a FROM TipoPremioEntity a")
@Table(name = "tipo_premio", schema = "public", catalog = "postgres")
public class TipoPremioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "tipoPremioByIdTipoPremio")
    private Collection<PremiosEntity> premiosById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoPremioEntity that = (TipoPremioEntity) o;

        if (id != that.id) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }

    public Collection<PremiosEntity> getPremiosById() {
        return premiosById;
    }

    public void setPremiosById(Collection<PremiosEntity> premiosById) {
        this.premiosById = premiosById;
    }

    @Override
    public String toString() {
        return "TipoPremioEntity{" +
                "nome='" + nome + '\'' +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(String nome) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TipoPremioEntity entity = new TipoPremioEntity();
            entity.setNome(nome);
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
            TypedQuery<TipoPremioEntity> query = entityManager.createNamedQuery("TipoPremio.readById", TipoPremioEntity.class);
            TipoPremioEntity premio = query.setParameter(1, id).getSingleResult();

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
            TypedQuery<TipoPremioEntity> query = entityManager.createNamedQuery("TipoPremio.readAll", TipoPremioEntity.class);
            for(TipoPremioEntity premio : query.getResultList()){
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
            TypedQuery<TipoPremioEntity> query = entityManager.createNamedQuery("TipoPremio.readById", TipoPremioEntity.class);
            TipoPremioEntity premio = query.setParameter(1, id).getSingleResult();
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
            TypedQuery<TipoPremioEntity> query = entityManager.createNamedQuery("TipoPremio.readAll", TipoPremioEntity.class);
            for(TipoPremioEntity premio : query.getResultList()){
                entityManager.remove(premio);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, String nome){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<TipoPremioEntity> query = entityManager.createNamedQuery("TipoPremio.readById", TipoPremioEntity.class);
            TipoPremioEntity premio = query.setParameter(1, id).getSingleResult();
            premio.setNome(nome);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
