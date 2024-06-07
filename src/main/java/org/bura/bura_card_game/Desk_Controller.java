package org.bura.bura_card_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Desk_Controller extends Application {
    private static Scene scene;


    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("desk.fxml"), 640, 480);
      stage.setScene(scene);
      stage.setResizable(true);
      stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        return
        FXMLLoader.load(Objects.requireNonNull(Desk_Controller.class.getResource("desk.fxml")));

    }

    public static void main (String [] args) {
        launch();
    }
}
