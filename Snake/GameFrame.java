package Snake;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    
    public GameFrame() {
        
        // Setting the frame information
        // ensure the panel is properly initialized
        GamePanel gamePanel = new GamePanel(this);
        this.add(gamePanel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        

        // Pack the frame to ensure proper sizing
        this.pack();
        // set the frame visible after adding components
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        gamePanel.requestFocusInWindow();
    }
}
