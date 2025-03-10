package Snake;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 70;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public GamePanel(){
        // setting the game panel and starting the game
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        StartGame();
    }
    public void StartGame() {
        // starts the game
        bodyParts = 6;
        x[0] = 0;
        y[0] = 0;
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){

        if(running) {
            
            /* Adds grid lines to the panel 
            for (int i=0; i<SCREEN_HEIGHT/UNIT_SIZE;i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            */
            // draw apple
            g.setColor(Color.red);
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
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+ applesEaten))/2, g.getFont().getSize());
        } else {
            gameOver(g);
        }

    }
    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    public void move(){
        
        for (int i = bodyParts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }
    public void checkApple() {

        if((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }

    }
    public void checkCollisions() {
        // checks if head collids with body
        for (int i = bodyParts; i > 0; i--){
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
        if(y[0] >= SCREEN_WIDTH ){
            running = false;
        }

        if (!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {
        // Score text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+ applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+ applesEaten))/2, g.getFont().getSize());

        // Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);


        // Restart text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 35));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press Enter to Restart", (SCREEN_WIDTH - metrics3.stringWidth("Press Enter to Restart"))/2, SCREEN_HEIGHT/2 + 50);

        // Exit text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 35));
        FontMetrics metrics4 = getFontMetrics(g.getFont());
        g.drawString("Press Escape to Exit", (SCREEN_WIDTH - metrics4.stringWidth("Press Escape to Exit"))/2, SCREEN_HEIGHT/2 + 100);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // the game actions
        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();


    }

    public class MyKeyAdapter extends KeyAdapter { // key listener
        // Allows correct mapping of user keys
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if(!running){
                        StartGame();
                    }
                    break;
                // Exit game
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                // Restart game
                case KeyEvent.VK_ENTER:
                    if(!running){
                        StartGame();
                    }
                    break;

// imput map and action event 

                // Default case
                // If the user presses any other key, do nothing
                default:
                    break;
            }
            // Restart game
            // If the game is over and the user presses enter, restart the game
            // If the game is running and the user presses enter, do nothing
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!running) {
                    StartGame();
                }
            }
            // Exit game
            // If the game is over and the user presses escape, exit the game
            // If the game is running and the user presses escape, exit the game
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }
}
