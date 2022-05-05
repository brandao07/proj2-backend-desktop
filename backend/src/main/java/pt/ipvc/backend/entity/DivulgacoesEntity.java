package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "divulgacoes", schema = "public", catalog = "postgres")
@NamedQuery(name = "Divulgacoes.readById", query = "SELECT a FROM DivulgacoesEntity a WHERE a.id = ?1")
@NamedQuery(name = "Divulgacoes.readAll", query = "SELECT a FROM DivulgacoesEntity a")

public class DivulgacoesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "data_divulgacao")
    private Date dataDivulgacao;
    @Basic
    @Column(name = "id_competicao")
    private Integer idCompeticao;
    @Basic
    @Column(name = "id_tipo_divulgacao")
    private Integer idTipoDivulgacao;
    @ManyToOne
    @JoinColumn(name = "id_competicao", referencedColumnName = "id", insertable = false, updatable = false)
    private CompeticoesEntity competicoesByIdCompeticao;
    @ManyToOne
    @JoinColumn(name = "id_tipo_divulgacao", referencedColumnName = "id", insertable = false, updatable = false)
    private TipoDivulgacaoEntity tipoDivulgacaoByIdTipoDivulgacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataDivulgacao() {
        return dataDivulgacao;
    }

    public void setDataDivulgacao(Date dataDivulgacao) {
        this.dataDivulgacao = dataDivulgacao;
    }

    public Integer getIdCompeticao() {
        return idCompeticao;
    }

    public void setIdCompeticao(Integer idCompeticao) {
        this.idCompeticao = idCompeticao;
    }

    public Integer getIdTipoDivulgacao() {
        return idTipoDivulgacao;
    }

    public void setIdTipoDivulgacao(Integer idTipoDivulgacao) {
        this.idTipoDivulgacao = idTipoDivulgacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DivulgacoesEntity that = (DivulgacoesEntity) o;

        if (id != that.id) return false;
        if (dataDivulgacao != null ? !dataDivulgacao.equals(that.dataDivulgacao) : that.dataDivulgacao != null)
            return false;
        if (idCompeticao != null ? !idCompeticao.equals(that.idCompeticao) : that.idCompeticao != null) return false;
        if (idTipoDivulgacao != null ? !idTipoDivulgacao.equals(that.idTipoDivulgacao) : that.idTipoDivulgacao != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dataDivulgacao != null ? dataDivulgacao.hashCode() : 0);
        result = 31 * result + (idCompeticao != null ? idCompeticao.hashCode() : 0);
        result = 31 * result + (idTipoDivulgacao != null ? idTipoDivulgacao.hashCode() : 0);
        return result;
    }

    public CompeticoesEntity getCompeticoesByIdCompeticao() {
        return competicoesByIdCompeticao;
    }

    public void setCompeticoesByIdCompeticao(CompeticoesEntity competicoesByIdCompeticao) {
        this.competicoesByIdCompeticao = competicoesByIdCompeticao;
    }

    public TipoDivulgacaoEntity getTipoDivulgacaoByIdTipoDivulgacao() {
        return tipoDivulgacaoByIdTipoDivulgacao;
    }

    public void setTipoDivulgacaoByIdTipoDivulgacao(TipoDivulgacaoEntity tipoDivulgacaoByIdTipoDivulgacao) {
        this.tipoDivulgacaoByIdTipoDivulgacao = tipoDivulgacaoByIdTipoDivulgacao;
    }

    @Override
    public String toString() {
        return "DivulgacoesEntity{" +
                "dataDivulgacao=" + dataDivulgacao +
                ", idCompeticao=" + idCompeticao +
                ", idTipoDivulgacao=" + idTipoDivulgacao +
                ", competicoesByIdCompeticao=" + competicoesByIdCompeticao +
                ", tipoDivulgacaoByIdTipoDivulgacao=" + tipoDivulgacaoByIdTipoDivulgacao +
                '}';
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(String dataDivulgacao, int idCompeticao, int idTipoDivulgacao) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            DivulgacoesEntity entity = new DivulgacoesEntity();
            entity.setDataDivulgacao(Date.valueOf(dataDivulgacao));
            entity.setIdCompeticao(idCompeticao);
            entity.setIdTipoDivulgacao(idTipoDivulgacao);
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
            TypedQuery<DivulgacoesEntity> query = entityManager.createNamedQuery("Divulgacoes.readById", DivulgacoesEntity.class);
            DivulgacoesEntity divulgacao = query.setParameter(1, id).getSingleResult();

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
            TypedQuery<DivulgacoesEntity> query = entityManager.createNamedQuery("Divulgacoes.readAll", DivulgacoesEntity.class);
            for(DivulgacoesEntity divulgacao : query.getResultList()){
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
            TypedQuery<DivulgacoesEntity> query = entityManager.createNamedQuery("Divulgacoes.readById", DivulgacoesEntity.class);
            DivulgacoesEntity divulgacao = query.setParameter(1, id).getSingleResult();
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
            TypedQuery<DivulgacoesEntity> query = entityManager.createNamedQuery("Divulgacoes.readAll", DivulgacoesEntity.class);
            for(DivulgacoesEntity divulgacao : query.getResultList()){
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
        String dataDivulgacao = "2000-01-01";
        int idCompeticao = 1;
        int idTipoDivulgacao = 2;

        try {
            transaction.begin();
            TypedQuery<DivulgacoesEntity> query = entityManager.createNamedQuery("Divulgacoes.readById", DivulgacoesEntity.class);
            DivulgacoesEntity divulgacao = query.setParameter(1, id).getSingleResult();
            divulgacao.setDataDivulgacao(Date.valueOf(dataDivulgacao));
            divulgacao.setIdTipoDivulgacao(idTipoDivulgacao);
            divulgacao.setIdCompeticao(idCompeticao);
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
