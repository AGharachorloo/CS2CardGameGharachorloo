import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private Player house;
    private Scanner input;
    private int numPlayers;

    public Game(String[] ranks, String[] suits, int[] values) {
        deck = new Deck(ranks, suits, values);
        players = new ArrayList<Player>();
        house = new Player("House");
        input = new Scanner(System.in);
        numPlayers = 0;
    }

    public void initializePlayers() {
        do {
            System.out.println("How many players are playing? ");
            numPlayers = input.nextInt();
            input.nextLine();
        } while(numPlayers < 2 || numPlayers > 7);
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("What is Player " + (i+1) + "'s name? ");
            String result = input.nextLine();
            if (result.equals("")) {
                result = "Player " + (i+1);
            }
            Player tempPlayer = new Player(result);
            players.add(tempPlayer);
        }
    }

    public void dealPlayer(int playerIndex) {
        players.get(playerIndex).addCard(deck.deal());
    }

    public void dealHouse() {
        house.addCard(deck.deal());
    }

    public void dealHands() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numPlayers; j++) {
                dealPlayer(j);
                dealHouse();
            }
        }
    }

    public void turn (int playerIndex) {
        Player currentPlayer = players.get(playerIndex);
        System.out.println(currentPlayer.getName() + "'s Turn!");
        String result;
        while (true) {
            System.out.println("Would you like to hit or stand? ");
            result = input.nextLine();
            if (result.toLowerCase().equals("hit")) {
                dealPlayer(playerIndex);
                break;
            }
            else if (result.toLowerCase().equals("stand")) {
                currentPlayer.setStands(true);
                break;
            }
            else {
                System.out.println("Input must be 'hit' or 'stand' ");
            }
        }
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
