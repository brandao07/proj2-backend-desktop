package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "clube")
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", unique = true)
    private String nome;

    @Column(name = "sigla")
    private String sigla;

    @Column(name = "pais")
    private String pais;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "data_fundacao")
    private String dataFundacao;

    @Column(name = "contacto")
    private String contacto;

    @Basic
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "clube", orphanRemoval = true)
    private Set<Equipa> equipas = new LinkedHashSet<>();

    public Set<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(Set<Equipa> equipas) {
        this.equipas = equipas;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(String dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}