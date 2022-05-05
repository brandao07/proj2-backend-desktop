package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@NamedQuery(name = "Atletas.readById", query = "SELECT a FROM AtletasEntity a WHERE a.id = ?1")
@NamedQuery(name = "Atletas.readAll", query = "SELECT a FROM AtletasEntity a")

@Table(name = "atletas", schema = "public", catalog = "postgres")
public class AtletasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "genero")
    private String genero;
    @Basic
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    @Basic
    @Column(name = "peso")
    private double peso;
    @Basic
    @Column(name = "altura")
    private double altura;
    @Basic
    @Column(name = "naturalidade")
    private String naturalidade;
    @Basic
    @Column(name = "nacionalidade")
    private String nacionalidade;
    @Basic
    @Column(name = "posicao")
    private String posicao;
    @Basic
    @Column(name = "associacao")
    private String associacao;
    @OneToMany(mappedBy = "atletasByIdAtleta")
    private Collection<ContratosEntity> contratosById;
    @OneToMany(mappedBy = "atletasByIdAtleta")
    private Collection<InscricoesEntity> inscricoesById;
    @Basic
    @Column(name = "visivel")
    private Boolean visivel;

    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public static void create(String nome, String genero, String dataNascimento, double peso, double altura, String naturalidade, String nacionalidade, String posicao, String associacao) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AtletasEntity entity = new AtletasEntity();
            entity.setNome(nome);
            entity.setGenero(genero);
            entity.setDataNascimento(Date.valueOf(dataNascimento));
            entity.setPeso(peso);
            entity.setAltura(altura);
            entity.setNaturalidade(naturalidade);
            entity.setNacionalidade(nacionalidade);
            entity.setPosicao(posicao);
            entity.setAssociacao(associacao);
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
            TypedQuery<AtletasEntity> query = entityManager.createNamedQuery("Atletas.readById", AtletasEntity.class);
            AtletasEntity atleta = query.setParameter(1, id).getSingleResult();

            System.out.println(atleta.toString());
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
            TypedQuery<AtletasEntity> query = entityManager.createNamedQuery("Atletas.readAll", AtletasEntity.class);
                for(AtletasEntity atleta : query.getResultList()){
                    System.out.println(atleta.toString());
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
            TypedQuery<AtletasEntity> query = entityManager.createNamedQuery("Atletas.readById", AtletasEntity.class);
            AtletasEntity atleta = query.setParameter(1, id).getSingleResult();
            entityManager.remove(atleta);
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
            TypedQuery<AtletasEntity> query = entityManager.createNamedQuery("Atletas.readAll", AtletasEntity.class);
            for(AtletasEntity atleta : query.getResultList()){
                entityManager.remove(atleta);
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
        String nome = "Teste";
        String genero = "Macho";
        String dataNascimento = "2000-01-01";
        double altura= 1.50;
        double peso = 60;
        String naturalidade = "Amadora";
        String nacionalidade = "Angola";
        String posicao = "Guarda-Sandes";
        String associacao = "Sagres-Mini";

        try {
            transaction.begin();
            TypedQuery<AtletasEntity> query = entityManager.createNamedQuery("Atletas.readById", AtletasEntity.class);
            AtletasEntity atleta = query.setParameter(1, id).getSingleResult();
            atleta.setNome(nome);
            atleta.setGenero(genero);
            atleta.setDataNascimento(Date.valueOf(dataNascimento));
            atleta.setAltura(altura);
            atleta.setPeso(peso);
            atleta.setNaturalidade(naturalidade);
            atleta.setNacionalidade(nacionalidade);
            atleta.setPosicao(posicao);
            atleta.setAssociacao(associacao);
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getAssociacao() {
        return associacao;
    }

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AtletasEntity that = (AtletasEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.peso, peso) != 0) return false;
        if (Double.compare(that.altura, altura) != 0) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (genero != null ? !genero.equals(that.genero) : that.genero != null) return false;
        if (dataNascimento != null ? !dataNascimento.equals(that.dataNascimento) : that.dataNascimento != null)
            return false;
        if (naturalidade != null ? !naturalidade.equals(that.naturalidade) : that.naturalidade != null) return false;
        if (nacionalidade != null ? !nacionalidade.equals(that.nacionalidade) : that.nacionalidade != null)
            return false;
        if (posicao != null ? !posicao.equals(that.posicao) : that.posicao != null) return false;
        if (associacao != null ? !associacao.equals(that.associacao) : that.associacao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (genero != null ? genero.hashCode() : 0);
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        temp = Double.doubleToLongBits(peso);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(altura);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (naturalidade != null ? naturalidade.hashCode() : 0);
        result = 31 * result + (nacionalidade != null ? nacionalidade.hashCode() : 0);
        result = 31 * result + (posicao != null ? posicao.hashCode() : 0);
        result = 31 * result + (associacao != null ? associacao.hashCode() : 0);
        return result;
    }

    public Collection<ContratosEntity> getContratosById() {
        return contratosById;
    }

    public void setContratosById(Collection<ContratosEntity> contratosById) {
        this.contratosById = contratosById;
    }

    public Collection<InscricoesEntity> getInscricoesById() {
        return inscricoesById;
    }

    public void setInscricoesById(Collection<InscricoesEntity> inscricoesById) {
        this.inscricoesById = inscricoesById;
    }

    @Override
    public String toString() {
        return "AtletasEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", peso=" + peso +
                ", altura=" + altura +
                ", naturalidade='" + naturalidade + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", posicao='" + posicao + '\'' +
                ", associacao='" + associacao + '\'' +
                '}';
    }

    public Boolean getVisivel() {
        return visivel;
    }

    public void setVisivel(Boolean visivel) {
        this.visivel = visivel;
    }
}

