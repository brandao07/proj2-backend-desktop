package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@NamedQuery(name = "Arbitros.readById", query = "SELECT a FROM ArbitrosEntity a WHERE a.id = ?1")
@NamedQuery(name = "Arbitros.readAll", query = "SELECT a FROM ArbitrosEntity a")
@Table(name = "arbitros", schema = "public", catalog = "postgres")
public class ArbitrosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    @Basic
    @Column(name = "naturalidade")
    private String naturalidade;
    @Basic
    @Column(name = "nacionalidade")
    private String nacionalidade;
    @Basic
    @Column(name = "associacao")
    private String associacao;
    @Basic
    @Column(name = "categoria")
    private String categoria;
    @OneToMany(mappedBy = "arbitrosByIdArbitro")
    private Collection<ProvaArbitroEntity> provaArbitrosById;
    @Basic
    @Column(name = "visivel")
    private Boolean visivel;

    @Override
    public String toString() {
        return "ArbitrosEntity{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", naturalidade='" + naturalidade + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", associacao='" + associacao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", provaArbitrosById=" + provaArbitrosById +
                '}';
    }

    /* *******************************************************
       *********************** QUERIES ***********************
       ******************************************************* */

    public static void create(String nome, String dataNascimento, String naturalidade, String nacionalidade, String associacao, String categoria) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ArbitrosEntity entity = new ArbitrosEntity();
            entity.setNome(nome);
            entity.setDataNascimento(Date.valueOf(dataNascimento));
            entity.setNaturalidade(naturalidade);
            entity.setNacionalidade(nacionalidade);
            entity.setAssociacao(associacao);
            entity.setCategoria(categoria);
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
            TypedQuery<ArbitrosEntity> query = entityManager.createNamedQuery("Arbitros.readById", ArbitrosEntity.class);
            ArbitrosEntity arbitros = query.setParameter(1, id).getSingleResult();

            System.out.println(arbitros.toString());
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
            TypedQuery<ArbitrosEntity> query = entityManager.createNamedQuery("Arbitros.readAll", ArbitrosEntity.class);
                for(ArbitrosEntity arbitro : query.getResultList()){
                    System.out.println(arbitro.toString());
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
            TypedQuery<ArbitrosEntity> query = entityManager.createNamedQuery("Arbitros.readById", ArbitrosEntity.class);
            ArbitrosEntity arbitro = query.setParameter(1, id).getSingleResult();
            entityManager.remove(arbitro);
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
            TypedQuery<ArbitrosEntity> query = entityManager.createNamedQuery("Arbitros.readAll", ArbitrosEntity.class);
            for(ArbitrosEntity arbitro : query.getResultList()){
                entityManager.remove(arbitro);
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
        String nome = "Teste Arbitro";
        //String genero = "Macho";
        String dataNascimento = "2000-01-01";
        double altura= 1.50;
        double peso = 60;
        String naturalidade = "Amadora Arbitro";
        String nacionalidade = "Angola Arbitro";
        //String posicao = "Guarda-Sandes Arbitro";
        String associacao = "Sagres-Mini Arbitro";
        String categoria = "Internacional e Regional";

        try {
            transaction.begin();
            TypedQuery<ArbitrosEntity> query = entityManager.createNamedQuery("Arbitros.readById", ArbitrosEntity.class);
            ArbitrosEntity arbitro = query.setParameter(1, id).getSingleResult();
            arbitro.setNome(nome);
            //arbitro.setGenero(genero);
            arbitro.setDataNascimento(Date.valueOf(dataNascimento));
            //arbitro.setAltura(altura);
            //arbitro.setPeso(peso);
            arbitro.setNaturalidade(naturalidade);
            arbitro.setNacionalidade(nacionalidade);
            arbitro.setAssociacao(associacao);
            arbitro.setCategoria(categoria);
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getAssociacao() {
        return associacao;
    }

    /* ---------------------- QUERIES ---------------------- */

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArbitrosEntity that = (ArbitrosEntity) o;

        if (id != that.id) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (dataNascimento != null ? !dataNascimento.equals(that.dataNascimento) : that.dataNascimento != null)
            return false;
        if (naturalidade != null ? !naturalidade.equals(that.naturalidade) : that.naturalidade != null) return false;
        if (nacionalidade != null ? !nacionalidade.equals(that.nacionalidade) : that.nacionalidade != null)
            return false;
        if (associacao != null ? !associacao.equals(that.associacao) : that.associacao != null) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        result = 31 * result + (naturalidade != null ? naturalidade.hashCode() : 0);
        result = 31 * result + (nacionalidade != null ? nacionalidade.hashCode() : 0);
        result = 31 * result + (associacao != null ? associacao.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }

    public Collection<ProvaArbitroEntity> getProvaArbitrosById() {
        return provaArbitrosById;
    }

    public void setProvaArbitrosById(Collection<ProvaArbitroEntity> provaArbitrosById) {
        this.provaArbitrosById = provaArbitrosById;
    }

    public Boolean getVisivel() {
        return visivel;
    }

    public void setVisivel(Boolean visivel) {
        this.visivel = visivel;
    }
}
