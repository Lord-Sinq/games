package Snake;

import java.awt.Panel;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    GameFrame() {
        
        // Setting the frame information
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // ensure the panel is properly initialized
        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);

        // Pack the frame to ensure proper sizing
        this.pack();
        // set the frame visible after adding components
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
