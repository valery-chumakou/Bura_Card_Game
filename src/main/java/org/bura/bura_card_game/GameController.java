package org.bura.bura_card_game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private GridPane dealer_panel;
    @FXML
    private GridPane player_panel;
    @FXML
    private Button start_btn;
    private List<Card>cards = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start_btn.setOnAction(e->startGame());
        for (int i = 0; i<3; i++) {
            Card card = new Card(i);
            cards.add(card);
        }
    }

    @FXML
    public void startGame() {
        for (int i = 0; i<cards.size(); i++) {
            Card card = cards.get(i);
            ImageView imageView = card.getCardView();
            GridPane.setConstraints(imageView,0,1);
            dealer_panel.add(imageView,0,i);
            imageView = card.getCardView();
            GridPane.setConstraints(imageView,0,i);
             player_panel.add(imageView,0,i);
        }
    }

    @FXML
    public void startButtonPressed() {
        startGame();
    }
}
