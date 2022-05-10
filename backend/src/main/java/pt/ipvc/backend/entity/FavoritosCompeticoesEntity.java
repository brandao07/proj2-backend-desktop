package pt.ipvc.backend.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "FavoritosCompeticoes.readById", query = "SELECT a FROM FavoritosCompeticoesEntity a WHERE a.idCliente = ?1")
@NamedQuery(name = "FavoritosCompeticoes.readAll", query = "SELECT a FROM FavoritosCompeticoesEntity a")

@Table(name = "favoritos_competicoes", schema = "public", catalog = "postgres")
@IdClass(FavoritosCompeticoesEntityPK.class)
public class FavoritosCompeticoesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente")
    private int idCliente;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_competicoes")
    private int idCompeticoes;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ClientesEntity clientesByIdCliente;
    @ManyToOne
    @JoinColumn(name = "id_competicoes", referencedColumnName = "id", nullable = false , insertable = false, updatable = false)
    private CompeticoesEntity competicoesByIdCompeticoes;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCompeticoes() {
        return idCompeticoes;
    }

    public void setIdCompeticoes(int idCompeticoes) {
        this.idCompeticoes = idCompeticoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritosCompeticoesEntity that = (FavoritosCompeticoesEntity) o;

        if (idCliente != that.idCliente) return false;
        if (idCompeticoes != that.idCompeticoes) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCliente;
        result = 31 * result + idCompeticoes;
        return result;
    }

    public ClientesEntity getClientesByIdCliente() {
        return clientesByIdCliente;
    }

    public void setClientesByIdCliente(ClientesEntity clientesByIdCliente) {
        this.clientesByIdCliente = clientesByIdCliente;
    }

    public CompeticoesEntity getCompeticoesByIdCompeticoes() {
        return competicoesByIdCompeticoes;
    }

    public void setCompeticoesByIdCompeticoes(CompeticoesEntity competicoesByIdCompeticoes) {
        this.competicoesByIdCompeticoes = competicoesByIdCompeticoes;
    }

    @Override
    public String toString() {
        return "FavoritosCompeticoesEntity{" +
                "idCliente=" + idCliente +
                ", idCompeticoes=" + idCompeticoes +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(int idCliente, int idCompeticao) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            FavoritosCompeticoesEntity entity = new FavoritosCompeticoesEntity();
            entity.setIdCliente(idCliente);
            entity.setIdCompeticoes(idCompeticao);
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
            TypedQuery<FavoritosCompeticoesEntity> query = entityManager.createNamedQuery("FavoritosCompeticoes.readById", FavoritosCompeticoesEntity.class);
            FavoritosCompeticoesEntity favoritosCompeticoes = query.setParameter(1, id).getSingleResult();

            System.out.println(favoritosCompeticoes.toString());
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
            TypedQuery<FavoritosCompeticoesEntity> query = entityManager.createNamedQuery("FavoritosCompeticoes.readAll", FavoritosCompeticoesEntity.class);
            for(FavoritosCompeticoesEntity provaArbitro : query.getResultList()){
                System.out.println(provaArbitro.toString());
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
            TypedQuery<FavoritosCompeticoesEntity> query = entityManager.createNamedQuery("FavoritosCompeticoes.readById", FavoritosCompeticoesEntity.class);
            FavoritosCompeticoesEntity favoritosCompeticoes = query.setParameter(1, id).getSingleResult();
            entityManager.remove(favoritosCompeticoes);
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
            TypedQuery<FavoritosCompeticoesEntity> query = entityManager.createNamedQuery("FavoritosCompeticoes.readAll", FavoritosCompeticoesEntity.class);
            for(FavoritosCompeticoesEntity favoritosCompeticoes : query.getResultList()){
                entityManager.remove(favoritosCompeticoes);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, int idCliente, int idCompeticao){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<FavoritosCompeticoesEntity> query = entityManager.createNamedQuery("FavoritosCompeticoes.readById", FavoritosCompeticoesEntity.class);
            FavoritosCompeticoesEntity favoritosCompeticoes = query.setParameter(1, id).getSingleResult();
            favoritosCompeticoes.setIdCompeticoes(idCompeticao);
            favoritosCompeticoes.setIdCliente(idCliente);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
