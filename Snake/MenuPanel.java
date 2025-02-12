package Snake;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel {

    private JFrame frame;

    public MenuPanel(JFrame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        
        // create buttons
        JButton startButton = new JButton("Start Game");
        JButton exitButton = new JButton("Exit");

        // add action listeners to button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // remove the menu pannel and add the game panel
                frame.getContentPane().removeAll();
                frame.add(new GamePanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        // add buttons to the pannel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        this.add(buttonPanel, BorderLayout.CENTER);

    }
    
}
