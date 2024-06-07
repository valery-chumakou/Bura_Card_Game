module org.bura.bura_card_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.bura.bura_card_game to javafx.fxml;
    exports org.bura.bura_card_game;
}