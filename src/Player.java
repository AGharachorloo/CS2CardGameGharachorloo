import java.util.ArrayList;
public class Player {
    private ArrayList<Card> hand;
    private int points;
    private String name;
    private boolean stands;
    private boolean hasLost;
    private String result;

    public Player(String name) {
        this.name = name;
        points = 1000;
        hand = new ArrayList<Card>();
        stands = false;
        hasLost = false;
    }
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        int size = hand.size();
        this.hand = new ArrayList<Card>();
        for (int i = 0; i < size; i++) {
            this.hand.add(hand.get(i));
        }
        stands = false;
        hasLost = false;
        points = 1000;
        result = "";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isHasLost() {
        return hasLost;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public boolean getIfStood() {
        return stands;
    }

    public void setStands(boolean stands) {
        this.stands = stands;
    }

    public void addPoints(int newPoints) {
        points += newPoints;
    }

    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }

}
