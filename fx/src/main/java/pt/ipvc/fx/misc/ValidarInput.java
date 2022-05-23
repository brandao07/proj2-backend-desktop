package pt.ipvc.fx.misc;

import javafx.event.ActionEvent;
import org.jetbrains.annotations.NotNull;
import pt.ipvc.fx.controller.ControladorGlobal;

public class ValidarInput {

    public static boolean validarString(String value){
        if (value != null)
            return !value.isEmpty();
        return false;
    }

    public static boolean validarDouble(double value){
        return value > 0;
    }

    public static boolean validarInt(int value){
        return value > 0;
    }

    public static void validarAdicionarDados(@NotNull String value, ActionEvent event){
        switch (value) {
            case "Árbitros":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-arbitro.fxml", event);
                break;

            case "Atletas":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-atleta.fxml", event);
                break;

            case "Contratos":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-contrato.fxml", event);
                break;

            case "Equipas":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-equipa.fxml", event);
                break;

            case "Modalidades":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-modalidade.fxml", event);
                break;

            case "Recintos":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-recinto.fxml", event);
                break;

            case "Tipos de Divulgação":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-divulgacao.fxml", event);
                break;

            case "Tipos de Prémios":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-premios.fxml", event);
                break;

            case "Tipos de Recintos":
                ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-tiporecinto.fxml", event);
                break;

            default:
                System.out.println("ERRO");
        }
    }
}
