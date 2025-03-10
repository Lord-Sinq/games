/*
 * @author Sinclair DeYoung
 * @Purpose The Solitaire game
 * @since 10-03-2025
 */
package Solitaire;

import javax.swing.*;
public class SolitaireRun {
    public static void main(String[] args) {
        // Create a new Solitaire object
        // System.out.println("Creating a new Solitaire object");
        SwingUtilities.invokeLater(() -> new Solitaire());
    }
}
