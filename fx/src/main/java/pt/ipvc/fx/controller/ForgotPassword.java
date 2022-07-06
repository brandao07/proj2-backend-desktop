package pt.ipvc.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.events.Event;
import pt.ipvc.backend.data.db.entity.users.Administrador;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.services.users.UtilizadorBLL;

import java.util.ArrayList;
import java.util.List;

public class ForgotPassword {

    @FXML
    protected TextField username;

    @FXML
    protected Label labelErro;

    public static String user;

    @FXML
    public void askForPassword(ActionEvent event) {
        List<Utilizador> lista_utilizadores = new ArrayList<>();
        lista_utilizadores = UtilizadorBLL.getUtilizadores();

        for (Utilizador u : lista_utilizadores){
            if (u.getUsername().equals(username.getText())){
                user = u.getUsername();
                ControladorGlobal.chamaScene("set-password-view.fxml", event);
                return;
            }
        }
        labelErro.setText("Utilizador não encontrado.");
    }


}
