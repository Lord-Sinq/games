/*
 * @author Sinclair DeYoung
 * @Purpose The TicTacToe game
 * @since 12-02-2025
 */
package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player_turn;

    TicTacToe() {
        // builds the frame 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tic Tac Toe");
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // sets the colors for the frame plus the title
        textfield.setBackground(new Color(120, 6, 116));
        textfield.setForeground(new Color(4,190, 196));
        textfield.setFont(new Font("Aris", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("TicTacToe");
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
        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
        // after the frame has loaded and both panels are loaded the firstTurn function is run
        firstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // loop for as many buttons there are
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                // checks player turn is X
                if (player_turn) {
                    // colors X tthen switches to other player
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player_turn = false;
                        textfield.setText("O Turn");
                        check();
                    }
                // checks player turn is O
                } else {
                    // colors the O then switches to other player
                    if (buttons[i].getText() == "") {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player_turn = true;
                        textfield.setText("X Turn");
                        check();
                    }

                }

            }
        }

    }

    public void firstTurn() {
        // keeps the turn message away for a bit so you can see the title
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // picks a random player to start
        if (random.nextInt(2) == 0) {
            player_turn = true;
            textfield.setText("X turn");
        } else {
            player_turn = false;
            textfield.setText("O turn");
        }
    }
    
    public void check() {
        // if statements to check if there is 3 in a row
        
        // winning conditions
        int[][] winningConditions = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        
        // checks for X win or O win
        for( int[] condition : winningConditions){
            if (buttons[condition[0]].getText().equals("X") &&
            buttons[condition[1]].getText().equals("X") &&
            buttons[condition[2]].getText().equals("X")){
                xWins(condition[0], condition[1],condition[2]);
                return;
            }
            if (buttons[condition[0]].getText().equals("O") &&
            buttons[condition[1]].getText().equals("O") &&
            buttons[condition[2]].getText().equals("O")){
                oWins(condition[0], condition[1],condition[2]);
                return;
            }
        }
        
        // check for cats game
        boolean isCatGame = true;
        for(int i = 0; i < buttons.length; i++){
            if (buttons[i].getText().isEmpty()){
                isCatGame = false;
                return;
            }
        }
        if (isCatGame) {
            catsGame(0, 1, 2, 3, 4,5,6,7,8);
        }

        // the logic above does the same as the logic below
        
        // // check X win
        // if(
            //     (buttons[0].getText()=="X") && 
        //     (buttons[1].getText()=="X") &&
        //     (buttons[2].getText()=="X")
        //     ){
        //     xWins(0, 1,2);
        // }
        // if(
        //     (buttons[3].getText()=="X") && 
        //     (buttons[4].getText()=="X") &&
        //     (buttons[5].getText()=="X")
        //     ){
        //     xWins(3, 4,5);
        // }
        // if(
        //     (buttons[6].getText()=="X") && 
        //     (buttons[7].getText()=="X") &&
        //     (buttons[8].getText()=="X")
        //     ){
        //     xWins(6, 7,8);
        // }
        // if(
        //     (buttons[0].getText()=="X") && 
        //     (buttons[3].getText()=="X") &&
        //     (buttons[6].getText()=="X")
        //     ){
        //     xWins(0, 3,6);
        // }
        // if(
        //     (buttons[1].getText()=="X") && 
        //     (buttons[4].getText()=="X") &&
        //     (buttons[7].getText()=="X")
        //     ){
        //     xWins(1, 4,7);
        // }
        // if(
        //     (buttons[2].getText()=="X") && 
        //     (buttons[5].getText()=="X") &&
        //     (buttons[8].getText()=="X")
        //     ){
        //     xWins(2, 5,8);
        // }
        // if(
        //     (buttons[0].getText()=="X") && 
        //     (buttons[4].getText()=="X") &&
        //     (buttons[8].getText()=="X")
        //     ){
        //     xWins(0, 4,8);
        // }
        // if(
        //     (buttons[2].getText()=="X") && 
        //     (buttons[4].getText()=="X") &&
        //     (buttons[6].getText()=="X")
        //     ){
        //     xWins(2, 4,6);
        // }
        // // check O win
        // if(
        //     (buttons[0].getText()=="O") && 
        //     (buttons[1].getText()=="O") &&
        //     (buttons[2].getText()=="O")
        //     ){
        //     oWins(0, 1,2);
        // }
        // if(
        //     (buttons[3].getText()=="O") && 
        //     (buttons[4].getText()=="O") &&
        //     (buttons[5].getText()=="O")
        //     ){
        //     oWins(3, 4,5);
        // }
        // if(
        //     (buttons[6].getText()=="O") && 
        //     (buttons[7].getText()=="O") &&
        //     (buttons[8].getText()=="O")
        //     ){
        //     oWins(6, 7,8);
        // }
        // if(
        //     (buttons[0].getText()=="O") && 
        //     (buttons[3].getText()=="O") &&
        //     (buttons[6].getText()=="O")
        //     ){
        //     oWins(0, 3,6);
        // }
        // if(
        //     (buttons[1].getText()=="O") && 
        //     (buttons[4].getText()=="O") &&
        //     (buttons[7].getText()=="O")
        //     ){
        //     oWins(1, 4,7);
        // }
        // if(
        //     (buttons[2].getText()=="O") && 
        //     (buttons[5].getText()=="O") &&
        //     (buttons[8].getText()=="O")
        //     ){
        //     oWins(2, 5,8);
        // }
        // if(
        //     (buttons[0].getText()=="O") && 
        //     (buttons[4].getText()=="O") &&
        //     (buttons[8].getText()=="O")
        //     ){
        //     oWins(0, 4,8);
        // }
        // if(
        //     (buttons[2].getText()=="O") && 
        //     (buttons[4].getText()=="O") &&
        //     (buttons[6].getText()=="O")
        //     ){
        //     oWins(2, 4,6);
        // }


    }

    public void xWins(int a, int b, int c) {
        // if X wins
        buttons[a].setBackground(Color.red);
        buttons[b].setBackground(Color.red);
        buttons[c].setBackground(Color.red);
        // disables buttons
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins!");
    }

    public void oWins(int a, int b, int c) {
        // if O wins 
        buttons[a].setBackground(Color.blue);
        buttons[b].setBackground(Color.blue);
        buttons[c].setBackground(Color.blue);
        // disables buttons
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins!");
    }

    public void catsGame(int a, int b, int c, int d, int e, int f, int g, int h, int j){
        // change background color if ther is a cats game
        buttons[a].setBackground(Color.orange);
        buttons[b].setBackground(Color.orange);
        buttons[c].setBackground(Color.orange);
        buttons[d].setBackground(Color.orange);
        buttons[e].setBackground(Color.orange);
        buttons[f].setBackground(Color.orange);
        buttons[g].setBackground(Color.orange);
        buttons[h].setBackground(Color.orange);
        buttons[j].setBackground(Color.orange);
        // disables buttons
        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textfield.setText("Cats Game!");
    }
}
