package pt.ipvc.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class ControladorGlobal {
    public static void chamaScene(String nomeScene, @NotNull ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(ControladorGlobal.class.getResource(nomeScene));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("SportEX");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exit(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Estás prestes a fechar a aplicação!");
        alert.setContentText("Tens a certeza?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static void adicionarArbitro() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação do árbitro realizada com sucesso!");
        alert.showAndWait();
    }

    public static void adicionarAtleta() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação do atleta realizada com sucesso!");
        alert.showAndWait();
    }

    public static void adicionarClube() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação do clube realizada com sucesso!");
        alert.showAndWait();
    }

    public static void adicionarEquipa() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação da equipa realizada com sucesso!");
        alert.showAndWait();
    }

    public static void adicionarRecinto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação do recinto realizado com sucesso!");
        alert.showAndWait();
    }

    public static void adicionarTipoRecinto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação do tipo de recinto realizado com sucesso!");
        alert.showAndWait();
    }

    public static void adicionarPremio() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação do tipo de prémio realizado com sucesso!");
        alert.showAndWait();
    }

    public static void adicionarPosicao() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText(null);
        alert.setContentText("Criação da posição realizada com sucesso!");
        alert.showAndWait();
    }


}
