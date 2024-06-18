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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    private Image backImage;

    public Bura() {

    }

    @Override
    public void start(Stage stage) throws Exception {
        mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10));
        mainPane.setStyle("-fx-background-color:red;");
        backImage = new Image("file:C:/Users/Valery Chumakou/OneDrive/Documents/Java Projects/Bura_Card_Game/images/back_image/b3.gif");

        gamePanel = new HBox(10);
        gamePanel.setPadding(new Insets(10));
        gamePanel.setAlignment(Pos.CENTER);

        computerPanel = new HBox(10);
        computerPanel.setPadding(new Insets(10));

        sidePanel = new VBox(10);
        sidePanel.setPadding(new Insets(100));
        mainPane.setRight(sidePanel);

        playerPanel = new HBox(10);
        playerPanel.setPadding(new Insets(20));

        getMoreCards();
        dealPlayerCards();
        dealComputerCards();


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
        bottomPanel.getChildren().addAll(tablePanel, playerPanel);
        mainPane.setBottom(bottomPanel);
        mainPane.setRight(sidePanel);
        mainPane.setCenter(gamePanel);


        Scene scene = new Scene(mainPane, 750, 500);
        stage.setScene(scene);
        stage.show();

    }


    public void getMoreCards() {
        File folder = new File("images");
        File[] files = folder.listFiles();
        Random random = new Random();
        Image backimage = new Image("file:C:/Users/Valery Chumakou/OneDrive/Documents/Java Projects/Bura_Card_Game/images/back_image/b3.gif");
        ImageView backimageView = new ImageView(backimage);
        Image triumphImage = new Image(files[random.nextInt(files.length)].toURI().toString());
        ImageView triumphImageView = new ImageView(triumphImage);
        triumphImageView.setFitHeight(100);
        triumphImageView.setFitWidth(100);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(backimageView, triumphImageView);
        mainPane.setCenter(vBox);
        imageView = triumphImageView;
        Image image = new Image(files[random.nextInt(files.length)].toURI().toString());
        sidePanel.getChildren().add(new ImageView(image));
        sidePanel.getChildren().add(new ImageView(backimage));
    }


    public void dealComputerCards() {

        File folder = new File("images");
        File[] files = folder.listFiles();
        Card[] cards = new Card[3];


        for (int i = 0; i < 3; i++) {
            cards[i] = new Card(new ImageView(backImage));
            ((ImageView) cards[i].getImageView()).setUserData(cards[i]);
            cards[i].getImageView().setVisible(true);
            cards[i].getImageView().setFitHeight(100);
            cards[i].getImageView().setFitWidth(100);
            ((ImageView) cards[i].getImageView()).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    Node node = (Node) event.getSource();
                    Card selectedCard = (Card) node.getUserData();
                    if (selectedCard != null && playerPanel.getChildren().contains(node)) {
                        gamePanel.getChildren().add(selectedCard.getImageView());
                        playerPanel.getChildren().remove(node);
                        computerMode();
                    }
                }
            });
        }
        playerPanel.getChildren().clear();
        for (int i = 0; i < 3; i++) {
             playerPanel.getChildren().add(cards[i].getImageView());
        }
    }

    public void computerMode() {
        File folder = new File("images");
        File [] files = folder.listFiles();
        Random random = new Random();
        File randomFile = files[random.nextInt(files.length)];
        Image image = new Image(randomFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Card card = new Card(imageView);
        card.setImageView(imageView);
        gamePanel.getChildren().add(card.getImageView());
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
                        gamePanel.getChildren().add(selectedCard.getImageView());
                        playerPanel.getChildren().remove(node);

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
