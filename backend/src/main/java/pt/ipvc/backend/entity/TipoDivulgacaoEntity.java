package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "TipoDivulgacao.readById", query = "SELECT a FROM TipoDivulgacaoEntity a WHERE a.id = ?1")
@NamedQuery(name = "TipoDivulgacao.readAll", query = "SELECT a FROM TipoDivulgacaoEntity a")
@Table(name = "tipo_divulgacao", schema = "public", catalog = "postgres")
public class TipoDivulgacaoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "tipoDivulgacaoByIdTipoDivulgacao")
    private Collection<DivulgacoesEntity> divulgacoesById;

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

        TipoDivulgacaoEntity that = (TipoDivulgacaoEntity) o;

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

    public Collection<DivulgacoesEntity> getDivulgacoesById() {
        return divulgacoesById;
    }

    public void setDivulgacoesById(Collection<DivulgacoesEntity> divulgacoesById) {
        this.divulgacoesById = divulgacoesById;
    }

    @Override
    public String toString() {
        return "TipoDivulgacaoEntity{" +
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
            TipoDivulgacaoEntity entity = new TipoDivulgacaoEntity();
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
            TypedQuery<TipoDivulgacaoEntity> query = entityManager.createNamedQuery("TipoDivulgacao.readById", TipoDivulgacaoEntity.class);
            TipoDivulgacaoEntity divulgacao = query.setParameter(1, id).getSingleResult();

            System.out.println(divulgacao.toString());
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
            TypedQuery<TipoDivulgacaoEntity> query = entityManager.createNamedQuery("TipoDivulgacao.readAll", TipoDivulgacaoEntity.class);
            for(TipoDivulgacaoEntity divulgacao : query.getResultList()){
                System.out.println(divulgacao.toString());
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
            TypedQuery<TipoDivulgacaoEntity> query = entityManager.createNamedQuery("TipoDivulgacao.readById", TipoDivulgacaoEntity.class);
            TipoDivulgacaoEntity divulgacao = query.setParameter(1, id).getSingleResult();
            entityManager.remove(divulgacao);
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
            TypedQuery<TipoDivulgacaoEntity> query = entityManager.createNamedQuery("TipoDivulgacao.readAll", TipoDivulgacaoEntity.class);
            for(TipoDivulgacaoEntity divulgacao : query.getResultList()){
                entityManager.remove(divulgacao);
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
            TypedQuery<TipoDivulgacaoEntity> query = entityManager.createNamedQuery("TipoDivulgacao.readById", TipoDivulgacaoEntity.class);
            TipoDivulgacaoEntity recinto = query.setParameter(1, id).getSingleResult();
            recinto.setNome(nome);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}
