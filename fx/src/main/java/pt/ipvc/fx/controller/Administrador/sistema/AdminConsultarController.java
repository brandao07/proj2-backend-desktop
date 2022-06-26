package pt.ipvc.fx.controller.Administrador.sistema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminConsultarController implements Initializable {
    @FXML
    private BarChart barrasAdminsGestores;



    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        long n_admins = UtilizadorBLL.numeroAdmins();
        long n_gestores = UtilizadorBLL.numeroGestores();

        series1.setName("Adminstradores");
        series2.setName("Gestores");

        series1.getData().add(new XYChart.Data("Administradores", Math.toIntExact(n_admins)));
        series2.getData().add(new XYChart.Data("Gestores", Math.toIntExact(n_gestores)));



        barrasAdminsGestores.getData().addAll(series1, series2);
    }
}
