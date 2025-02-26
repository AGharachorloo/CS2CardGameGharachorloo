import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private final static int WINDOW_HEIGHT = 850;
    private final static int WINDOW_WIDTH = 1400;
    private final static int WELCOME_SCREEN = 0;
    private final static int PLAYING_GAME = 1;
    private final static int DEALER_TURN = 2;
    private final static int RESULTS = 3;
    private final static int DEALERHAND_Y = 16;
    private final static int PLAYERHAND_Y = 632;
    private int playerIndex;
    private Image gameBackground, welcomeScreen, resultsBackground, cardBack;
    private Game back;

    public GameView(Game back) {
        //Initialize reference to the backend

        // Initialize the screen
        this.back = back;
        this.setTitle("Black Jack!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        // Initialize the images
        cardBack = new ImageIcon("Resources/back.png").getImage();
        gameBackground = new ImageIcon("Resources/gameBackground.png").getImage();
        welcomeScreen = new ImageIcon("Resources/TitleScreen.png").getImage();
        resultsBackground = new ImageIcon("Resources/resultsBackground.png").getImage();
        // Set the player index to zero
        playerIndex = 0;
    }

    // Set the current player index
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    // Paint the screen depending on the current game status
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("San Serif", Font.BOLD + Font.ITALIC, 50));
        // Check if status is welcome screen
        if (back.getStatus() == WELCOME_SCREEN) {
            // Show the welcome screen with instructions on it
            g.drawImage(welcomeScreen, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        // Check if status is playing the game
        } else if (back.getStatus() == PLAYING_GAME) {
            // Draw the game background and the hidden dealers card
            String playerName = back.getPlayers().get(playerIndex).getName();
            g.drawImage(gameBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawImage(cardBack, 550, 40,160, 248, this);
            // Show all of the players cards in their hand
            for (int i = 1; i < back.getHouse().getHand().size(); i++) {
                g.drawImage(back.getHouse().getHand().get(i).getCardImage(), 550 + 160*i, 40, 160, 248, this);
            }
            // Print the player who's turn it is then display the cards in their hand
            g.drawString(playerName.toUpperCase() + "'S TURN!", WINDOW_WIDTH/2 - (playerName.length())*30, WINDOW_HEIGHT/2);
            for (int i = 0; i < back.getPlayers().get(playerIndex).getHand().size(); i++) {
                g.drawImage(back.getPlayers().get(playerIndex).getHand().get(i).getCardImage(), 550 + 160*i, WINDOW_HEIGHT-40-248, 160, 248, this);
            }
        // if the status is on the dealers turn
        } else if (back.getStatus() == DEALER_TURN) {
            // Draw the background and the dealers cards (including the previously hidden card)
            g.drawImage(gameBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            for (int i = 0; i < back.getHouse().getHand().size(); i++) {
                g.drawImage(back.getHouse().getHand().get(i).getCardImage(), 550 + 160*i, 40, 160, 248, this);
            }
            // Print that it is the dealers turn
            g.drawString("DEALER'S TURN!", WINDOW_WIDTH/2 - (6*30), WINDOW_HEIGHT/2);
        // If the status is at the results portion
        } else if (back.getStatus() == RESULTS) {
            // set the background to the results background
            g.setFont(new Font("San Serif", Font.BOLD, 40));
            g.drawImage(resultsBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            // Print out each players name and their result;
            for (int i = 0; i < back.getPlayers().size(); i++) {
                Player currentPlayer = back.getPlayers().get(i);
                g.drawString(currentPlayer.getName() + " has " + currentPlayer.getResult(), 500, 100 + 100*i);
            }
        }
    }
}
