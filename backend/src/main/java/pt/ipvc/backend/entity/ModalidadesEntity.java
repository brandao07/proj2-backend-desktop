package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "modalidades", schema = "public", catalog = "postgres")
@NamedQuery(name = "Modalidades.readById", query = "SELECT a FROM ModalidadesEntity a WHERE a.id = ?1")
@NamedQuery(name = "Modalidades.readAll", query = "SELECT a FROM ModalidadesEntity a")
public class ModalidadesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "modalidadesByIdModalidade")
    private Collection<CompeticoesEntity> competicoesById;

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

        ModalidadesEntity that = (ModalidadesEntity) o;

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

    public Collection<CompeticoesEntity> getCompeticoesById() {
        return competicoesById;
    }

    public void setCompeticoesById(Collection<CompeticoesEntity> competicoesById) {
        this.competicoesById = competicoesById;
    }

    @Override
    public String toString() {
        return "ModalidadesEntity{" +
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
            ModalidadesEntity entity = new ModalidadesEntity();
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
            TypedQuery<ModalidadesEntity> query = entityManager.createNamedQuery("Modalidades.readById", ModalidadesEntity.class);
            ModalidadesEntity modalida = query.setParameter(1, id).getSingleResult();

            System.out.println(modalida.toString());
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
            TypedQuery<ModalidadesEntity> query = entityManager.createNamedQuery("Modalidades.readAll", ModalidadesEntity.class);
            for(ModalidadesEntity modalidade : query.getResultList()){
                System.out.println(modalidade.toString());
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
            TypedQuery<ModalidadesEntity> query = entityManager.createNamedQuery("Modalidades.readById", ModalidadesEntity.class);
            ModalidadesEntity modalidade = query.setParameter(1, id).getSingleResult();
            entityManager.remove(modalidade);
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
            TypedQuery<ModalidadesEntity> query = entityManager.createNamedQuery("Modalidades.readAll", ModalidadesEntity.class);
            for(ModalidadesEntity modalidade : query.getResultList()){
                entityManager.remove(modalidade);
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
        String nome = "Modalidade de CONAS";

        try {
            transaction.begin();
            TypedQuery<EquipasEntity> query = entityManager.createNamedQuery("Modalidades.readById", EquipasEntity.class);
            EquipasEntity equipa = query.setParameter(1, id).getSingleResult();
            equipa.setNome(nome);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
