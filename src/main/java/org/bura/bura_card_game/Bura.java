package org.bura.bura_card_game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class Bura extends Application {
    private BorderPane mainPane;
    private HBox computerPanel;
    private HBox tablePanel;
    private HBox playerPanel;
    private VBox sidePanel;
    private HBox gamePanel;
    private ImageView imageView;
    private Image image;
    private  Image backImage;

    public Bura () {

    }
    @Override
    public void start(Stage stage) throws Exception {
        mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10));
        mainPane.setStyle("-fx-background-color:red;");
        backImage = new Image("file:C:/Users/Valery Chumakou/OneDrive/Documents/Java Projects/Bura_Card_Game/images/back_image/b3.gif");

        computerPanel = new HBox(10);
        computerPanel.setPadding(new Insets(10));
        dealComputerCards();


        playerPanel = new HBox(10);
        playerPanel.setPadding(new Insets(20));
        dealPlayerCards();

        tablePanel = new HBox(10);
        tablePanel.setPadding(new Insets(10));
        mainPane.setCenter(tablePanel);

        HBox topPanel = new HBox();
        topPanel.setSpacing(10);
        topPanel.setAlignment(Pos.CENTER);
        topPanel.getChildren().addAll(computerPanel);

        mainPane.setTop(topPanel);

        HBox bottomPanel = new HBox();
        bottomPanel.setSpacing(10);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.getChildren().addAll(tablePanel,playerPanel);
        mainPane.setBottom(bottomPanel);
        mainPane.setRight(sidePanel);
        mainPane.setCenter(gamePanel);

        Scene scene = new Scene(mainPane, 750, 500);
        stage.setScene(scene);
        stage.show();

    }


    public void dealComputerCards() {
        File folder = new File("images");
        File [] files = folder.listFiles();
        Card [] cards = new Card[3];

        for (int i = 0; i<3; i++) {
            cards[i] = new Card(new ImageView(backImage));
            cards[i].getImageView().setVisible(true);
            cards[i].getImageView().setFitHeight(100);
            cards[i].getImageView().setFitWidth(100);
            computerPanel.getChildren().add(cards[i].getImageView());
        }
    }

    public void dealPlayerCards() {
        File folder  = new File("images");
        File [] files = folder.listFiles();
        Card [] cards = new Card[3];

        for (int i = 0; i<3; i++) {
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];
            Image image = new Image(randomFile.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            Card card = new Card(imageView);
            card.setImageView(imageView);
            cards[i] = card;
            cards[i].getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    Node node = (Node) event.getSource();
                    Card selectedCard = (Card) node.getUserData();
                    if (selectedCard != null && playerPanel.getChildren().contains(node)) {
                        System.out.println("Card selected");

                    }
                }
            });
        }
        playerPanel.getChildren().clear();
        for (int i = 0; i<3; i++) {
            ((ImageView)cards[i].getImageView()).setUserData(cards[i]);
            playerPanel.getChildren().add(cards[i].getImageView());
        }
    }


    public static void main (String [] args) {
        launch(args);
    }
}
