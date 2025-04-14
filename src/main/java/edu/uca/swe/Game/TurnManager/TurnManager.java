package edu.uca.swe.Game.TurnManager;

import edu.uca.swe.GUI.Panels.GamePanel;
import edu.uca.swe.Game.Board;
import edu.uca.swe.Game.Pieces.Pawn;
import edu.uca.swe.Game.Pieces.Piece;
import edu.uca.swe.Game.Player.Player;

import java.util.Timer;
import java.util.TimerTask;

// TODO: make a visual countdown on a side panel of the board

public class TurnManager {
    private Player currentPlayer;
    private final Player player1;
    private final Player player2;
    private Timer turnTimer;
    private boolean isGameActive;
    private GamePanel gamePanel;
    private Board board;
    private static final int TURN_TIME_LIMIT = 45000; // 45 seconds per turn

    // Sets initial state and starts the first players turn
    public TurnManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.isGameActive = true; // Game starts right away
        this.currentPlayer = player1; // Game starts with player 1
        turnTimer(); // Starts turn timer
        this.gamePanel = gamePanel;
    }

    // Returns current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // Changing current player
    public void setCurrentPlayer(Player player) {
        if (player == player1 || player == player2) {
            currentPlayer = player;
            turnTimer(); // Restarts timer for new player
        }
    }

    // Returns whether game is currently active
    public boolean isGameActive() {
        return isGameActive;
    }

    // Active when move is made and should validate move
    public void processMove(Piece piece, int toRow, int toCol, Board board) {
        if (!isGameActive) return;

        //TODO: wait until rules class is made
        //make sure peices rules are valid for move, if move is valid
        //update pieces position, switch turn after move

        // Switch turns
        switchTurn();
    }


    //Switches current player to the other player and restarts timer
    public void switchTurn() {
        if (isGameActive) {
            //Checks if the current player is player one, if true switches to player2 if false changes to player1
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            turnTimer();
        }
    }

    // Resets turn timer for current player
    private void turnTimer() {
        // Cancels and clean up existing timer if it is already running
        if (turnTimer != null) {
            turnTimer.cancel();
            turnTimer.purge();
        }

        // Starts a new timer for current turn
        Timer newTimer = new Timer();
        newTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isGameActive) {
                    System.out.println(currentPlayer.getUsername() + " took too long! Turn forfeited.");
                    switchTurn(); // Automatically switches turn if time runs out
                }
            }
        }, TURN_TIME_LIMIT);

        turnTimer = newTimer; // Saves the new timer instance
    }

    //Call cancleTimer to Game class to end the timer
    public void cancelTimer() {
        if (turnTimer != null) {
            turnTimer.cancel();
            turnTimer.purge();
            turnTimer = null;
        }
    }
}
