package org.bura.bura_card_game;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Card_Controller {

private Image image;
private int i;
public Card_Controller(Card card) {
    this.i = card.getI();
    this.image = new Image("file:C:/Users/Valery Chumakou/OneDrive/Documents/Java Projects/Bura_Card_Game/src/main/java/org/bura/bura_card_game/cards/" + (i*2)+".jpg");
}

public ImageView getCardView() {
    ImageView view = new ImageView();
    view.setImage(image);
    return view;
}

}
