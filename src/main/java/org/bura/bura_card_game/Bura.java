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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Random;

public class Bura extends Application {
    private BorderPane mainPane;
    private HBox computerPanel;
    private HBox tablePanel;
    private HBox playerPanel;
    private VBox sidePanel;
    private HBox gamePanel;
    private VBox discardPanel;
    private ImageView imageView;
    private Image image;
    private Image backImage;
    private List<Card> computerHand;
    private List<Card> playerHand;
    private RenderedCard clickedCard;
    private Splash endTurnImg;
    private Splash pickUp;
    private char highSuit;


    private boolean playersTurn = true;
    private boolean playerHasDefended = false;
    private boolean endGame = false;
    private boolean quitGame = false;
    private int n;
    private RenderedCard highCard;
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
        dealComputerCards();
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
        bottomPanel.getChildren().addAll(tablePanel, playerPanel);
        mainPane.setBottom(bottomPanel);
        mainPane.setRight(sidePanel);
        mainPane.setCenter(gamePanel);


        discardPanel = new VBox();
        discardPanel.setSpacing(10);
        discardPanel.setAlignment(Pos.BOTTOM_LEFT);
        mainPane.setLeft(discardPanel); // add this line
        ImageView imageView = new ImageView(backImage);
        discardPanel.getChildren().add(imageView);


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



    public void discardCards() {

    }

    public void dealPlayerCards() {
        File folder = new File("images");
        File[] files = folder.listFiles();
        Card[] cards = new Card[3];

        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            File randomFile = files[random.nextInt(files.length)];
            Image image = new Image(randomFile.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            Card card = new Card(imageView);
            card.setImageView(imageView);
            card.setSelected(false);
            cards[i] = card;
            card.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Node node = (Node) event.getSource();
                    Card selectedCard = (Card) node.getUserData();
                    if (selectedCard != null && !selectedCard.isSelected()) {
                        gamePanel.getChildren().add(selectedCard.getImageView());
                        playerPanel.getChildren().remove(node);
                        selectedCard.setSelected(true);
                        playersTurn = false;
                        playerMode(selectedCard);
                        computerMode();
                    }
                }
            });

        }
        playerPanel.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            ((ImageView) cards[i].getImageView()).setUserData(cards[i]);
            playerPanel.getChildren().add(cards[i].getImageView());
        }
    }

    public void dealComputerCards() {
        File folder = new File("images");
        File[] files = folder.listFiles();
        Card[] cards = new Card[3];
         for (int i = 0; i < 3; i++) {
            Random random = new Random();
            Image image = backImage;
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            Card card = new Card(imageView);
            card.setImageView(imageView);
            computerPanel.getChildren().add(imageView); // Add the computer's card to the computer's panel
            cards[i] = card;

        }
     }


    public char getTrumpSuit(char suit) {
        // determine trump suit based on suit of turned-up card
        if (suit == 'D') {
            return 'D'; // diamonds is trump
        } else if (suit == 'C') {
            return 'C'; // clubs is trump
        } else if (suit == 'H') {
            return 'H'; // hearts is trump
        } else {
            return 'S'; // spades is trump
        }
    }

    public void playerMode(Card selectedCard) {
        File folder = new File("images");
        File[] files = folder.listFiles();
        if (gamePanel.getChildren().size() > 1) {
            gamePanel.getChildren().remove(0);
        }
        Random random = new Random();
        File randomFile = files[random.nextInt(files.length)];
        Image image = new Image(randomFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Card card = new Card(imageView);
        card.setImageView(imageView);

        // Get the name of the selected image file
        String imageName = randomFile.getName();
        String rank = getRank(imageName);
        String suit = getSuit(imageName);
        System.out.println("Player's card: " + rank + " of " + suit);

        // Set the text on the ImageView
        Text text = new Text(rank + " of " + suit);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", 12));
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Your code here
            }
        });
        gamePanel.getChildren().add(imageView);
    }
    public void computerMode() {
        File folder = new File("images");
        File[] files = folder.listFiles();
        if (computerPanel.getChildren().size() > 1) {
            computerPanel.getChildren().remove(0);
        }
        Random random = new Random();
        File randomFile = files[random.nextInt(files.length)];
        Image image = new Image(randomFile.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Card card = new Card(imageView);
        card.setImageView(imageView);

        // Get the name of the selected image file
        String imageName = randomFile.getName();
        String[] parts = imageName.split("_");
        String rank = parts[1].split("\\.")[0];
        String suit = parts[0];

        // Print the rank and suit of the computer's card
        System.out.println("Computer card: " + rank + " of " + suit);

        gamePanel.getChildren().add(imageView);
    }

    public String getRank(String fileName) {
        String[] parts = fileName.split("_");
        String[] rankParts = parts[1].split("\\.");
        return rankParts[0];
    }

    public String getSuit(String fileName) {
        return fileName.split("_")[0];
    }

    public static void main (String [] args) {
        launch(args);
    }
}
