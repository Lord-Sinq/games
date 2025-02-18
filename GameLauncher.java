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

public class GameLauncher {

    // initialize the JFrame and JButtons
    private JFrame frame;
    private JButton tttButton;
    private JButton snakeButton;
    static final int SCREEN_WIDTH = 300;
    static final int SCREEN_HEIGHT = 200;

    public GameLauncher() {
        createAndShowGUI();
    }
    private void createAndShowGUI() {
        // System.out.println("Creating the GUI");
        frame = new JFrame("Game Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new FlowLayout());

        

        // create buttons
        // System.out.println("Creating buttons");
        tttButton = new JButton("Tic Tac Toe");
        snakeButton = new JButton("Snake Game");

        // add listeners to buttons
        tttButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // System.out.println("Tic Tac Toe launch button in the Active listener");
                lauchTTT();
            }
        });
        snakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // System.out.println("Snake Game launch button in the Active listener");
                lauchSnake();
            }
        });

        // add buttons to the frame
        frame.add(tttButton);
        frame.add(snakeButton);
        // center the frame on the screen
        frame.setLocationRelativeTo(null);
        //make the frame visiable
        frame.setVisible(true);


    }
    private void lauchTTT() {
        // lanuches tic tac toe
        // System.out.println("Tic Tac Toe launch area");
        // new TTTRun();
        SwingUtilities.invokeLater(() -> TTTRun.main(null));  
    }
    private void lauchSnake() {
        // lanuches Snake Game
        // System.out.println("Snake Game launch area");
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
