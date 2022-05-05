package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "contratos", schema = "public", catalog = "postgres")
@NamedQuery(name = "Contratos.readById", query = "SELECT a FROM ContratosEntity a WHERE a.id = ?1")
@NamedQuery(name = "Contratos.readAll", query = "SELECT a FROM ContratosEntity a")

public class ContratosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "data_inicio")
    private Date dataInicio;
    @Basic
    @Column(name = "data_fim")
    private Date dataFim;
    @Basic
    @Column(name = "id_atleta")
    private Integer idAtleta;
    @Basic
    @Column(name = "id_equipa")
    private Integer idEquipa;
    @ManyToOne
    @JoinColumn(name = "id_atleta", referencedColumnName = "id", insertable = false, updatable = false)
    private AtletasEntity atletasByIdAtleta;
    @ManyToOne
    @JoinColumn(name = "id_equipa", referencedColumnName = "id", insertable = false, updatable = false)
    private EquipasEntity equipasByIdEquipa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getIdAtleta() {
        return idAtleta;
    }

    public void setIdAtleta(Integer idAtleta) {
        this.idAtleta = idAtleta;
    }

    public Integer getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(Integer idEquipa) {
        this.idEquipa = idEquipa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContratosEntity that = (ContratosEntity) o;

        if (id != that.id) return false;
        if (dataInicio != null ? !dataInicio.equals(that.dataInicio) : that.dataInicio != null) return false;
        if (dataFim != null ? !dataFim.equals(that.dataFim) : that.dataFim != null) return false;
        if (idAtleta != null ? !idAtleta.equals(that.idAtleta) : that.idAtleta != null) return false;
        if (idEquipa != null ? !idEquipa.equals(that.idEquipa) : that.idEquipa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dataInicio != null ? dataInicio.hashCode() : 0);
        result = 31 * result + (dataFim != null ? dataFim.hashCode() : 0);
        result = 31 * result + (idAtleta != null ? idAtleta.hashCode() : 0);
        result = 31 * result + (idEquipa != null ? idEquipa.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContratosEntity{" +
                "dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", idAtleta=" + idAtleta +
                ", idEquipa=" + idEquipa +
                ", atletasByIdAtleta=" + atletasByIdAtleta +
                ", equipasByIdEquipa=" + equipasByIdEquipa +
                '}';
    }

    public AtletasEntity getAtletasByIdAtleta() {
        return atletasByIdAtleta;
    }

    public void setAtletasByIdAtleta(AtletasEntity atletasByIdAtleta) {
        this.atletasByIdAtleta = atletasByIdAtleta;
    }

    public EquipasEntity getEquipasByIdEquipa() {
        return equipasByIdEquipa;
    }

    public void setEquipasByIdEquipa(EquipasEntity equipasByIdEquipa) {
        this.equipasByIdEquipa = equipasByIdEquipa;
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(String dataInicio, String dataFim, int idAtleta, int idEquipa) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ContratosEntity entity = new ContratosEntity();
            entity.setDataInicio(Date.valueOf(dataInicio));
            entity.setDataFim(Date.valueOf(dataFim));
            entity.setIdAtleta(idAtleta);
            entity.setIdEquipa(idEquipa);
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
            TypedQuery<ContratosEntity> query = entityManager.createNamedQuery("Contratos.readById", ContratosEntity.class);
            ContratosEntity contrato = query.setParameter(1, id).getSingleResult();

            System.out.println(contrato.toString());
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
            TypedQuery<ContratosEntity> query = entityManager.createNamedQuery("Contratos.readAll", ContratosEntity.class);
            for(ContratosEntity contrato : query.getResultList()){
                System.out.println(contrato.toString());
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
            TypedQuery<ContratosEntity> query = entityManager.createNamedQuery("Contratos.readById", ContratosEntity.class);
            ContratosEntity contrato = query.setParameter(1, id).getSingleResult();
            entityManager.remove(contrato);
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
            TypedQuery<ContratosEntity> query = entityManager.createNamedQuery("Contratos.readAll", ContratosEntity.class);
            for(ContratosEntity contrato : query.getResultList()){
                entityManager.remove(contrato);
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
        String dataInicio = "2000-01-01";
        String dataFim = "2000-01-01";
        int idAtleta = 1;
        int idEquipa = 1;

        try {
            transaction.begin();
            TypedQuery<ContratosEntity> query = entityManager.createNamedQuery("Contratos.readById", ContratosEntity.class);
            ContratosEntity contrato = query.setParameter(1, id).getSingleResult();
            contrato.setIdEquipa(idEquipa);
            contrato.setIdAtleta(idAtleta);
            contrato.setDataInicio(Date.valueOf(dataInicio));
            contrato.setDataFim(Date.valueOf(dataFim));

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
