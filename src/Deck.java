import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> deck;
    private int cardsLeft;
    private Image cardBack;
    private Image[] cardImages;
    private GameView front;

    public Deck(String[] rank, String[] suit, int[] value, GameView front) {
        this.front = front;
        deck = new ArrayList<Card>();
        Card currentCard;
        cardBack = new ImageIcon("Resources/back.png").getImage();
        cardImages = new Image[52];
        for (int i = 0; i < 52; i++) {
            cardImages[i] = new ImageIcon("Resources/" + (i+1) + ".png").getImage();
        }
        int cardNumber = 0;
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                currentCard = new Card(rank[j], suit[i], value[j], cardImages[cardNumber], front);
                deck.add(currentCard);
                cardNumber++;
            }
        }
        cardsLeft = deck.size();
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        // Check if there are cards remaining, otherwise remove a card and return that card.
        if (cardsLeft == 0) {
            return null;
        }
        cardsLeft--;
        return deck.get(cardsLeft - 1);
    }
    public void shuffle() {
        // take the top card of the deck and put it in a random spot below it.
        cardsLeft = deck.size();
        int randomIndex;
        Card temp;
        for (int i = cardsLeft - 1; i >= 0; i--) {
            randomIndex = (int)(i*Math.random());
            temp = deck.get(randomIndex);
            deck.set(randomIndex, deck.get(i));
            deck.set(i, temp);
        }
    }
}
