package pt.ipvc.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pt.ipvc.fx.controller.ControladorGlobal;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("controller/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add( new Image(new File("fx/src/main/resources/pt/ipvc/fx/logo.png").toURI().toString()));

        stage.setOnCloseRequest(event -> {
            event.consume();
            ControladorGlobal.exit(stage);
        });
    }
}