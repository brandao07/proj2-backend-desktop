package pt.ipvc.fx.controller.Administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pt.ipvc.fx.controller.ControladorGlobal;

public class AdminHomePageController {
    @FXML
    private BarChart<?, ?> barrasAdminsGestores;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnAdicionarDados;

    @FXML
    private Button btnConsultarDados;

    @FXML
    private Button btnEditarDados;

    @FXML
    private Button btnRemoverDados;

    @FXML
    private Label labelNome;

    @FXML
    private LineChart<?, ?> lineChartClientes;


    public void setBtnAdicionarDados(ActionEvent event) {
        ControladorGlobal.chamaScene("adicionarDados/admin-adicionar-dados-arbitro.fxml", event);

    }
}
