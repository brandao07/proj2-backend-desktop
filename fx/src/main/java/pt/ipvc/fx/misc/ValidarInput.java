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
        //Data Inico ocorre antes da Data Fim ou Data Inicio ocorre quando a Data Fimantes da Data Fim ou Data Inicio ocorre quando a Data Fim
        return !(dInicio.compareTo(dFim) > 0);
    }


    public static void choiceBoxAdminAdicionarDados(@NotNull String value, ActionEvent event) {
        switch (value) {
            case "Árbitros" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-arbitro.fxml", event);
            case "Atletas" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-atleta.fxml", event);
            case "Equipas" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-equipa.fxml", event);
            case "Tipos de Prémio" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-premio.fxml", event);
            case "Tipos de Recinto" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-tipoRecinto.fxml", event);
            case "Recintos" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-recintos.fxml", event);
            case "Clubes" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-clube.fxml", event);

            default -> System.out.println("ERRO");
        }
    }

    public static void choiceBoxAdminConsultarDados(@NotNull String value, ActionEvent event) {
        switch (value) {
            case "Árbitros" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-arbitros.fxml", event);
            case "Atletas" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-atleta.fxml", event);
            case "Equipas" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-equipa.fxml", event);
            case "Modalidades" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-modalidade.fxml", event);
            case "Recintos" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-recinto.fxml", event);
            case "Tipos de Prémios" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-tipo-premio.fxml", event);
            case "Tipos de Recintos" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-tipo-recinto.fxml", event);
            default -> System.out.println("ERRO");
        }
    }



    public static void sideMenuBarButtonLink(@NotNull String value, ActionEvent event) {
        switch (value) {
            case "Utilizadores" ->
                    ControladorGlobal.chamaScene("Administrador/sistema/admin-sistema-utilizadores.fxml", event);
            case "Consultar Dados" ->
                    ControladorGlobal.chamaScene("Administrador/consultar_editarDados/admin-consultar-dados-arbitros.fxml", event);
            case "Adicicionar Dados" ->
                    ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-arbitro.fxml", event);
            case "Consultar Sistema" ->
                    ControladorGlobal.chamaScene("Administrador/sistema/admin-sistema-consultar.fxml", event);
            case "Criar Competição" ->
                    ControladorGlobal.chamaScene("Gestor/criarCompeticao/criar-competicoes.fxml", event);
            case "Gerir Competição" ->
                    ControladorGlobal.chamaScene("Gestor/gerirCompeticao/gerir-competicao.fxml", event);
            case "SportEX" ->
                    ControladorGlobal.chamaScene("Administrador/admin-home-page.fxml", event);
            default -> System.out.println("ERRO");
        }
    }

    public static void opcoesMenuAdicionarAdmin(@NotNull String value, ActionEvent event){
        switch (value) {
            case "Atleta" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-atleta.fxml", event);
            case "Equipa" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-clube.fxml", event);
            case "Prêmio" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-premio.fxml", event);
            case "Recintos" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-recintos.fxml", event);
            case "Árbitro" -> ControladorGlobal.chamaScene("Administrador/adicionarDados/admin-adicionar-dados-arbitro.fxml", event);

            default -> System.out.println("ERRO");
        }
    }

}
