package org.bura.bura_card_game;

import javafx.scene.image.ImageView;


public class CardController {

    protected RenderedCard[] cards;
    protected int top;

    public CardController() {
        top = 0;
        char[] suits = Card.getSuits();
        int numValues = Card.MAX - Card.MIN + 1;
        cards = new RenderedCard[suits.length * numValues];
        int cIndex;

        for (int s = 0; s < suits.length; s++) {
            for (int v = Card.MIN; v <= Card.MAX; v++) {
                cIndex = s * numValues + v - Card.MIN;
                cards[cIndex] = new RenderedCard(v, suits[s], true);
            }
        }
    }

    public Card getCard(int index) {
        return cards[index];
    }







    public int getTopIndex() {
        return top;
    }

    public Card deal() {
        Card dealt = cards[top++];
        if (top > cards.length) {
            return null;
        }
        return dealt;
    }

    public void reset() {
        top = 0;
    }
}
