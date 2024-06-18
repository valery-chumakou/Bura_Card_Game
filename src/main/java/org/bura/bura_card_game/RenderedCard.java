package org.bura.bura_card_game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;


public class RenderedCard extends Card {
    protected boolean isHighSuit;
    protected Image image;
    protected Dimension dim;

    public RenderedCard(int cv, char s, boolean v) {
        super(cv, s, v);


        dim = new Dimension();
        dim.setSize(73, 97);
    }

    public void setHighSuit(boolean hs) {
        isHighSuit = hs;
    }

    public boolean getHighSuit() {
        return isHighSuit;
    }


    }










