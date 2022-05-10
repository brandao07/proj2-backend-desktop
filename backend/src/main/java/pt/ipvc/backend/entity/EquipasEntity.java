package pt.ipvc.backend.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "equipas", schema = "public", catalog = "postgres")
@NamedQuery(name = "Equipas.readById", query = "SELECT a FROM EquipasEntity a WHERE a.id = ?1")
@NamedQuery(name = "Equipas.readAll", query = "SELECT a FROM EquipasEntity a")

public class EquipasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "pais")
    private String pais;
    @Basic
    @Column(name = "cidade")
    private String cidade;
    @Basic
    @Column(name = "contacto")
    private String contacto;
    @Basic
    @Column(name = "sigla")
    private String sigla;

    @Basic
    @Column(name = "associacao")
    private String associacao;

    @OneToMany(mappedBy = "equipasByIdEquipa")
    private Collection<ContratosEntity> contratosById;
    @OneToMany(mappedBy = "equipasByIdEquipa")
    private Collection<InscricoesEntity> inscricoesById;
    @OneToMany(mappedBy = "equipasByIdEquipaCasa")
    private Collection<ProvasEntity> provasById;
    @OneToMany(mappedBy = "equipasByIdEquipaFora")
    private Collection<ProvasEntity> provasById_0;
    @Basic
    @Column(name = "visivel")
    private Boolean visivel;
    @OneToMany(mappedBy = "equipasByIdEquipa")
    private Collection<FavoritosEquipasEntity> favoritosEquipasById;

    public static void create(String nome, String pais, String cidade, String contacto, String sigla, String associacao) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EquipasEntity entity = new EquipasEntity();
            entity.setNome(nome);
            entity.setCidade(cidade);
            entity.setContacto(contacto);
            entity.setPais(pais);
            entity.setCidade(cidade);
            entity.setSigla(sigla);
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
            TypedQuery<EquipasEntity> query = entityManager.createNamedQuery("Equipas.readById", EquipasEntity.class);
            EquipasEntity equipa = query.setParameter(1, id).getSingleResult();

            System.out.println(equipa.toString());
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
            TypedQuery<EquipasEntity> query = entityManager.createNamedQuery("Equipas.readAll", EquipasEntity.class);
            for(EquipasEntity equipa : query.getResultList()){
                System.out.println(equipa.toString());
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
            TypedQuery<EquipasEntity> query = entityManager.createNamedQuery("Equipas.readById", EquipasEntity.class);
            EquipasEntity equipa = query.setParameter(1, id).getSingleResult();
            entityManager.remove(equipa);
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
            TypedQuery<EquipasEntity> query = entityManager.createNamedQuery("Equipas.readAll", EquipasEntity.class);
            for(EquipasEntity equipa : query.getResultList()){
                entityManager.remove(equipa);
            }
            transaction.commit();
        } finally{
            if (transaction.isActive()) transaction.rollback();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static void update(int id, String nome, String pais, String cidade, String contacto, String sigla, String associacao){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        try {
            transaction.begin();
            TypedQuery<EquipasEntity> query = entityManager.createNamedQuery("Equipas.readById", EquipasEntity.class);
            EquipasEntity equipa = query.setParameter(1, id).getSingleResult();
            equipa.setNome(nome);
            equipa.setPais(pais);
            equipa.setCidade(cidade);
            equipa.setContacto(contacto);
            equipa.setSigla(sigla);
            equipa.setAssociacao(associacao);
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getAssociacao() {
        return associacao;
    }

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    public Boolean getVisivel() {
        return visivel;
    }

    public void setVisivel(Boolean visivel) {
        this.visivel = visivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipasEntity that = (EquipasEntity) o;

        if (id != that.id) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (pais != null ? !pais.equals(that.pais) : that.pais != null) return false;
        if (cidade != null ? !cidade.equals(that.cidade) : that.cidade != null) return false;
        if (contacto != null ? !contacto.equals(that.contacto) : that.contacto != null) return false;
        if (sigla != null ? !sigla.equals(that.sigla) : that.sigla != null) return false;
        if (associacao != null ? !associacao.equals(that.associacao) : that.associacao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (pais != null ? pais.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (contacto != null ? contacto.hashCode() : 0);
        result = 31 * result + (sigla != null ? sigla.hashCode() : 0);
        result = 31 * result + (associacao != null ? associacao.hashCode() : 0);
        return result;
    }

    public Collection<ContratosEntity> getContratosById() {
        return contratosById;
    }

    public void setContratosById(Collection<ContratosEntity> contratosById) {
        this.contratosById = contratosById;
    }


    /* *******************************************************
     *********************** QUERIES ***********************
     ******************************************************* */

    public Collection<InscricoesEntity> getInscricoesById() {
        return inscricoesById;
    }

    public void setInscricoesById(Collection<InscricoesEntity> inscricoesById) {
        this.inscricoesById = inscricoesById;
    }

    public Collection<ProvasEntity> getProvasById() {
        return provasById;
    }

    public void setProvasById(Collection<ProvasEntity> provasById) {
        this.provasById = provasById;
    }

    public Collection<ProvasEntity> getProvasById_0() {
        return provasById_0;
    }

    public void setProvasById_0(Collection<ProvasEntity> provasById_0) {
        this.provasById_0 = provasById_0;
    }

    @Override
    public String toString() {
        return "EquipasEntity{" +
                "nome='" + nome + '\'' +
                ", pais='" + pais + '\'' +
                ", cidade='" + cidade + '\'' +
                ", contacto='" + contacto + '\'' +
                ", sigla='" + sigla + '\'' +
                ", associacao='" + associacao + '\'' +
                '}';
    }

    public Collection<FavoritosEquipasEntity> getFavoritosEquipasById() {
        return favoritosEquipasById;
    }

    public void setFavoritosEquipasById(Collection<FavoritosEquipasEntity> favoritosEquipasById) {
        this.favoritosEquipasById = favoritosEquipasById;
    }
}
