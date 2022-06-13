package pt.ipvc.backend.data.db.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "prova")
public class Prova {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "resultado_equipa_casa")
    private String resultadoEquipaCasa;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @ManyToOne
    @JoinColumn(name = "recinto_id")
    private Recinto recinto;

    @ManyToOne
    @JoinColumn(name = "competicao_id")
    private Competicao competicao;

    @ManyToOne
    @JoinColumn(name = "equipa_casa_id")
    private Equipa equipaCasa;

    @ManyToOne
    @JoinColumn(name = "equipa_fora_id")
    private Equipa equipaFora;

    @Column(name = "resultado_equipa_fora")
    private String resultadoEquipaFora;

    public String getResultadoEquipaFora() {
        return resultadoEquipaFora;
    }

    public void setResultadoEquipaFora(String resultadoEquipaFora) {
        this.resultadoEquipaFora = resultadoEquipaFora;
    }

    public Equipa getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(Equipa equipaFora) {
        this.equipaFora = equipaFora;
    }

    public Equipa getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(Equipa equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public Competicao getCompeticao() {
        return competicao;
    }

    public void setCompeticao(Competicao competicao) {
        this.competicao = competicao;
    }

    public Recinto getRecinto() {
        return recinto;
    }

    public void setRecinto(Recinto recinto) {
        this.recinto = recinto;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getResultadoEquipaCasa() {
        return resultadoEquipaCasa;
    }

    public void setResultadoEquipaCasa(String resultado) {
        this.resultadoEquipaCasa = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Prova{" +
                "id=" + id +
                ", resultado='" + resultadoEquipaCasa + '\'' +
                ", dataInicio=" + dataInicio +
                ", recinto=" + recinto +
                ", competicao=" + competicao +
                ", equipaCasa=" + equipaCasa +
                ", equipaFora=" + equipaFora +
                '}';
    }
}