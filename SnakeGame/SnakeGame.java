/** 
* SnakeGame.java
* @author Sinclair DeYoung
* @purpose The Snake game
* @since 12-02-2025
 * @version 1.1
 */

package SnakeGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class SnakeGame extends JFrame implements ActionListener {

    // Constants for the game
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static final int DELAY = 70; // delay in milliseconds
    private GameMenu gameMenu = GameMenu.PLAY;

    // Game variables
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;

    // GUI components
    javax.swing.Timer timer;
    Random random = new Random();
    GamePanel gamePanel = new GamePanel();
    JPanel scorePanel = new JPanel();
    JLabel scoreLabel = new JLabel();

    public SnakeGame() {
        // Set up the game frame
        setTitle("Snake Game");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);

        // Set up the game panel
        gamePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setLayout(null);

        // Set up the score panel
        scorePanel.setPreferredSize(new Dimension(SCREEN_WIDTH, 50));
        scorePanel.setBackground(Color.GRAY);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scorePanel.add(scoreLabel);

        // Add panels to the frame
        add(gamePanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.NORTH);
        
        // start the game
        startGame(); // start the game
        
        setVisible(true);
        gamePanel.requestFocusInWindow();
    }

    public void startGame() {
        // Initialize game variables
        direction = 'R';
        bodyParts = 6;
        applesEaten = 0;
        running = true;
        x[0] = 0;
        y[0] = 0;

        // Place the first apple
        newApple();

        // Start the timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void draw(Graphics g){

        if (gameMenu == GameMenu.PLAY) {

            if(running) {
                // Adds grid lines to the panel 
                // for (int i=0; i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                //     g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                //     g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
                // }
                
                // draw apple
                g.setColor(Color.RED);
                g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

                // draw snake 
                for (int i = 0; i < bodyParts; i++){
                    // draw snake head
                    if(i==0){
                        g.setColor(new Color(3,252,53));
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    } else {
                    // draw snake body
                        g.setColor(new Color(3,252,140));
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
                g.setColor(new Color(198, 64, 222));
                g.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+ applesEaten))/2, g.getFont().getSize());
            } else {
                // Timer stop
                timer.stop();
                // Game over
                gameOver(g);
            }
        } else if (gameMenu == GameMenu.PAUSE) {
            gamePause(g);
        }

    }
    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    enum GameMenu {
        PLAY, PAUSE, RESTART, EXIT
    }

    public void move(){
        if(running) {
            switch (gameMenu) {
                case PLAY -> {
                    for (int i = bodyParts; i > 0; i--) {
                        x[i] = x[i - 1];
                        y[i] = y[i - 1];
                    }
                    // direction control through a switch statement
                    switch (direction) {
                        case 'U' -> y[0] = y[0] - UNIT_SIZE;
                        case 'D' -> y[0] = y[0] + UNIT_SIZE;
                        case 'L' -> x[0] = x[0] - UNIT_SIZE;
                        case 'R' -> x[0] = x[0] + UNIT_SIZE;
                        default -> {}
                    }
                }
                case PAUSE -> {
                    return;
                }
                case RESTART -> startGame();
                case EXIT -> System.exit(0);
                // Default case
                // If the user presses any other key, do nothing
                default -> throw new AssertionError();
            }
        }

    }
    public void checkApple() {
        if(running) {
            if((x[0] == appleX) && (y[0] == appleY)){
                bodyParts++;
                applesEaten++;
                newApple();
            }
        }

    }
    public void checkCollisions() {
        // checks if head collids with body
        if(running) {
            for (int i = bodyParts - 1; i > 0; i--){
                if((x[0] ==x[i]) && y[0] == y[i]){
                    running = false;
                }
            }
            // checks if head touches left border
            if(x[0] < 0 ){
                running = false;
            }
            // checks if head touches right border
            if(x[0] >= SCREEN_WIDTH ){
                running = false;
            }
            // checks if head touches top border
            if(y[0] < 0 ){
                running = false;
            }
            // checks if head touches bottom border
            if(y[0] >= SCREEN_HEIGHT ){
                running = false;
            }

            if (!running){
                timer.stop();
            }
        }
    }

    public void gamePause(Graphics g) {
        // Pasue menu
        // show current score 
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+ applesEaten))/2, g.getFont().getSize());
        // pause menu text
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Paused", (SCREEN_WIDTH - metrics2.stringWidth("Paused"))/2, SCREEN_HEIGHT/2);

        // Restart text
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 35));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press R to Restart", (SCREEN_WIDTH - metrics3.stringWidth("Press R to Restart"))/2, SCREEN_HEIGHT/2 + 50);

        // Exit text
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 35));
        FontMetrics metrics4 = getFontMetrics(g.getFont());
        g.drawString("Press Escape to Exit", (SCREEN_WIDTH - metrics4.stringWidth("Press Escape to Exit"))/2, SCREEN_HEIGHT/2 + 75);


    }



    public void gameOver(Graphics g) {
        // Score text
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+ applesEaten))/2, g.getFont().getSize());

        // Game over text
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

        // Restart text
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 35));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press R to Restart", (SCREEN_WIDTH - metrics3.stringWidth("Press R to Restart"))/2, SCREEN_HEIGHT/2 + 50);

        // Exit text
        g.setColor(new Color(198, 64, 222));
        g.setFont(new Font("Bauhaus 93", Font.BOLD, 35));
        FontMetrics metrics4 = getFontMetrics(g.getFont());
        g.drawString("Press Escape to Exit", (SCREEN_WIDTH - metrics4.stringWidth("Press Escape to Exit"))/2, SCREEN_HEIGHT/2 + 75);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // the game actions
        if(running && gameMenu == GameMenu.PLAY) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter {
        // Allows correct mapping of user keys
        @Override
        public void keyPressed(KeyEvent e){
            // System.out.println("in key pressed");
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if(direction != 'R') direction = 'L';
                }
                case KeyEvent.VK_RIGHT -> {
                    if(direction != 'L') direction = 'R';
                }
                case KeyEvent.VK_UP -> {
                    if(direction != 'D') direction = 'U';
                }
                case KeyEvent.VK_DOWN -> {
                    if(direction != 'U') direction = 'D';
                }
                case KeyEvent.VK_P -> {
                    gameMenu = (gameMenu == GameMenu.PLAY) ? GameMenu.PAUSE : GameMenu.PLAY;
                }
                case KeyEvent.VK_R -> {
                    StartGame();
                }
                case KeyEvent.VK_ESCAPE -> {
                    gameMenu = GameMenu.EXIT;
                }
                // Default case
                // If the user presses any other key, do nothing
                default -> {
                    // System.out.println("in default case");
                    // throw new AssertionError();
                }
        }
    }

    public void StartGame() {
        // System.out.println("in start game");
        gameMenu = GameMenu.PLAY;
        // System.out.println("gameMenu = " + gameMenu);
        startGame();
    }
}

class GamePanel extends JPanel {
    // Game panel class
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}
}

