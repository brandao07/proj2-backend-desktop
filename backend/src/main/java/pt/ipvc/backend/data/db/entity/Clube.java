package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.sql.Date;

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
    private Date dataFundacao;

    @Column(name = "contacto")
    private String contacto;

    @Basic
    @Column(name = "imagem_web")
    private String imagemWeb;

    @Column(name = "imagem_byte")
    private byte[] imagemByte;

    public Clube(String nome) {
        this.nome = nome;
    }

    public Clube(String nome, String sigla, String pais, String cidade, Date dataFundacao, String contacto, byte[] imagemByte) {
        this.nome = nome;
        this.sigla = sigla;
        this.pais = pais;
        this.cidade = cidade;
        this.dataFundacao = dataFundacao;
        this.contacto = contacto;
        this.imagemByte = imagemByte;
    }

    public Clube() {

    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
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

    public String getImagemWeb() {
        return imagemWeb;
    }

    public void setImagemWeb(String imagemWeb) {
        this.imagemWeb = imagemWeb;
    }

    public byte[] getImagemByte() {
        return imagemByte;
    }

    public void setImagemByte(byte[] imagemByte) {
        this.imagemByte = imagemByte;
    }
}