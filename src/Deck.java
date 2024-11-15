import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> deck;
    private int cardsLeft;

    public Deck(String[] rank, String[] suit, int[] value) {
        deck = new ArrayList<Card>();
        Card currentCard;
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                currentCard = new Card(rank[j], suit[i], value[j]);
                deck.add(currentCard);
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
        if (cardsLeft == 0) {
            return null;
        }
        cardsLeft--;
        return deck.get(cardsLeft - 1);
    }
    public void shuffle() {
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

    public static void main(String[] args) {

    }
}
