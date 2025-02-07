import javax.swing.*;
import java.awt.*;

public class Card {

    private String rank;
    private String suit;
    private int value;
    private Image[] cardImages;
    private Image cardBack;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        cardBack = new ImageIcon("Resources/back.png").getImage();
        cardImages = new Image[52];
        for (int i = 0; i < 52; i++) {
            cardImages[i] = new ImageIcon("Resources/" + (i+1) + ".png").getImage();
        }
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public void setRank(String newRank) {
        rank = newRank;
    }

    public void setSuit(String newSuit) {
        suit = newSuit;
    }

    public void setValue(int newValue) {
        value = newValue;
    }

    public String toString() {
        return rank + " of " + suit;
    }

}
