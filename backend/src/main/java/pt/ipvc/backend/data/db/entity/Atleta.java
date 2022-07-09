package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "atleta")
public class Atleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "genero")
    private String genero;

    @Column(name = "data_nascimento")
    private Date dataNascimento;


    @Column(name = "peso")
    private Double peso;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Column(name = "posicao")
    private String posicao;

    @ManyToOne
    @JoinColumn(name = "equipa_id")
    private Equipa equipa;

    @ManyToOne
    @JoinColumn(name = "modalidade_id")
    private Modalidade modalidade;
    @Basic
    @Column(name = "imagem_web")
    private String imagemWeb;

    @Column(name = "naturalidade")
    private String naturalidade;

    @Column(name = "imagem_byte")
    private byte[] imagemByte;

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Atleta() {
    }

    public Atleta(String nome, String genero, Date dataNascimento, Double peso, Double altura,
                  String nacionalidade, String naturalidade,String posicao, byte[] imagemByte) {
        this.nome = nome;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        this.altura = altura;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
        this.posicao = posicao;
        this.imagemByte = imagemByte;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
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

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
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

    @Override
    public String toString() {
        return "Atleta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", peso=" + peso +
                ", altura=" + altura +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", posicao='" + posicao + '\'' +
                ", equipa=" + equipa + '\'' +
                ", imagemByte=" + imagemByte + '\'' +
                '}';
    }


}