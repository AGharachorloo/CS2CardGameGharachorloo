import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private Player house;
    private Scanner input;
    private int numPlayers;

    public Game(Deck deck) {
        this.deck = deck;
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
        } while(numPlayers < 1 || numPlayers > 7);
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
        System.out.println(players.get(playerIndex).getName() + " got a " + players.get(playerIndex).getHand().getLast());
        if (playerHasLost(playerIndex)) {
            System.out.println(players.get(playerIndex).getName() + " has busted!");
        }
        else {
            System.out.println(". Their new hand: " + players.get(playerIndex).getHand());
        }
    }

    public void dealHouse() {
        house.addCard(deck.deal());
    }

    public void printHands() {
        for (int i = 0; i < numPlayers; i++) {
            System.out.println(players.get(i).getName() + "'s cards: " + players.get(i).getHand());
        }
        System.out.println("The Dealer shows a " + house.getHand().get(0) + "\n");
    }


    public void dealHands() {
        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numPlayers; j++) {
                dealPlayer(j);
                dealHouse();
            }
            printHands();
        }
    }

    public void playerTurn(int playerIndex) {
        Player currentPlayer = players.get(playerIndex);
        if (currentPlayer.HasLost()) {
            return;
        }

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
                System.out.println(currentPlayer.getName() + " stands with a hand of " + getPlayerSum(playerIndex));
                break;
            }
            else {
                System.out.println("Input must be 'hit' or 'stand' ");
            }
        }
    }

    public void houseTurn() {
        int sum = getHouseSum();
        if (sum >= 17) {
            house.setStands(true);
        }
        else {
            dealHouse();
        }
    }

    public int getHouseSum() {
        int sum = 0;
        for (int i = 0; i < house.getHand().size(); i++) {
            sum += house.getHand().get(i).getValue();
        }
        return sum;
    }
    public int getPlayerSum(int index) {
        int sum = 0;
        for (int i = 0; i < players.get(index).getHand().size(); i++) {
            sum += players.get(index).getHand().get(i).getValue();
        }
        return sum;
    }

    public boolean playerHasLost(int index) {
        int sum = getPlayerSum(index);
        if (sum > 21) {
            players.get(index).setHasLost(true);
            return true;
        }
        return false;
    }

    public void initializeGame() {
        initializePlayers();
        dealHands();
        for (int i = 0; i < numPlayers; i++) {
            while (!players.get(i).getIfStood()) {
                playerTurn(i);
            }
        }
    }

    public static void main(String[] args) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        Deck deck = new Deck(ranks, suits, values);
        Game game = new Game(deck);
        game.initializeGame();

    }
}
