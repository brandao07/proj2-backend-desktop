package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQuery(name = "Utilizadores.readById", query = "SELECT a FROM UtilizadoresEntity a WHERE a.id = ?1")
@NamedQuery(name = "Utilizadores.readAll", query = "SELECT a FROM UtilizadoresEntity a")
@Table(name = "utilizadores", schema = "public", catalog = "postgres")
public class UtilizadoresEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "utilizadoresByIdUtilizador")
    private Collection<AdministradoresEntity> administradoresById;
    @OneToMany(mappedBy = "utilizadoresByIdUtilizador")
    private Collection<ClientesEntity> clientesById;
    @OneToMany(mappedBy = "utilizadoresByIdUtilizador")
    private Collection<GestoresEntity> gestoresById;
    @Basic
    @Column(name = "password")
    private String password;

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(String username, String password, String email) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            UtilizadoresEntity entity = new UtilizadoresEntity();
            entity.setUsername(username);
            entity.setPassword(password);
            entity.setEmail(email);
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
            TypedQuery<UtilizadoresEntity> query = entityManager.createNamedQuery("Utilizadores.readById", UtilizadoresEntity.class);
            UtilizadoresEntity utilizadores = query.setParameter(1, id).getSingleResult();

            System.out.println(utilizadores.toString());
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static ArrayList<UtilizadoresEntity> readAll() {
        ArrayList<UtilizadoresEntity> info = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UtilizadoresEntity> query = entityManager.createNamedQuery("Utilizadores.readAll", UtilizadoresEntity.class);
            for(UtilizadoresEntity utilizadores : query.getResultList()){
                info.add(utilizadores);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
        return info;
    }

    public static void delete(int id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<UtilizadoresEntity> query = entityManager.createNamedQuery("Utilizadores.readById", UtilizadoresEntity.class);
            UtilizadoresEntity utilizadores = query.setParameter(1, id).getSingleResult();
            entityManager.remove(utilizadores);
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
            TypedQuery<UtilizadoresEntity> query = entityManager.createNamedQuery("Utilizadores.readAll", UtilizadoresEntity.class);
            for(UtilizadoresEntity utilizadores : query.getResultList()){
                entityManager.remove(utilizadores);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, String username, String password, String email){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        try {
            transaction.begin();
            TypedQuery<UtilizadoresEntity> query = entityManager.createNamedQuery("Utilizadores.readById", UtilizadoresEntity.class);
            UtilizadoresEntity utilizadores = query.setParameter(1, id).getSingleResult();
            utilizadores.setEmail(email);
            utilizadores.setUsername(username);
            utilizadores.setPassword(password);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UtilizadoresEntity that = (UtilizadoresEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }



    public Collection<AdministradoresEntity> getAdministradoresById() {
        return administradoresById;
    }

    public void setAdministradoresById(Collection<AdministradoresEntity> administradoresById) {
        this.administradoresById = administradoresById;
    }

    public Collection<ClientesEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(Collection<ClientesEntity> clientesById) {
        this.clientesById = clientesById;
    }

    public Collection<GestoresEntity> getGestoresById() {
        return gestoresById;
    }

    public void setGestoresById(Collection<GestoresEntity> gestoresById) {
        this.gestoresById = gestoresById;
    }

    @Override
    public String toString() {
        return "UtilizadoresEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", administradoresById=" + administradoresById +
                ", clientesById=" + clientesById +
                ", gestoresById=" + gestoresById +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
