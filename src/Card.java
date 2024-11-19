public class Card {

    private String rank;
    private String suit;
    private int value;

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
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

    public static void main(String[] args) {
    }
}
