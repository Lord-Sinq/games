/*
 * @purpose Used as a game lauch menu
 * @author Sinclair DeYoung
 * @since 12-02-2025
 */

// Import for the games
import TicTacToe.TicTacToe;
import Snake.GameFrame;

public class Main {

    public static void main(String[] args) {
        // test if games launch
        new TicTacToe();

        new GameFrame();
    }
    
}
