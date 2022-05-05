package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "TipoRecinto.readById", query = "SELECT a FROM TipoRecintoEntity a WHERE a.id = ?1")
@NamedQuery(name = "TipoRecinto.readAll", query = "SELECT a FROM TipoRecintoEntity a")
@Table(name = "tipo_recinto", schema = "public", catalog = "postgres")
public class TipoRecintoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "tipoRecintoByIdTipoRecinto")
    private Collection<RecintosTipoRecintoEntity> recintosTipoRecintosById;

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

        TipoRecintoEntity that = (TipoRecintoEntity) o;

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

    public Collection<RecintosTipoRecintoEntity> getRecintosTipoRecintosById() {
        return recintosTipoRecintosById;
    }

    public void setRecintosTipoRecintosById(Collection<RecintosTipoRecintoEntity> recintosTipoRecintosById) {
        this.recintosTipoRecintosById = recintosTipoRecintosById;
    }

    @Override
    public String toString() {
        return "TipoRecintoEntity{" +
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
            TipoRecintoEntity entity = new TipoRecintoEntity();
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
            TypedQuery<TipoRecintoEntity> query = entityManager.createNamedQuery("TipoRecinto.readById", TipoRecintoEntity.class);
            TipoRecintoEntity recinto = query.setParameter(1, id).getSingleResult();

            System.out.println(recinto.toString());
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
            TypedQuery<TipoRecintoEntity> query = entityManager.createNamedQuery("TipoRecinto.readAll", TipoRecintoEntity.class);
            for(TipoRecintoEntity recinto : query.getResultList()){
                System.out.println(recinto.toString());
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
            TypedQuery<TipoRecintoEntity> query = entityManager.createNamedQuery("TipoRecinto.readById", TipoRecintoEntity.class);
            TipoRecintoEntity recinto = query.setParameter(1, id).getSingleResult();
            entityManager.remove(recinto);
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
            TypedQuery<TipoRecintoEntity> query = entityManager.createNamedQuery("TipoRecinto.readAll", TipoRecintoEntity.class);
            for(TipoRecintoEntity recinto : query.getResultList()){
                entityManager.remove(recinto);
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
        String nome = "Teste";

        try {
            transaction.begin();
            TypedQuery<TipoRecintoEntity> query = entityManager.createNamedQuery("TipoRecinto.readById", TipoRecintoEntity.class);
            TipoRecintoEntity recinto = query.setParameter(1, id).getSingleResult();
            recinto.setNome(nome);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}
