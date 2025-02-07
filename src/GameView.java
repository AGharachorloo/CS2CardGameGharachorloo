import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private final static int WINDOW_HEIGHT = 1200;
    private final static int WINDOW_WIDTH = 2000;
    Image background;

    public GameView() {
        //Initialize reference to the backend

        // Initialize the screen
        this.setTitle("Black Jack!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        background = new ImageIcon("Resources/TablesBackground.jpg").getImage();
    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
    }

    public static void main(String[] args) {
        GameView game = new GameView();
        game.repaint();
    }
}
