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
        cardBack = new ImageIcon("Resources/back.png").getImage();
        gameBackground = new ImageIcon("Resources/gameBackground.png").getImage();
        welcomeScreen = new ImageIcon("Resources/TitleScreen.png").getImage();
        resultsBackground = new ImageIcon("Resources/resultsBackground.png").getImage();
        playerIndex = 0;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("San Serif", Font.BOLD + Font.ITALIC, 50));

        if (back.getStatus() == WELCOME_SCREEN) {
            g.drawImage(welcomeScreen, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        } else if (back.getStatus() == PLAYING_GAME) {
            String playerName = back.getPlayers().get(playerIndex).getName();
            g.drawImage(gameBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            g.drawImage(cardBack, 550, 40,160, 248, this);
            for (int i = 1; i < back.getHouse().getHand().size(); i++) {
                g.drawImage(back.getHouse().getHand().get(i).getCardImage(), 550 + 160*i, 40, 160, 248, this);
            }
            g.drawString(playerName.toUpperCase() + "'S TURN!", WINDOW_WIDTH/2 - (playerName.length())*30, WINDOW_HEIGHT/2);
            for (int i = 0; i < back.getPlayers().get(playerIndex).getHand().size(); i++) {
                g.drawImage(back.getPlayers().get(playerIndex).getHand().get(i).getCardImage(), 550 + 160*i, WINDOW_HEIGHT-40-248, 160, 248, this);
            }
        } else if (back.getStatus() == DEALER_TURN) {
            String playerName = back.getPlayers().get(playerIndex).getName();
            g.drawImage(gameBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            for (int i = 0; i < back.getHouse().getHand().size(); i++) {
                g.drawImage(back.getHouse().getHand().get(i).getCardImage(), 550 + 160*i, 40, 160, 248, this);
            }
            g.drawString("DEALER'S TURN!", WINDOW_WIDTH/2 - (6*30), WINDOW_HEIGHT/2);
        } else if (back.getStatus() == RESULTS) {
            g.setFont(new Font("San Serif", Font.BOLD, 40));
            g.drawImage(resultsBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            for (int i = 0; i < back.getPlayers().size(); i++) {
                Player currentPlayer = back.getPlayers().get(i);
                g.drawString(currentPlayer.getName() + " has " + currentPlayer.getResult(), 500, 100 + 100*i);
            }
        }
    }
}
