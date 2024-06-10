package org.bura.bura_card_game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {

    private int i;
    private Image image;

    public Card (int i) {
        this.i = i;
        this.image = new Image("file:C:/Users/Valery Chumakou/OneDrive/Documents/Java Projects/Bura_Card_Game/src/main/java/org/bura/bura_card_game/cards/" + (i*2)+".jpg");
    }

    public ImageView getCardView() {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        return imageView;
    }
    public int getI() {
        return i;
    }

}