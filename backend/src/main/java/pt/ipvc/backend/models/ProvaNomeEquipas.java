package pt.ipvc.backend.models;

import java.util.Date;

public class ProvaNomeEquipas {
    private Long id;

    private String resultadoEquipaCasa;

    private Date dataInicio;

    private String recinto;

    private String equipaCasa;

    private String equipaFora;

    private String resultadoEquipaFora;

    private String arbitro;


    public ProvaNomeEquipas(String resultadoEquipaCasa, Date dataInicio, String recinto, String equipaCasa, String equipaFora, String resultadoEquipaFora, String arbitro, Long id) {
        this.resultadoEquipaCasa = resultadoEquipaCasa;
        this.dataInicio = dataInicio;
        this.recinto = recinto;
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.resultadoEquipaFora = resultadoEquipaFora;
        this.arbitro = arbitro;
        this.id = id;
    }

    public ProvaNomeEquipas(){}

    public String getResultadoEquipaCasa() {
        return resultadoEquipaCasa;
    }

    public void setResultadoEquipaCasa(String resultadoEquipaCasa) {
        this.resultadoEquipaCasa = resultadoEquipaCasa;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getRecinto() {
        return recinto;
    }

    public void setRecinto(String recinto) {
        this.recinto = recinto;
    }

    public String getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(String equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public String getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(String equipaFora) {
        this.equipaFora = equipaFora;
    }

    public String getResultadoEquipaFora() {
        return resultadoEquipaFora;
    }

    public void setResultadoEquipaFora(String resultadoEquipaFora) {
        this.resultadoEquipaFora = resultadoEquipaFora;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProvaNomeEquipas{" +
                "resultadoEquipaCasa='" + resultadoEquipaCasa + '\'' +
                ",dataInicio='" + dataInicio +
                ",recinto='" + recinto + '\'' +
                ",equipaCasa='" + equipaCasa + '\'' +
                ",equipaFora='" + equipaFora + '\'' +
                ",resultadoEquipaFora='" + resultadoEquipaFora + '\'' +
                ",arbitro='" + arbitro + '\'' +
                ", id=" + id +
                '}';
    }
}
