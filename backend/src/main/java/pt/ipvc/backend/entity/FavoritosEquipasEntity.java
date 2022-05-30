package pt.ipvc.backend.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "FavoritosEquipas.readById", query = "SELECT a FROM FavoritosEquipasEntity a WHERE a.idCliente = ?1")
@NamedQuery(name = "FavoritosEquipas.readAll", query = "SELECT a FROM FavoritosEquipasEntity a")

@Table(name = "favoritos_equipas", schema = "public", catalog = "postgres")
@IdClass(FavoritosEquipasEntityPK.class)
public class FavoritosEquipasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_equipa")
    private int idEquipa;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_cliente")
    private int idCliente;
    @ManyToOne
    @JoinColumn(name = "id_equipa", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private EquipasEntity equipasByIdEquipa;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ClientesEntity clientesByIdCliente;

    public int getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(int idEquipa) {
        this.idEquipa = idEquipa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoritosEquipasEntity that = (FavoritosEquipasEntity) o;

        if (idEquipa != that.idEquipa) return false;
        if (idCliente != that.idCliente) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEquipa;
        result = 31 * result + idCliente;
        return result;
    }

    public EquipasEntity getEquipasByIdEquipa() {
        return equipasByIdEquipa;
    }

    public void setEquipasByIdEquipa(EquipasEntity equipasByIdEquipa) {
        this.equipasByIdEquipa = equipasByIdEquipa;
    }

    public ClientesEntity getClientesByIdCliente() {
        return clientesByIdCliente;
    }

    public void setClientesByIdCliente(ClientesEntity clientesByIdCliente) {
        this.clientesByIdCliente = clientesByIdCliente;
    }

    @Override
    public String toString() {
        return "FavoritosEquipasEntity{" +
                "idEquipa=" + idEquipa +
                ", idCliente=" + idCliente +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(int idCliente, int idEquipa) {
//        EquipasEntity equipa = FavoritosEquipasEntity.getById(5);
//        equipa.addCliente(ClientesEntity.getById(10));


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            FavoritosEquipasEntity entity = new FavoritosEquipasEntity();
            entity.setIdCliente(idCliente);
            entity.setIdEquipa(idEquipa);
            entityManager.persist(entity);
            transaction.commit();
        } finally {
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public void addCliente(ClientesEntity cliente){
        int idCliente = cliente.getId();
        int idEquipa = this.getIdEquipa();
    }

    public static void read(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<FavoritosEquipasEntity> query = entityManager.createNamedQuery("FavoritosEquipas.readById", FavoritosEquipasEntity.class);
            FavoritosEquipasEntity favoritosEquipas = query.setParameter(1, id).getSingleResult();

            System.out.println(favoritosEquipas.toString());
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
            TypedQuery<ProvaArbitroEntity> query = entityManager.createNamedQuery("ProvaArbitros.readAll", ProvaArbitroEntity.class);
            for(ProvaArbitroEntity provaArbitro : query.getResultList()){
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
            TypedQuery<FavoritosEquipasEntity> query = entityManager.createNamedQuery("FavoritosEquipas.readById", FavoritosEquipasEntity.class);
            FavoritosEquipasEntity favoritosEquipas = query.setParameter(1, id).getSingleResult();
            entityManager.remove(favoritosEquipas);
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
            TypedQuery<FavoritosEquipasEntity> query = entityManager.createNamedQuery("FavoritosEquipas.readAll", FavoritosEquipasEntity.class);
            for(FavoritosEquipasEntity favoritosEquipas : query.getResultList()){
                entityManager.remove(favoritosEquipas);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, int idCliente, int idEquipa){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<FavoritosEquipasEntity> query = entityManager.createNamedQuery("FavoritosEquipas.readById", FavoritosEquipasEntity.class);
            FavoritosEquipasEntity favoritosEquipas = query.setParameter(1, id).getSingleResult();
            favoritosEquipas.setIdEquipa(idEquipa);
            favoritosEquipas.setIdCliente(idCliente);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}


