 /*
* @purpose Used as a game lauch menu
* @author Sinclair DeYoung
* @since 18-02-2025
*/

// Imports for logic
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;
import javax.imageio.IIOException;

// Import for the games
import TicTacToe.TTTRun;
import SnakeGame.SnakeRun;
import Solitaire.SolitaireRun;
/* import RPS.RPSRun; */ // Removed because RPSRun class not found

public class GameLauncher {

    // initialize the JFrame and JButtons
    private JFrame frame;
    private JButton tttButton;
    private JButton snakeButton;
    private JButton solitaireButton;
    // private JButton RPSButton; // Removed because RPSRun class not found
    static final int numberOfButtons = 3;
    // set the screen size
    static final int SCREEN_WIDTH = numberOfButtons * 200;
    static final int SCREEN_HEIGHT = 300;

    public GameLauncher() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Game Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null); // center the frame on the screen

        // Create a main panel with box layout
        JLabel titleLabel = new JLabel("Game Launcher");
        JPanel buttonPanel = new JPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false); // make the panel transparent

        // set background image
        try {
            ImageIcon backgroundImage = new ImageIcon("images/BackgroundImage.jpg");
            frame.setContentPane(new BackgroundPanel(backgroundImage));
        } catch (Exception e) {
            System.out.println("Exception error backgroud image not found: " + e.getMessage());
            frame.setContentPane(new JPanel());
        }

        // add the main panel to the frame
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // create title label
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // add space between title and buttons

        // button panel
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false); // make the panel transparent

        // create buttons
        tttButton = new JButton("Tic Tac Toe");
        snakeButton = new JButton("Snake Game");
        solitaireButton = new JButton("Solitaire");
        // RPSButton = new JButton("Rock Paper Sissors"); // Removed because RPSRun class not found
        // set button size
        styleButton(tttButton); // add style to buttons
        styleButton(snakeButton); // add style to buttons
        styleButton(solitaireButton); // add style to buttons
        // styleButton(RPSButton); // add style to buttons
        tttButton.addActionListener(l -> launchTTT()); // lambda expression
        snakeButton.addActionListener(l -> launchSnake()); // lambda expression
        solitaireButton.addActionListener(l -> launchSolitaire()); // lambda expression
        // RPSButton.addActionListener(l -> launchRPS()); // lambda expression
        buttonPanel.add(tttButton);
        buttonPanel.add(snakeButton);
        buttonPanel.add(solitaireButton);
        // add button panel to main panel
        mainPanel.add(buttonPanel); 
        //make the frame visiable
        frame.setVisible(true);
    }

    // adds universal style to buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setForeground(Color.DARK_GRAY);
        button.setBackground(new Color(219, 88, 171));
        button.setFocusPainted(false);
        button.setBorderPainted(false); // remove border
        button.setOpaque(true);

        // hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) { 
                button.setBackground(new Color(219, 140, 201));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(219, 88, 171));
            }
        });

    }

    // show the main menu
    public void showMainMenu() {
        frame.setVisible(true);
    }

    private void launchTTT() {
        // lanuches tic tac toe
        frame.setVisible(false); // set the launcher to invisible
        SwingUtilities.invokeLater(() -> TTTRun.main(null));  
    }
    private void launchSnake() {
        // lanuches Snake Game
        frame.setVisible(false); // set the launcher to invisible
        SwingUtilities.invokeLater(() -> SnakeRun.main(null));
    }
    private void launchSolitaire() {
        // lanuches Snake Game
        frame.setVisible(false); // set the launcher to invisible
        SwingUtilities.invokeLater(() -> SolitaireRun.main(null));
    }
    // private void launchRPS() {
    //     // launches RPS Game
    //     frame.setVisible(false); // set the launcher to invisible
    //     SwingUtilities.invokeLater(() -> RPSRun.main(null));
    // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameLauncher();
            }
        });
    }

    // define a custom JPanel class for the background image
    class BackgroundPanel extends JPanel {
        private final ImageIcon backgroundImage;

        public BackgroundPanel(ImageIcon backgroundImage) {
            this.backgroundImage = backgroundImage;
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
