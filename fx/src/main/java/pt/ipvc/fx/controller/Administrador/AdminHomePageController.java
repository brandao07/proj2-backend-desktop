package pt.ipvc.fx.controller.Administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.controller.ControladorGlobal;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AdminHomePageController implements Initializable {
    @FXML
    private Label labelBemVindo;

    @FXML
    private Label clientes_ativos;

    @FXML
    private Label admins_ativos;

    @FXML
    private Label gestores_ativos;

    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utilizador utilizador = UtilizadorBLL.getUserSession();
        labelBemVindo.setText("Bem-Vindo " + UtilizadorBLL.getUserSession().getUsername());
        Random r = new Random();
        int low_admins = 10;
        int high_admins = 100;
        int low_gestores = 75;
        int high_gestores = 400;
        int low_clientes = 400;
        int high_clientes = 10000;
        int result_admins = r.nextInt(high_admins-low_admins) + low_admins;
        int result_gestores = r.nextInt(high_gestores-low_gestores) + low_gestores;
        int result_clientes = r.nextInt(high_clientes-low_clientes) + low_clientes;
        admins_ativos.setText(String.valueOf(result_admins));
        gestores_ativos.setText(String.valueOf(result_gestores));
        clientes_ativos.setText(String.valueOf(result_clientes));
    }
}
