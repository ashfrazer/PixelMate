package edu.uca.swe.Game.TurnManager;

/*
    This part of the program handles the turns of each player:
    - if player 1 moves to a valid space on the board then players 1 turn will end
    and transfer to player 2.
    - There will be a timer that if a player does not make a valid move in that time
    then there turn will be forfeited.
    - TurnManager will end if one player leaves or game ends in a win/loss/stalemate

    Should run once Player class is made
 */

/*
import edu.uca.swe.Game.Pieces.Piece;
import java.util.Timer;
import java.util.TimerTask;

public class TurnManager {
    private Player currentPlayer;
    private final Player player1;
    private final Player player2;
    private Timer turnTimer;
    private boolean isGameActive;
    private static final int TURN_TIME_LIMIT = 45000; //45 seconds per turn

    public TurnManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.isGameActive = true; //Game starts right away
        this.currentPlayer = player1; //Game starts with player 1
        turnTimer();
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player player) {
        if (player == player1 || player == player2) {
            currentPlayer = player;
            turnTimer();
        }
    }
    public boolean isGameActive() {
        return isGameActive;
    }
    public void processMove(Piece piece, int toRow, int toCol) {
        if (isGameActive) {
            System.out.println(currentPlayer.getName() + " moves " + piece.getName() + " to (" + toRow + ", " + toCol + ")");
            switchTurn();
        }
    }
    public void switchTurn() {
        if (isGameActive) {
            //Checks if the current player is player one, if true switches to player2 if false changes to player1
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            turnTimer();
        }
    }
    private void turnTimer() {
        if (turnTimer != null) {
            turnTimer.cancel(); // Cancel any existing timer
        }

        Timer newTimer = new Timer();
        newTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isGameActive) {
                    System.out.println(currentPlayer.getName() + " took too long! Turn forfeited.");
                    switchTurn();
                }
            }
        }, TURN_TIME_LIMIT);
    }
}*/
