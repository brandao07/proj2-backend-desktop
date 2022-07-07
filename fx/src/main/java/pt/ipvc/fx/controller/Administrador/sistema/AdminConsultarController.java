package pt.ipvc.fx.controller.Administrador.sistema;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import pt.ipvc.backend.data.db.entity.users.Utilizador;
import pt.ipvc.backend.services.users.UtilizadorBLL;
import pt.ipvc.fx.misc.ValidarInput;

import java.net.URL;
import java.util.*;

public class AdminConsultarController implements Initializable {
    @FXML
    private BarChart barrasAdminsGestores;

    @FXML
    private LineChart clientes;

    @FXML
    protected Label usernameLabel;




    public void setBtnNavMenu(ActionEvent event) {
        String nome_scene = String.valueOf(event.getTarget());
        nome_scene = nome_scene.substring(nome_scene.indexOf("'") + 1);
        nome_scene = nome_scene.substring(0, nome_scene.indexOf("'"));
        ValidarInput.sideMenuBarButtonLink(nome_scene, event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameLabel.setText(UtilizadorBLL.getUserSession().getUsername());

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        XYChart.Series series3 = new XYChart.Series();

        long n_admins = UtilizadorBLL.numeroAdmins();
        long n_gestores = UtilizadorBLL.numeroGestores();

        series1.setName("Adminstradores");
        series2.setName("Gestores");
        series3.setName("Clientes");



        series1.getData().add(new XYChart.Data("Administradores", Math.toIntExact(n_admins)));
        series2.getData().add(new XYChart.Data("Gestores", Math.toIntExact(n_gestores)));

        barrasAdminsGestores.getData().addAll(series1, series2);

        List infoData = UtilizadorBLL.getDataCount();
        Map<String, Integer> dataMap = new HashMap<>();
        String data = "";
        String count = "";

        for (Object item : infoData){
            data = item.toString();
            data = data.substring((data.indexOf("=") + 1));
            data = data.substring(0, data.indexOf(","));

            count = item.toString();
            count = count.substring((count.indexOf("count=") + 1));
            count = count.substring(0, count.indexOf("}"));
            count = count.replace("ount=", "");

            dataMap.put(data, Integer.valueOf(count));
        }

        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            String tmpString = entry.getKey();
            Integer tmpValue = entry.getValue();
            XYChart.Data<String, Integer> d = new XYChart.Data<>(tmpString, tmpValue);
            System.out.println(d);
            series3.getData().add(d);
        }
        clientes.getData().addAll(series3);


    }
}
