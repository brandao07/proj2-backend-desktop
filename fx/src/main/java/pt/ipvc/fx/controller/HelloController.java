package pt.ipvc.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pt.ipvc.backend.entity.*;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        RecintosEntity.readAll();

    }
}