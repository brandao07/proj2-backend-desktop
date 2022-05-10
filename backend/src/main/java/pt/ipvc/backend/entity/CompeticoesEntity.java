package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "competicoes", schema = "public", catalog = "postgres")
@NamedQuery(name = "Competicoes.readById", query = "SELECT a FROM CompeticoesEntity a WHERE a.id = ?1")
@NamedQuery(name = "Competicoes.readAll", query = "SELECT a FROM CompeticoesEntity a")


public class CompeticoesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "data_inicio")
    private Date dataInicio;
    @Basic
    @Column(name = "data_fim")
    private Date dataFim;
    @Basic
    @Column(name = "genero")
    private String genero;
    @Basic
    @Column(name = "id_modalidade")
    private Integer idModalidade;
    @ManyToOne
    @JoinColumn(name = "id_modalidade", referencedColumnName = "id", insertable = false, updatable = false)
    private ModalidadesEntity modalidadesByIdModalidade;
    @OneToMany(mappedBy = "competicoesByIdCompeticao")
    private Collection<DivulgacoesEntity> divulgacoesById;
    @OneToMany(mappedBy = "competicoesByIdCompeticao")
    private Collection<InscricoesEntity> inscricoesById;
    @OneToMany(mappedBy = "competicoesByIdCompeticao")
    private Collection<PremiosEntity> premiosById;
    @OneToMany(mappedBy = "competicoesByIdCompeticao")
    private Collection<ProvasEntity> provasById;
    @Basic
    @Column(name = "id_gestor")
    private Integer idGestor;
    @ManyToOne
    @JoinColumn(name = "id_gestor", referencedColumnName = "id", insertable = false, updatable = false)
    private GestoresEntity gestoresByIdGestor;
    @OneToMany(mappedBy = "competicoesByIdCompeticoes")
    private Collection<FavoritosCompeticoesEntity> favoritosCompeticoesById;

    public static void create(String nome, String dataInicio, String dataFim, String genero, int modalidade, int idGestor) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CompeticoesEntity entity = new CompeticoesEntity();
            entity.setNome(nome);
            entity.setGenero(genero);
            entity.setDataInicio(Date.valueOf(dataInicio));
            entity.setDataFim(Date.valueOf(dataFim));
            entity.setGenero(genero);
            entity.setIdModalidade(modalidade);
            entity.setIdGestor(idGestor);
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
            TypedQuery<CompeticoesEntity> query = entityManager.createNamedQuery("Competicoes.readById", CompeticoesEntity.class);
            CompeticoesEntity competicao = query.setParameter(1, id).getSingleResult();

            System.out.println(competicao.toString());
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
            TypedQuery<CompeticoesEntity> query = entityManager.createNamedQuery("Competicoes.readAll", CompeticoesEntity.class);
            for(CompeticoesEntity competicao : query.getResultList()){
                System.out.println(competicao.toString());
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
            TypedQuery<CompeticoesEntity> query = entityManager.createNamedQuery("Competicoes.readById", CompeticoesEntity.class);
            CompeticoesEntity competicao = query.setParameter(1, id).getSingleResult();
            entityManager.remove(competicao);
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
            TypedQuery<CompeticoesEntity> query = entityManager.createNamedQuery("Competicoes.readAll", CompeticoesEntity.class);
            for(CompeticoesEntity competicao : query.getResultList()){
                entityManager.remove(competicao);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, String nome, String dataInicio, String dataFim, String genero, int idGestor){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            TypedQuery<CompeticoesEntity> query = entityManager.createNamedQuery("Competicoes.readById", CompeticoesEntity.class);
            CompeticoesEntity competicao = query.setParameter(1, id).getSingleResult();
            competicao.setNome(nome);
            competicao.setGenero(genero);
            competicao.setDataInicio(Date.valueOf(dataInicio));
            competicao.setDataFim(Date.valueOf(dataFim));
            competicao.setIdGestor(idGestor);

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIdModalidade() {
        return idModalidade;
    }

    public void setIdModalidade(Integer idModalidade) {
        this.idModalidade = idModalidade;
    }

    @Override
    public String toString() {
        return "CompeticoesEntity{" +
                "nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", genero='" + genero + '\'' +
                ", idModalidade=" + idModalidade +
                ", modalidadesByIdModalidade=" + modalidadesByIdModalidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompeticoesEntity that = (CompeticoesEntity) o;

        if (id != that.id) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (dataInicio != null ? !dataInicio.equals(that.dataInicio) : that.dataInicio != null) return false;
        if (dataFim != null ? !dataFim.equals(that.dataFim) : that.dataFim != null) return false;
        if (genero != null ? !genero.equals(that.genero) : that.genero != null) return false;
        if (idModalidade != null ? !idModalidade.equals(that.idModalidade) : that.idModalidade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (dataInicio != null ? dataInicio.hashCode() : 0);
        result = 31 * result + (dataFim != null ? dataFim.hashCode() : 0);
        result = 31 * result + (genero != null ? genero.hashCode() : 0);
        result = 31 * result + (idModalidade != null ? idModalidade.hashCode() : 0);
        return result;
    }

    public ModalidadesEntity getModalidadesByIdModalidade() {
        return modalidadesByIdModalidade;
    }

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public void setModalidadesByIdModalidade(ModalidadesEntity modalidadesByIdModalidade) {
        this.modalidadesByIdModalidade = modalidadesByIdModalidade;
    }

    public Collection<DivulgacoesEntity> getDivulgacoesById() {
        return divulgacoesById;
    }

    public void setDivulgacoesById(Collection<DivulgacoesEntity> divulgacoesById) {
        this.divulgacoesById = divulgacoesById;
    }

    public Collection<InscricoesEntity> getInscricoesById() {
        return inscricoesById;
    }

    public void setInscricoesById(Collection<InscricoesEntity> inscricoesById) {
        this.inscricoesById = inscricoesById;
    }

    public Collection<PremiosEntity> getPremiosById() {
        return premiosById;
    }

    public void setPremiosById(Collection<PremiosEntity> premiosById) {
        this.premiosById = premiosById;
    }

    public Collection<ProvasEntity> getProvasById() {
        return provasById;
    }

    public void setProvasById(Collection<ProvasEntity> provasById) {
        this.provasById = provasById;
    }

    public Integer getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(Integer idGestor) {
        this.idGestor = idGestor;
    }

    public GestoresEntity getGestoresByIdGestor() {
        return gestoresByIdGestor;
    }

    public void setGestoresByIdGestor(GestoresEntity gestoresByIdGestor) {
        this.gestoresByIdGestor = gestoresByIdGestor;
    }

    public Collection<FavoritosCompeticoesEntity> getFavoritosCompeticoesById() {
        return favoritosCompeticoesById;
    }

    public void setFavoritosCompeticoesById(Collection<FavoritosCompeticoesEntity> favoritosCompeticoesById) {
        this.favoritosCompeticoesById = favoritosCompeticoesById;
    }
}
