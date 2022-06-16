package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "premio")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lugar")
    private Integer lugar;

    @Column(name = "valor")
    private String valor;


    @ManyToOne
    @JoinColumn(name = "tipo_premio_id")
    private TipoPremio tipoPremio;

    @ManyToOne
    @JoinColumn(name = "competicao_id")
    private Competicao competicao;

    public Premio() {
    }

    public Premio(Integer lugar, String valor) {
        this.lugar = lugar;
        this.valor = valor;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    public void setCompeticao(Competicao competicao) {
        this.competicao = competicao;
    }

    public TipoPremio getTipoPremio() {
        return tipoPremio;
    }

    public void setTipoPremio(TipoPremio tipoPremio) {
        this.tipoPremio = tipoPremio;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getLugar() {
        return lugar;
    }

    public void setLugar(Integer lugar) {
        this.lugar = lugar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Premio{" +
                "id=" + id +
                ", lugar=" + lugar +
                ", valor=" + valor +
                ", tipoPremio=" + tipoPremio.getNome() +
                ", competicao=" + competicao.getNome() +
                '}';
    }
}