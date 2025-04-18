package edu.uca.swe.GUI.Controllers;

import edu.uca.swe.Game.Board;
import edu.uca.swe.Game.Move;
import edu.uca.swe.Game.Pieces.Piece;
import edu.uca.swe.GUI.Panels.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameController implements ActionListener {
    private GamePanel gamePanel;
    private Board board;
    private Piece selectedPiece;
    private int selectedRow;
    private int selectedCol;
    private String Color;

    public GameController(GamePanel gamePanel, Board board) {
        this.gamePanel = gamePanel;
        this.board = board;
        this.selectedPiece = null;

        //set Host to White and Client to Black
        if (gamePanel.getPlayerRole() == "host"){
            Color = "White";
        }else{ Color = "Black";}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if it's the player's turn
        if (!board.getCurrentTurn().equals(Color)) {
            System.out.println("Not your turn! Current turn: " + board.getCurrentTurn());
            return;
        }

        // identify which tile was clicked
        JButton clickedButton = (JButton) e.getSource();

        //Find the row/col of the button click
        int row = -1, col = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (clickedButton == gamePanel.getTileButton(i, j)) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // If player is host (white), flip the coordinates
        if (gamePanel.getPlayerRole().equals("host")) {
            row = 7 - row;
            col = 7 - col;
        }

        //First click will select the game piece
        //second click will execute move and deselect piece
        if(selectedPiece == null) {
            if(board.getPieceAt(row, col) != null) {
                if(board.getPieceAt(row, col).getColor().equals(Color)) {
                    selectedPiece = board.getPieceAt(row, col);
                }
            }
        } else {
            try {
                handleTileClick(row, col);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void handleTileClick(int row, int col) throws IOException {
        // Check if it's still the player's turn
        if (!board.getCurrentTurn().equals(Color)) {
            System.out.println("Not your turn! Current turn: " + board.getCurrentTurn());
            selectedPiece = null;
            return;
        }

        // Attempt a move
        selectedRow = selectedPiece.getRow();
        selectedCol = selectedPiece.getCol();
        Move move = new Move(board, selectedPiece, selectedRow, selectedCol, row, col);

        // If move is valid, make the move
        if (move.isValid()) {
            // Store the piece that might be captured
            Piece capturedPiece = board.getPieceAt(row, col);

            move.makeMove();
            gamePanel.revalidate();
            gamePanel.repaint();

            // Store the color before deselecting the piece
            String pieceColor = selectedPiece.getColor();

            // Send move to server
            gamePanel.getClient().sendToServer
                    (gamePanel.getPlayerRole() + " moved " + selectedPiece.toString() +" at [" + selectedRow + "," + selectedCol + "] to [" + row + "," + col + "]");

            // Deselect piece
            selectedPiece = null;

            // Update board
            gamePanel.updateBoard();

            // Check if a king was captured
            if (capturedPiece != null && capturedPiece.getClass().getSimpleName().equals("King")) {
                // The game is over, the current player wins
                String winner = pieceColor;
                // Show game over panel for the capturing player
                gamePanel.getClient().getController().showGameOver(winner);
                // Send game over message to opponent
                gamePanel.getClient().sendToServer("Game Over! Winner: " + winner);
                return;
            }
        } else {
            System.out.println("Invalid move.");
            selectedPiece = null;
        }
    }

    public void handleOther(String msg) throws IOException {
        // Check if this is a game over message
        if (msg.startsWith("Game Over!")) {
            String[] parts = msg.split(" ");
            String winner = parts[3]; // The color of the winner
            gamePanel.getClient().getController().showGameOver(winner);
            return;
        }

        // Handle regular move messages
        String[] splitMsg = msg.split(" ");
        String[] startCoords = splitMsg[4].replace("[", "").replace("]", "").split(",");
        String[] endCoords = splitMsg[6].replace("[", "").replace("]", "").split(",");

        int startRow = Integer.parseInt(startCoords[0]);
        int startCol = Integer.parseInt(startCoords[1]);
        int endRow = Integer.parseInt(endCoords[0]);
        int endCol = Integer.parseInt(endCoords[1]);

        selectedPiece = board.getPieceAt(startRow, startCol);
        if (selectedPiece != null) {
            Move move = new Move(board, selectedPiece, startRow, startCol, endRow, endCol);
            if (move.isValid()) {
                move.makeMove();
                gamePanel.updateBoard();
                gamePanel.revalidate();
                gamePanel.repaint();
            }
            selectedPiece = null;
        }
    }
}