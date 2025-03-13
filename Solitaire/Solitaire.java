/**
 * @author Sinclair DeYoung
 * @Purpose The Solitaire game
 * @since 10-03-2025
 */
package Solitaire;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Solitaire extends JFrame implements ActionListener {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player_turn;

    public Solitaire() {
        // builds the frame 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Solitaire");
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.requestFocusInWindow();

        // sets the colors for the frame plus the title
        textfield.setBackground(new Color(120, 6, 116));
        textfield.setForeground(new Color(4,190, 196));
        textfield.setFont(new Font("Aris", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Solitaire");
        textfield.setOpaque(true);

        // set the title panel size
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, SCREEN_WIDTH, 100);

        // set the button panel size
        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        // create the buttons for the button panel
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // starts the frame
    }
    public void actionPerformed(ActionEvent e) {
  
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                System.out.println("Button " + i + " was pressed");
            }
        }
    }
}