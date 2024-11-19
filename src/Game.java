import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private Player house;

    public Game(String[] ranks, String[] suits, int[] values) {
        deck = new Deck(ranks, suits, values);
        players = new ArrayList<Player>();
        house = new Player("House");
    }

    public initializePlayers() {

    }
    public static void main(String[] args) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        Deck deck = new Deck(ranks, suits, values);
//        System.out.println(deck.deal());
//        System.out.println(deck.getCardsLeft());
//        deck.shuffle();
//        System.out.println(deck.getCardsLeft());
//        System.out.println(deck.deal());

    }
}
