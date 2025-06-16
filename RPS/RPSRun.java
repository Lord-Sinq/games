/*
 * Rock Paper Scissors Game laucher
 * @author Sinclair DeYoung
 * @version 1.0
 * @since 10-03-2025
 * @purpose A simple Rock Paper Scissors game launcher using Java Swing
 */
package RPS;

import javax.swing.SwingUtilities;

/**
 * Main class to run the Rock Paper Scissors game.
 */
public class RPSRun {
    public static void main(String[] args) {
        // Create a new RockPaperScissors object
        // System.out.println("Creating a new RockPaperScissors object");
        SwingUtilities.invokeLater(() -> new RockPaperScissors());
    }
}
