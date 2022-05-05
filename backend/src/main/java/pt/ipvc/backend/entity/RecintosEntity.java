package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "recintos", schema = "public", catalog = "postgres")
    @NamedQuery(name = "Recintos.readById", query = "SELECT a FROM RecintosEntity a WHERE a.id = ?1")
@NamedQuery(name = "Recintos.readAll", query = "SELECT a FROM RecintosEntity a")
public class RecintosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "pais")
    private String pais;
    @Basic
    @Column(name = "capacidade")
    private int capacidade;
    @OneToMany(mappedBy = "recintosByIdRecinto")
    private Collection<ProvasEntity> provasById;
    @OneToMany(mappedBy = "recintosByIdRecinto")
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecintosEntity that = (RecintosEntity) o;

        if (id != that.id) return false;
        if (capacidade != that.capacidade) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (pais != null ? !pais.equals(that.pais) : that.pais != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + capacidade;
        return result;
    }

    public Collection<ProvasEntity> getProvasById() {
        return provasById;
    }

    public void setProvasById(Collection<ProvasEntity> provasById) {
        this.provasById = provasById;
    }

    public Collection<RecintosTipoRecintoEntity> getRecintosTipoRecintosById() {
        return recintosTipoRecintosById;
    }

    public void setRecintosTipoRecintosById(Collection<RecintosTipoRecintoEntity> recintosTipoRecintosById) {
        this.recintosTipoRecintosById = recintosTipoRecintosById;
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    @Override
    public String toString() {
        return "RecintosEntity{" +
                "nome='" + nome + '\'' +
                ", pais='" + pais + '\'' +
                ", capacidade=" + capacidade +
                '}';
    }

    public static void create(String nome, int capacidade, String pais) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RecintosEntity entity = new RecintosEntity();
            entity.setNome(nome);
            entity.setCapacidade(capacidade);
            entity.setPais(pais);
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
            TypedQuery<RecintosEntity> query = entityManager.createNamedQuery("Recintos.readById", RecintosEntity.class);
            RecintosEntity recinto = query.setParameter(1, id).getSingleResult();

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
            TypedQuery<RecintosEntity> query = entityManager.createNamedQuery("Recintos.readAll", RecintosEntity.class);
            for(RecintosEntity recinto : query.getResultList()){
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
            TypedQuery<RecintosEntity> query = entityManager.createNamedQuery("Recintos.readById", RecintosEntity.class);
            RecintosEntity recinto = query.setParameter(1, id).getSingleResult();
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
            TypedQuery<RecintosEntity> query = entityManager.createNamedQuery("Recintos.readAll", RecintosEntity.class);
            for(RecintosEntity recinto : query.getResultList()){
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
        String nome = "Recinto Conas";
        String pais = "Pico";
        int capacidade = 100000;

        try {
            transaction.begin();
            TypedQuery<RecintosEntity> query = entityManager.createNamedQuery("Recintos.readById", RecintosEntity.class);
            RecintosEntity recinto = query.setParameter(1, id).getSingleResult();
            recinto.setPais(pais);
            recinto.setNome(nome);
            recinto.setCapacidade(capacidade);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }

    }

}
