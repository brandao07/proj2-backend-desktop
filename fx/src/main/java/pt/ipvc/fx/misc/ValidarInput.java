package pt.ipvc.fx.misc;

import javafx.event.ActionEvent;
import org.jetbrains.annotations.NotNull;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.time.LocalDate;

public class ValidarInput {

    public static boolean validarString(String value) {
        if (value != null)
            return !value.isEmpty();
        return false;
    }

    public static boolean validarDouble(double value) {
        return value > 0;
    }

    public static boolean validarInt(int value) {
        return value > 0;
    }

    public static boolean validarDataPicker(LocalDate value) {
        if (value != null)
            return !value.equals(null);
        return false;
    }

    public static boolean validarListView(boolean value) {
        if (value){
            System.out.println("true");
            return true;
        }
        System.out.println("false");

        return false;
    }

    public static boolean validarChoiceBox(Object value) {
        if (value != null)
            return !value.equals(null);
        return false;
    }
    public static boolean validarDatas(@NotNull LocalDate dInicio, @NotNull LocalDate dFim) {
        //Data Inico ocorre         //Data Inico ocorre antes da Data Fim ou Data Inicio ocorre quando a Data Fimantes da Data Fim ou Data Inicio ocorre quando a Data Fim
        return !(dInicio.compareTo(dFim) > 0);
    }


    public static void validarAdicionarDados(@NotNull String value, ActionEvent event) {
        switch (value) {
            case "Árbitros" -> ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-arbitro.fxml", event);
            case "Atletas" -> ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-atleta.fxml", event);
            case "Contratos" -> ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-contrato.fxml", event);
            case "Equipas" -> ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-equipa.fxml", event);
            case "Modalidades" -> ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-modalidade.fxml", event);
            case "Recintos" -> ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-recinto.fxml", event);
            case "Tipos de Divulgação" ->
                    ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-divulgacao.fxml", event);
            case "Tipos de Prémios" ->
                    ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-premios.fxml", event);
            case "Tipos de Recintos" ->
                    ControladorGlobal.chamaScene("adicionarDados/adicionar-dados-tiporecinto.fxml", event);
            default -> System.out.println("ERRO");
        }
    }

    public static void mudarPagConsultarEditarAdmin(@NotNull String value, ActionEvent event) {
        switch (value) {
            case "Árbitros" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-sistema-adicionar-user.fxml", event);
            case "Atletas" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-atleta.fxml", event);
            case "Equipas" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-equipa.fxml", event);
            case "Prémios" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-premio.fxml", event);
            case "Modalidades" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-modalidade.fxml", event);
            case "Recintos" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-recinto.fxml", event);
            case "Tipos de Prémios" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-tipos-premio.fxml", event);
            case "Tipos de Recintos" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-tipos-recinto.fxml", event);
            default -> System.out.println("ERRO");
        }
    }

    public static void sideMenuBarButtonLink(@NotNull String value, ActionEvent event) {
        switch (value) {
            case "Consultar Dados" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-editar-dados-arbitros.fxml", event);
            case "Adicicionar Dados" ->
                    ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-arbitro.fxml", event);
            case "Consultar Sistema" ->
                    ControladorGlobal.chamaScene("Administrador/sistema/admin-sistema-consultar.fxml", event);
            case "Criar Utilizador\n" ->
                    ControladorGlobal.chamaScene("Administrador/sistema/admin-sistema-adicionar-user.fxml", event);
            default -> System.out.println("ERRO");
        }
    }

    public static void opcoesMenuAdicionarAdmin(@NotNull String value, ActionEvent event){
        switch (value) {
            case "Atleta" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-atleta.fxml", event);
            case "Equipa" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-equipa.fxml", event);
            case "Prêmio" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-premio.fxml", event);
            case "Recintos\n" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-recintos.fxml", event);
            case "Árbitro" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-arbitro.fxml", event);

            default -> System.out.println("ERRO");
        }
    }

}
