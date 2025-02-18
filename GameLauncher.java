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
import java.util.concurrent.Flow;
import javax.imageio.IIOException;

public class GameLauncher {

    // initialize the JFrame and JButtons
    private JFrame frame;
    private JButton tttButton;
    private JButton snakeButton;
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
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // add space between title and buttons

        // button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false); // make the panel transparent

        // create buttons
        tttButton = new JButton("Tic Tac Toe");
        snakeButton = new JButton("Snake Game");
        styleButton(tttButton); // add style to buttons
        styleButton(snakeButton); // add style to buttons
        tttButton.addActionListener(l -> lauchTTT()); // lambda expression
        snakeButton.addActionListener(l -> lauchSnake()); // lambda expression
        buttonPanel.add(tttButton);
        buttonPanel.add(snakeButton);

        mainPanel.add(buttonPanel); // add button panel to main panel

        // add listeners to buttons
        tttButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                lauchTTT();
            }
        });
        snakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                lauchSnake();
            }
        });

        //make the frame visiable
        frame.setVisible(true);
    }

    // adds universal style to buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setForeground(Color.DARK_GRAY);
        button.setBackground(Color.MAGENTA);
        button.setFocusPainted(false);

        // hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.PINK);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.MAGENTA);
            }
        });

    }

    private void lauchTTT() {
        // lanuches tic tac toe
        JOptionPane.showMessageDialog(frame, "Lauching Tic Tac Toe...");
        SwingUtilities.invokeLater(() -> TTTRun.main(null));  
    }
    private void lauchSnake() {
        // lanuches Snake Game
        JOptionPane.showMessageDialog(frame, "Lauching Snake Game...");
        SwingUtilities.invokeLater(() -> SnakeGame.main(null));
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
