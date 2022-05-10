package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NamedQuery(name = "Clientes.readById", query = "SELECT a FROM ClientesEntity a WHERE a.id = ?1")
@NamedQuery(name = "Clientes.readAll", query = "SELECT a FROM ClientesEntity a")
@Table(name = "clientes", schema = "public", catalog = "postgres")
public class ClientesEntity {
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
    @OneToMany(mappedBy = "clientesByIdCliente")
    private Collection<FavoritosCompeticoesEntity> favoritosCompeticoesById;
    @OneToMany(mappedBy = "clientesByIdCliente")
    private Collection<FavoritosEquipasEntity> favoritosEquipasById;

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

        ClientesEntity that = (ClientesEntity) o;

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

    public Collection<FavoritosCompeticoesEntity> getFavoritosCompeticoesById() {
        return favoritosCompeticoesById;
    }

    public void setFavoritosCompeticoesById(Collection<FavoritosCompeticoesEntity> favoritosCompeticoesById) {
        this.favoritosCompeticoesById = favoritosCompeticoesById;
    }

    public Collection<FavoritosEquipasEntity> getFavoritosEquipasById() {
        return favoritosEquipasById;
    }

    public void setFavoritosEquipasById(Collection<FavoritosEquipasEntity> favoritosEquipasById) {
        this.favoritosEquipasById = favoritosEquipasById;
    }

    @Override
    public String toString() {
        return "ClientesEntity{" +
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
            ClientesEntity entity = new ClientesEntity();
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
            TypedQuery<ClientesEntity> query = entityManager.createNamedQuery("Clientes.readById", ClientesEntity.class);
            ClientesEntity clientes = query.setParameter(1, id).getSingleResult();

            System.out.println(clientes.toString());
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
            TypedQuery<ClientesEntity> query = entityManager.createNamedQuery("Clientes.readAll", ClientesEntity.class);
            for(ClientesEntity clientes : query.getResultList()){
                System.out.println(clientes.toString());
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
            TypedQuery<ClientesEntity> query = entityManager.createNamedQuery("Clientes.readById", ClientesEntity.class);
            ClientesEntity clientes = query.setParameter(1, id).getSingleResult();
            entityManager.remove(clientes);
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
            TypedQuery<ClientesEntity> query = entityManager.createNamedQuery("Clientes.readAll", ClientesEntity.class);
            for(ClientesEntity clientes : query.getResultList()){
                entityManager.remove(clientes);
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
            TypedQuery<ClientesEntity> query = entityManager.createNamedQuery("Clientes.readById", ClientesEntity.class);
            ClientesEntity clientes = query.setParameter(1, id).getSingleResult();
            clientes.setIdUtilizador(idUtilizador);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

}
