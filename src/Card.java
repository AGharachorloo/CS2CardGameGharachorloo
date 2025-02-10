import javax.swing.*;
import java.awt.*;

public class Card {

    private String rank;
    private String suit;
    private int value;
    private Image cardImage;
    private GameView front;

    public Card(String rank, String suit, int value, Image cardImage, GameView front) {
        this.front = front;
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.cardImage = cardImage;
    }
    public Image getCardImage() {
        return cardImage;
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
