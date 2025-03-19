/**
 * @author Sinclair DeYoung
 * @Purpose The Snake game
 * @since 12-02-2025
 * @version 1.1
 */

package SnakeGame;

import javax.swing.SwingUtilities;

public class SnakeRun {
    public static void main(String[] args) {

        // launch the SnakeGame on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}