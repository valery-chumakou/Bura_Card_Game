package org.bura.bura_card_game;

import javafx.application.Application;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Desk_Controller extends Application {

@FXML
private GridPane dealer_panel;
@FXML
private GridPane player_panel;
@FXML
private Button start_btn;
private List<Card> cards = new ArrayList<>();


public void initialize(URL url, ResourceBundle resourceBundle) {
    for (int i = 0; i<6; i++) {
        Card card = new Card(i);
        cards.add(card);
    }
}
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("desk.fxml"));
        Parent root = loader.load();
        GameController gameController = loader.getController();
        gameController.startGame();
        stage.setScene(new Scene(root));
        stage.show();
    }
}