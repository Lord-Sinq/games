/*
 * @author Sinclair DeYoung
 * @Purpose The TicTacToe game
 * @since 12-02-2025
 */
package TicTacToe;

import javax.swing.*;
public class TTTRun {
    public static void main(String[] args) {
        // Create a new TicTacToe object
        // System.out.println("Creating a new TicTacToe object");
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
