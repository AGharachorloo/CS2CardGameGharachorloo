import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private final static int WELCOME_SCREEN = 0;
    private final static int PLAYING_GAME = 1;
    private final static int DEALER_TURN = 2;
    private final static int RESULTS = 3;
    private ArrayList<Player> players;
    private Player house;
    private Scanner input;
    private int numPlayers;
    private GameView front;
    private Deck deck;
    private int status;

    public Game() {
        status = WELCOME_SCREEN;
        front = new GameView(this);
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values, front);
        players = new ArrayList<Player>();
        house = new Player("House");
        input = new Scanner(System.in);
        numPlayers = 0;
    }

    public Player getHouse() {
        return house;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void printInstructions() {
        // Print the instructions to the game.
        front.repaint();
        System.out.println("Welcome to Black Jack. The goal of the game is to get as close to 21 or hit 21" +
                " without going over");
        System.out.println("In order to win, your cards must be higher than or equal to that of the dealer.");
        System.out.println("You will be dealt 2 cards, only being able to see one of the dealers two cards.");
        System.out.println("Number cards are worth their number value, faces are worth 10, and ace's are worth 1.");
        System.out.println("When it is your turn you can either hit or stand");
        System.out.println("Hit: ask for another card to be dealt to you");
        System.out.println("Stand: ask to stop recieving cards and hold at the hand you currently have");
        System.out.println("Your turn will continue until you either stand or bust (go over 21), moving onto " +
                "the next person");
        System.out.println("When all player turns are over, the house will reveal its second card and play.");
        System.out.println("If the house has a total of 17 or above, they will be forced to stand");
        System.out.println("It is important to note that the game is not played against the other players but rather" +
                " against the house.");
    }

    public int getStatus() {
        return status;
    }

    public void initializePlayers() {
        // Ask for number of players, b/w 1-7
        do {
            System.out.println("How many players are playing? ");
            numPlayers = input.nextInt();
            input.nextLine();
        } while(numPlayers < 1 || numPlayers > 7);
        // Ask for the players name, if no name, call them Player X.
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
        // Deal the player a card
        players.get(playerIndex).addCard(deck.deal());
        System.out.println(players.get(playerIndex).getName() + " got a " + players.get(playerIndex).getHand().getLast());
        // Check if they have busted.
        if (playerHasLost(playerIndex)) {
            System.out.println(players.get(playerIndex).getName() + " has busted!");
        }
    }

    public void dealHouse() {
        // Deal to the house
        house.addCard(deck.deal());
        // Check if house busts
        if (houseHasLost()) {
            System.out.println("The dealer has busted!");
        }
    }

    public void printHands() {
        // Print out each players' hand
        for (int i = 0; i < numPlayers; i++) {
            System.out.println(players.get(i).getName() + "'s cards: " + players.get(i).getHand());
        }
        // Print out dealer's hand
        if (house.getHand().size() >= 2) {
            System.out.println("The Dealer shows a " + house.getHand().get(1) + "\n");
        }
    }


    public void dealHands() {
        // Deal hands by giving each player one card, then the house, and print each round of giving hands
        // Do so twice.
        deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numPlayers; j++) {
                dealPlayer(j);
            }
            dealHouse();
            printHands();
        }
    }

    public void playerTurn(int playerIndex) {
        // Check if player has lost
        Player currentPlayer = players.get(playerIndex);
        if (currentPlayer.isHasLost()) {
            return;
        }
        front.repaint();

        System.out.println(currentPlayer.getName() + "'s Turn!");
        String result;
        // If they haven't prompt if they would like to hit or stand
        while (true) {
            System.out.println("Would you like to hit or stand? ");
            result = input.nextLine();
            // If they hit, deal them a card and check if they have lost
            // If they haven't lost, print their new hand and end the turn.
            if (result.toLowerCase().equals("hit")) {
                dealPlayer(playerIndex);
                front.repaint();
                if (!playerHasLost(playerIndex)) {
                    System.out.println("Their new hand: " + players.get(playerIndex).getHand() + " Sum: " + getPlayerSum(playerIndex));
                }
                else {
                    return;
                }
            }
            // If they stand, end the turn.
            else if (result.toLowerCase().equals("stand")) {
                currentPlayer.setStands(true);
                System.out.println(currentPlayer.getName() + " stands with a hand of " + getPlayerSum(playerIndex));
                return;
            }
            // If they input incorrectly, reprompt.
            else {
                System.out.println("Input must be 'hit' or 'stand' ");
            }
        }
    }

    public void houseTurn() {
        // Check to see if the house's cards sum to or more than 17. If so, stand, otherwise hit.\
        front.repaint();
        int sum = getHouseSum();
        if (sum >= 17) {
            house.setStands(true);
            System.out.println("House has stood.");
        }
        else {
            System.out.println("Dealer hits...");
            dealHouse();
            front.repaint();
            if (!house.isHasLost()) {
            System.out.println("The Dealer got a " + house.getHand().getLast());
            System.out.println("Their new hand: " + house.getHand() + " Sum: " + getHouseSum());
            }
        }
    }

    public int getHouseSum() {
        // Get the sum of the cards of the house. Add up each card's value in hand.
        int sum = 0;
        for (int i = 0; i < house.getHand().size(); i++) {
            sum += house.getHand().get(i).getValue();
        }
        return sum;
    }

    public int getPlayerSum(int index) {
        // Get the sum of the cards of the player whos index was inputted. Add up each card's value in hand.
        int sum = 0;
        for (int i = 0; i < players.get(index).getHand().size(); i++) {
            sum += players.get(index).getHand().get(i).getValue();
        }
        return sum;
    }

    public boolean playerHasLost(int index) {
        // If the players cards sum up to over 21, change their status to lost.
        int sum = getPlayerSum(index);
        if (sum > 21) {
            players.get(index).setHasLost(true);
            return true;
        }
        return false;
    }
    public boolean houseHasLost() {
        // If the house's cards are over 21, change status to lost.
        int sum = getHouseSum();
        if (sum > 21) {
            house.setHasLost(true);
            return true;
        }
        return false;
    }

    public void playGame() {
        // Initialize the game (print instructions, initialize players, deal hands)
        printInstructions();
        initializePlayers();
        status = PLAYING_GAME;
        front.repaint();
        dealHands();
        // For each player, as long as they haven't stood and haven't lost, have them play a turn till one is true.
        // When their round is over print the new hands of the players
        for (int i = 0; i < numPlayers; i++) {
            while (!players.get(i).getIfStood() && !players.get(i).isHasLost()) {
                front.setPlayerIndex(i);
                playerTurn(i);
                front.repaint();
                System.out.println("Press enter for next player: ");
                input.nextLine();
            }
            printHands();
        }
        // After all players have played, have the dealer reveal 2nd card and play till either busts or stands.
        System.out.println("Press enter for Dealer's turn: ");
        System.out.println("The Dealer reveals hand..." + house.getHand());
        status = DEALER_TURN;
        front.repaint();
        while(!house.getIfStood() && !house.isHasLost()) {
            System.out.println("Press enter for next turn: ");
            input.nextLine();
            houseTurn();
        }
        front.repaint();
        // Figure out which players beat, tied, or lost to the house
        System.out.println("Press enter for results");
        input.nextLine();
        status = RESULTS;
        findWinners();
        front.repaint();
    }

    public void findWinners() {
        int houseSum = getHouseSum();
        int playerSum;
        // For each player, If they haven't lost,
        // check to see if they have a sum greater, equal to, or less than the house and print results.
        for (int i = 0; i < numPlayers; i++) {
            if (!players.get(i).isHasLost()) {
                playerSum = getPlayerSum(i);
                if (playerSum > houseSum && !(houseSum > 21) || house.isHasLost()) {
                    System.out.println(players.get(i).getName() + " has won against the house!");
                    players.get(i).setResult("won!");
                }
                else if (playerSum == houseSum) {
                    System.out.println(players.get(i).getName() + " has pushed against the house!");

                    players.get(i).setResult("pushed!");
                }
                else {
                    System.out.println(players.get(i).getName() + " has lost against the house.");
                    players.get(i).setResult("lost!");
                }
            }
            // If they have already lost, print the loss due to bust.
            else {
                System.out.println(players.get(i).getName() + " has lost against the house.");
                players.get(i).setResult("lost!");
            }
        }
    }

    public static void main(String[] args) {
        // Initialize the game instance
        Game game = new Game();
        // Play the game!
        game.playGame();

    }
}
