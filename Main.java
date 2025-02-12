/*
 * @purpose Used as a game lauch menu
 * @author Sinclair DeYoung
 * @since 12-02-2025
 */

 // Imports for logic
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
// Import for the games
import TicTacToe.TTTRun;
import Snake.SnakeGame;

public class Main {

    // initialize the JFrame and JButtons
    private JFrame frame;
    private JButton tttButton;
    private JButton snakeButton;
    static final int SCREEN_WIDTH = 300;
    static final int SCREEN_HEIGHT = 200;

    public Main() {
        createAndShowGUI();
    }
    private void createAndShowGUI() {
        frame = new JFrame("Game Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setLayout(new FlowLayout());

        // create buttons
        tttButton = new JButton("Tic Tac Toe");
        snakeButton = new JButton("Snake Game");

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
        new TTTRun();

    }
    private void lauchSnake() {
        // lanuches Snake Game
        new SnakeGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
