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

        //First click will select the game piece
        //second click will execute move and deselect piece
        if(selectedPiece == null) {
            if(board.getPieceAt(row, col) != null) {
                if(board.getPieceAt(row, col).getColor().equals(Color)) {
                    selectedPiece = board.getPieceAt(row, col);
                }
            }
        }else {
            try {
                handleTileClick(row, col);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void handleTileClick(int row, int col) throws IOException {
        // Attempt a move
        selectedRow = selectedPiece.getRow();
        selectedCol = selectedPiece.getCol();
        Move move = new Move(board, selectedPiece, selectedRow, selectedCol, row, col);
        // If move is valid, make the move
        if (move.isValid()) {
            move.makeMove();
            gamePanel.revalidate();
            gamePanel.repaint();
            gamePanel.getClient().sendToServer
                    (gamePanel.getPlayerRole() + " moved " + selectedPiece.toString() +" at [" + selectedRow + "," + selectedCol + "] to [" + row + "," + col + "]");
        } else {
            System.out.println("Invalid move.");
        }
        // Deselect piece
        selectedPiece = null;

        // Update board
        gamePanel.updateBoard();
    }

    public void handleOther(String msg) throws IOException {
        String[] splitMsg = msg.split(" ");
        int[] start = {splitMsg[4].charAt(1) - '0', splitMsg[4].charAt(3) - '0'};
        int[] end = {splitMsg[6].charAt(1) - '0', splitMsg[6].charAt(3) - '0'};
        selectedPiece = board.getPieceAt(start[0], start[1]);
        handleTileClick(end[0], end[1]);
    }
}