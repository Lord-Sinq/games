package Snake;

import javax.swing.SwingUtilities;

public class SnakeGame {
    public static void main(String[] args) {
        // Create a new GameFrame object
        SwingUtilities.invokeLater(() -> new GameFrame());   
    }
}
