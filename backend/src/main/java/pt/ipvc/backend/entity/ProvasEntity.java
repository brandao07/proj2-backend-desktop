package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@NamedQuery(name = "Provas.readById", query = "SELECT a FROM ProvasEntity a WHERE a.id = ?1")
@NamedQuery(name = "Provas.readAll", query = "SELECT a FROM ProvasEntity a")
@Table(name = "provas", schema = "public", catalog = "postgres")
public class ProvasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "data_prova")
    private Date dataProva;
    @Basic
    @Column(name = "id_equipa_casa")
    private Integer idEquipaCasa;
    @Basic
    @Column(name = "id_equipa_fora")
    private Integer idEquipaFora;
    @Basic
    @Column(name = "id_recinto")
    private Integer idRecinto;
    @Basic
    @Column(name = "id_competicao")
    private Integer idCompeticao;
    @OneToMany(mappedBy = "provasByIdProva")
    private Collection<ProvaArbitroEntity> provaArbitrosById;
    @ManyToOne
    @JoinColumn(name = "id_equipa_casa", referencedColumnName = "id", insertable = false, updatable = false)
    private EquipasEntity equipasByIdEquipaCasa;
    @ManyToOne
    @JoinColumn(name = "id_equipa_fora", referencedColumnName = "id", insertable = false, updatable = false)
    private EquipasEntity equipasByIdEquipaFora;
    @ManyToOne
    @JoinColumn(name = "id_recinto", referencedColumnName = "id", insertable = false, updatable = false)
    private RecintosEntity recintosByIdRecinto;
    @ManyToOne
    @JoinColumn(name = "id_competicao", referencedColumnName = "id", insertable = false, updatable = false)
    private CompeticoesEntity competicoesByIdCompeticao;
    @OneToMany(mappedBy = "provasByIdProva")
    private Collection<VendaBilhetesEntity> vendaBilhetesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataProva() {
        return dataProva;
    }

    public void setDataProva(Date dataProva) {
        this.dataProva = dataProva;
    }

    public Integer getIdEquipaCasa() {
        return idEquipaCasa;
    }

    public void setIdEquipaCasa(Integer idEquipaCasa) {
        this.idEquipaCasa = idEquipaCasa;
    }

    public Integer getIdEquipaFora() {
        return idEquipaFora;
    }

    public void setIdEquipaFora(Integer idEquipaFora) {
        this.idEquipaFora = idEquipaFora;
    }

    public Integer getIdRecinto() {
        return idRecinto;
    }

    public void setIdRecinto(Integer idRecinto) {
        this.idRecinto = idRecinto;
    }

    public Integer getIdCompeticao() {
        return idCompeticao;
    }

    public void setIdCompeticao(Integer idCompeticao) {
        this.idCompeticao = idCompeticao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvasEntity that = (ProvasEntity) o;

        if (id != that.id) return false;
        if (dataProva != null ? !dataProva.equals(that.dataProva) : that.dataProva != null) return false;
        if (idEquipaCasa != null ? !idEquipaCasa.equals(that.idEquipaCasa) : that.idEquipaCasa != null) return false;
        if (idEquipaFora != null ? !idEquipaFora.equals(that.idEquipaFora) : that.idEquipaFora != null) return false;
        if (idRecinto != null ? !idRecinto.equals(that.idRecinto) : that.idRecinto != null) return false;
        if (idCompeticao != null ? !idCompeticao.equals(that.idCompeticao) : that.idCompeticao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dataProva != null ? dataProva.hashCode() : 0);
        result = 31 * result + (idEquipaCasa != null ? idEquipaCasa.hashCode() : 0);
        result = 31 * result + (idEquipaFora != null ? idEquipaFora.hashCode() : 0);
        result = 31 * result + (idRecinto != null ? idRecinto.hashCode() : 0);
        result = 31 * result + (idCompeticao != null ? idCompeticao.hashCode() : 0);
        return result;
    }

    public Collection<ProvaArbitroEntity> getProvaArbitrosById() {
        return provaArbitrosById;
    }

    public void setProvaArbitrosById(Collection<ProvaArbitroEntity> provaArbitrosById) {
        this.provaArbitrosById = provaArbitrosById;
    }

    public EquipasEntity getEquipasByIdEquipaCasa() {
        return equipasByIdEquipaCasa;
    }

    public void setEquipasByIdEquipaCasa(EquipasEntity equipasByIdEquipaCasa) {
        this.equipasByIdEquipaCasa = equipasByIdEquipaCasa;
    }

    public EquipasEntity getEquipasByIdEquipaFora() {
        return equipasByIdEquipaFora;
    }

    public void setEquipasByIdEquipaFora(EquipasEntity equipasByIdEquipaFora) {
        this.equipasByIdEquipaFora = equipasByIdEquipaFora;
    }

    public RecintosEntity getRecintosByIdRecinto() {
        return recintosByIdRecinto;
    }

    public void setRecintosByIdRecinto(RecintosEntity recintosByIdRecinto) {
        this.recintosByIdRecinto = recintosByIdRecinto;
    }

    public CompeticoesEntity getCompeticoesByIdCompeticao() {
        return competicoesByIdCompeticao;
    }

    public void setCompeticoesByIdCompeticao(CompeticoesEntity competicoesByIdCompeticao) {
        this.competicoesByIdCompeticao = competicoesByIdCompeticao;
    }

    public Collection<VendaBilhetesEntity> getVendaBilhetesById() {
        return vendaBilhetesById;
    }

    public void setVendaBilhetesById(Collection<VendaBilhetesEntity> vendaBilhetesById) {
        this.vendaBilhetesById = vendaBilhetesById;
    }

    @Override
    public String toString() {
        return "ProvasEntity{" +
                "dataProva=" + dataProva +
                ", idEquipaCasa=" + idEquipaCasa +
                ", idEquipaFora=" + idEquipaFora +
                ", idRecinto=" + idRecinto +
                ", idCompeticao=" + idCompeticao +
                ", equipasByIdEquipaCasa=" + equipasByIdEquipaCasa +
                ", equipasByIdEquipaFora=" + equipasByIdEquipaFora +
                ", recintosByIdRecinto=" + recintosByIdRecinto +
                ", competicoesByIdCompeticao=" + competicoesByIdCompeticao +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(String dataProva, int idEquipaCasa, int idEquipaFora, int idRecinto, int idCompeticao) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ProvasEntity entity = new ProvasEntity();
            entity.setIdCompeticao(idCompeticao);
            entity.setIdEquipaCasa(idEquipaCasa);
            entity.setIdEquipaFora(idEquipaFora);
            entity.setIdRecinto(idRecinto);
            entity.setDataProva(Date.valueOf(dataProva));
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
            TypedQuery<ProvasEntity> query = entityManager.createNamedQuery("Provas.readById", ProvasEntity.class);
            ProvasEntity prova = query.setParameter(1, id).getSingleResult();

            System.out.println(prova.toString());
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
            TypedQuery<ProvasEntity> query = entityManager.createNamedQuery("Competicoes.readAll", ProvasEntity.class);
            for(ProvasEntity prova : query.getResultList()){
                System.out.println(prova.toString());
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
            TypedQuery<ProvasEntity> query = entityManager.createNamedQuery("Provas.readById", ProvasEntity.class);
            ProvasEntity prova = query.setParameter(1, id).getSingleResult();
            entityManager.remove(prova);
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
            TypedQuery<ProvasEntity> query = entityManager.createNamedQuery("Provas.readAll", ProvasEntity.class);
            for(ProvasEntity prova : query.getResultList()){
                entityManager.remove(prova);
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
        String dataProva = "1999-12-12";
        int idRecinto = 5;
        int idCompeticao = 1;
        int idEquipaFora = 2;
        int idEquipaCasa = 1;

        try {
            transaction.begin();
            TypedQuery<ProvasEntity> query = entityManager.createNamedQuery("Provas.readById", ProvasEntity.class);
            ProvasEntity prova = query.setParameter(1, id).getSingleResult();
            prova.setDataProva(Date.valueOf(dataProva));
            prova.setIdCompeticao(idCompeticao);
            prova.setIdEquipaCasa(idEquipaCasa);
            prova.setIdEquipaFora(idEquipaFora);
            prova.setIdRecinto(idRecinto);

            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
