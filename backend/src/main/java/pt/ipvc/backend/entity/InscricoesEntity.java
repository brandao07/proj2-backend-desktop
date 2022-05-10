package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "inscricoes", schema = "public", catalog = "postgres")
@NamedQuery(name = "Inscricoes.readById", query = "SELECT a FROM InscricoesEntity a WHERE a.id = ?1")
@NamedQuery(name = "Inscricoes.readAll", query = "SELECT a FROM InscricoesEntity a")
public class InscricoesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "data_inscricao")
    private Date dataInscricao;
    @Basic
    @Column(name = "id_equipa")
    private Integer idEquipa;
    @Basic
    @Column(name = "id_competicao")
    private Integer idCompeticao;
    @Basic
    @Column(name = "id_atleta")
    private Integer idAtleta;
    @ManyToOne
    @JoinColumn(name = "id_equipa", referencedColumnName = "id", insertable = false, updatable = false)
    private EquipasEntity equipasByIdEquipa;
    @ManyToOne
    @JoinColumn(name = "id_competicao", referencedColumnName = "id", insertable = false, updatable = false)
    private CompeticoesEntity competicoesByIdCompeticao;
    @ManyToOne
    @JoinColumn(name = "id_atleta", referencedColumnName = "id", insertable = false, updatable = false)
    private AtletasEntity atletasByIdAtleta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(Date dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Integer getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(Integer idEquipa) {
        this.idEquipa = idEquipa;
    }

    public Integer getIdCompeticao() {
        return idCompeticao;
    }

    public void setIdCompeticao(Integer idCompeticao) {
        this.idCompeticao = idCompeticao;
    }

    public Integer getIdAtleta() {
        return idAtleta;
    }

    public void setIdAtleta(Integer idAtleta) {
        this.idAtleta = idAtleta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InscricoesEntity that = (InscricoesEntity) o;

        if (id != that.id) return false;
        if (dataInscricao != null ? !dataInscricao.equals(that.dataInscricao) : that.dataInscricao != null)
            return false;
        if (idEquipa != null ? !idEquipa.equals(that.idEquipa) : that.idEquipa != null) return false;
        if (idCompeticao != null ? !idCompeticao.equals(that.idCompeticao) : that.idCompeticao != null) return false;
        if (idAtleta != null ? !idAtleta.equals(that.idAtleta) : that.idAtleta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dataInscricao != null ? dataInscricao.hashCode() : 0);
        result = 31 * result + (idEquipa != null ? idEquipa.hashCode() : 0);
        result = 31 * result + (idCompeticao != null ? idCompeticao.hashCode() : 0);
        result = 31 * result + (idAtleta != null ? idAtleta.hashCode() : 0);
        return result;
    }

    public EquipasEntity getEquipasByIdEquipa() {
        return equipasByIdEquipa;
    }

    public void setEquipasByIdEquipa(EquipasEntity equipasByIdEquipa) {
        this.equipasByIdEquipa = equipasByIdEquipa;
    }

    public CompeticoesEntity getCompeticoesByIdCompeticao() {
        return competicoesByIdCompeticao;
    }

    public void setCompeticoesByIdCompeticao(CompeticoesEntity competicoesByIdCompeticao) {
        this.competicoesByIdCompeticao = competicoesByIdCompeticao;
    }

    public AtletasEntity getAtletasByIdAtleta() {
        return atletasByIdAtleta;
    }

    public void setAtletasByIdAtleta(AtletasEntity atletasByIdAtleta) {
        this.atletasByIdAtleta = atletasByIdAtleta;
    }

    @Override
    public String toString() {
        return "InscricoesEntity{" +
                "dataInscricao=" + dataInscricao +
                ", idEquipa=" + idEquipa +
                ", idCompeticao=" + idCompeticao +
                ", idAtleta=" + idAtleta +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(String dataInscricao, int idEquipa, int idCompeticao, int idAtleta) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            InscricoesEntity entity = new InscricoesEntity();
            entity.setDataInscricao(Date.valueOf(dataInscricao));
            entity.setIdEquipa(idEquipa);
            entity.setIdCompeticao(idCompeticao);
            entity.setIdAtleta(idAtleta);
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
            TypedQuery<InscricoesEntity> query = entityManager.createNamedQuery("Inscricoes.readById", InscricoesEntity.class);
            InscricoesEntity inscricao = query.setParameter(1, id).getSingleResult();

            System.out.println(inscricao.toString());
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
            TypedQuery<InscricoesEntity> query = entityManager.createNamedQuery("Inscricoes.readAll", InscricoesEntity.class);
            for(InscricoesEntity inscricao : query.getResultList()){
                System.out.println(inscricao.toString());
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
            TypedQuery<InscricoesEntity> query = entityManager.createNamedQuery("Inscricoes.readById", InscricoesEntity.class);
            InscricoesEntity inscricao = query.setParameter(1, id).getSingleResult();
            entityManager.remove(inscricao);
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
            TypedQuery<InscricoesEntity> query = entityManager.createNamedQuery("Inscricoes.readAll", InscricoesEntity.class);
            for(InscricoesEntity inscricao : query.getResultList()){
                entityManager.remove(inscricao);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, String dataInscricao, int idEquipa, int idCompeticao, int idAtleta){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<InscricoesEntity> query = entityManager.createNamedQuery("Inscricoes.readById", InscricoesEntity.class);
            InscricoesEntity inscricao = query.setParameter(1, id).getSingleResult();
            inscricao.setIdAtleta(idAtleta);
            inscricao.setDataInscricao(Date.valueOf(dataInscricao));
            inscricao.setIdCompeticao(idCompeticao);
            inscricao.setIdEquipa(idEquipa);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
