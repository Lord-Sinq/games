/*
* Rock Paper Scissors Game 
 * This is a simple implementation of the Rock Paper Scissors game in Java.
 * The game allows a user to play against the computer.
 * The user can choose rock, paper, or scissors,
 * and the computer randomly selects one of the three options.
 * The game keeps track of the score and displays the result of each round.
 * The game continues until the user decides to quit.
 * This game will be build with Java Swing for the GUI.
 * The game will have a simple interface with buttons for each option.
 * The buttons will be labeled "Rock", "Paper", and "Scissors".
 * The game will also have a label to display the score.
 * The score will be displayed as "Player: X, Computer: Y",
 * where X is the player's score and Y is the computer's score.
 * The game will also have a label to display the result of each round.
 * @author Sinclair DeYoung
 * @version 1.0
 * @since 10-03-2025
 * @purpose A simple Rock Paper Scissors game using Java Swing
*/
package RPS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;


public class RockPaperScissors extends JFrame implements ActionListener {
    private int playerScore = 0;
    private int computerScore = 0;
    private JLabel scoreLabel;
    private JLabel resultLabel;
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        scoreLabel = new JLabel("Player: 0, Computer: 0");
        resultLabel = new JLabel("Choose your move!");

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        add(scoreLabel);
        add(resultLabel);
        add(rockButton);
        add(paperButton);
        add(scissorsButton);

        setVisible(true);
    }

    private String getComputerMove() {
        String[] moves = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        return moves[random.nextInt(moves.length)];
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "It's a tie!";
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                   (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                   (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player wins!";
        } else {
            return "Computer wins!";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerMove = e.getActionCommand();
        String computerMove = getComputerMove();
        String result = determineWinner(playerMove, computerMove);

        if (result.equals("Player wins!")) {
            playerScore++;
        } else if (result.equals("Computer wins!")) {
            computerScore++;
        }

        scoreLabel.setText("Player: " + playerScore + ", Computer: " + computerScore);
        resultLabel.setText("You chose " + playerMove + ", Computer chose " + computerMove + ". " + result);
    }
}




