import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private final static int WINDOW_HEIGHT = 900;
    private final static int WINDOW_WIDTH = 1400;
    private final static int WELCOME_SCREEN = 0;
    private final static int PLAYING_GAME = 1;
    private final static int RESULTS = 2;
    private Image background;
    private Game back;

    public GameView(Game back) {
        //Initialize reference to the backend

        // Initialize the screen
        this.back = back;
        this.setTitle("Black Jack!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        background = new ImageIcon("Resources/TableBackground.jpg").getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        if (back.getStatus() == WELCOME_SCREEN) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 70));
            g.drawString("WELCOME TO BLACKJACK!", 225, 100);
            g.setFont(new Font("SansSerif", Font.BOLD, 25));
            g.drawString("The goal of the game is to get as close to 21 or hit 21" +
                    " without going over", 50, 200);
        }
    }
}
