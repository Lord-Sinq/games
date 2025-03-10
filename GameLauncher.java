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
// Import for the games
import TicTacToe.TTTRun;
import Snake.SnakeGame;
import Solitaire.SolitaireRun;
import java.util.concurrent.Flow;
import javax.imageio.IIOException;

public class GameLauncher {

    // initialize the JFrame and JButtons
    private JFrame frame;
    private JButton tttButton;
    private JButton snakeButton;
    private JButton solitaireButton;
    // set the screen size
    static final int SCREEN_WIDTH = 400;
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
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false); // make the panel transparent

        // set background image
        try {
            ImageIcon backgroundImage = new ImageIcon("images/BackgroundImage.jpg");
            frame.setContentPane(new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            });
            
        } catch (Exception e) {
            System.out.println("Error backgroud image not found: " + e);
            frame.setContentPane(new JPanel());
        }

        // add the main panel to the frame
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // create title label
        JLabel titleLabel = new JLabel("Game Launcher");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // center the label
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // add space between title and buttons

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false); // make the panel transparent

        // create buttons
        tttButton = new JButton("Tic Tac Toe");
        snakeButton = new JButton("Snake Game");
        solitaireButton = new JButton("Solitaire");
        // set button size
        styleButton(tttButton); // add style to buttons
        styleButton(snakeButton); // add style to buttons
        styleButton(solitaireButton); // add style to buttons
        tttButton.addActionListener(l -> lauchTTT()); // lambda expression
        snakeButton.addActionListener(l -> lauchSnake()); // lambda expression
        solitaireButton.addActionListener(l -> lauchSolitaire()); // lambda expression
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

    private void lauchTTT() {
        // lanuches tic tac toe
        frame.setVisible(false); // set the launcher to invisible
        SwingUtilities.invokeLater(() -> TTTRun.main(null));  
    }
    private void lauchSnake() {
        // lanuches Snake Game
        frame.setVisible(false); // set the launcher to invisible
        SwingUtilities.invokeLater(() -> SnakeGame.main(null));
    }
    private void lauchSolitaire() {
        // lanuches Snake Game
        frame.setVisible(false); // set the launcher to invisible
        SwingUtilities.invokeLater(() -> SolitaireRun.main(null));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameLauncher();
            }
        });
    }
}
