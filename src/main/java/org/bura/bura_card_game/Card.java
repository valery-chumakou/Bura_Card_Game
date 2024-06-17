package org.bura.bura_card_game;


import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends Canvas implements MouseListener {

    public static final int JACK = 11, QUEEN = 12, KING = 13,
    ACE = 14, MAX = 14, MIN = 2;
    protected int card_value;
    protected int real_value;
    protected char suit;
    protected boolean visible;
    ImageView imageView;
    private Image image;

    public Card (int cv, int rv, char s, boolean v) {
        card_value = (cv>=MIN && rv<=MAX)?cv:2;
        real_value = rv;
        if (s=='C' || s == 'D' || s == 'H' || s == 'S') {
            suit = s;
        } else {
            suit = 'C';
        }
        visible = v;
    }

    public Card (int cv, char s, boolean v) {
        this(cv, cv, s, v);
    }

    public boolean getVisible() {
        return visible;
    }
    public Card (ImageView imageView) {
        this.imageView = imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String toString() {
        if (!visible) {
            return "??";
        }
        String face;
        if (card_value>=2 && card_value <=10) {
            face = String.valueOf(card_value);
        } else {
            switch (card_value) {
                case JACK:
                    face = "J";
                    break;
                case QUEEN:
                    face = "Q";
                    break;
                case KING:
                    face = "K";
                    break;
                case ACE:
                    face = "A";
                    break;
                default:
                    face = "2";
            }
        }
        face += suit;
        return face;
    }

    public  void setImage(Image image) {
        this.image = image;
        imageView = new ImageView(String.valueOf(image));
        imageView.setFitWidth(50);
        imageView.setFitHeight(75);
    }

    public void setValue (int rv) {
        real_value = rv;
    }
    public int getValue() {
        return real_value;
    }
    public int getCard_value() {
        return card_value;
    }
    public char getSuit() {
        return suit;
    }

    public static char[] getSuits() {
        char[] suits = {'C', 'D', 'H', 'S'};
        return suits;
        }

    public boolean isPicturedCard() {
        if (card_value >= JACK && card_value <= KING) {
            return true;
        }
        return false;

    }

    public ImageView getImageView() {
        return imageView;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}