package edu.uca.swe.GUI.Controllers;

import edu.uca.swe.Game.Board;
import edu.uca.swe.Game.Move;
import edu.uca.swe.Game.Pieces.Piece;
import edu.uca.swe.GUI.Panels.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanelController implements ActionListener {
    private GamePanel gamePanel;
    private Board board;
    private Piece selectedPiece;
    private int selectedRow;
    private int selectedCol;

    public GamePanelController(GamePanel gamePanel, Board board) {
        this.gamePanel = gamePanel;
        this.board = board;
        this.selectedPiece = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get clicked tile
        JButton clickedButton = (JButton) e.getSource();

        // Find pos of clicked tile
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

        // Handle click
        //TODO fix logic
        if (selectedPiece == null) {
            Piece piece = board.getBoard()[row][col];
            if (piece != null) {
                selectedPiece = piece;
                selectedRow = row;
                selectedCol = col;
                System.out.println("Selected piece: " + piece.getClass().getSimpleName() + " at [" + row + "," + col + "]");
            }
        } else {
            Move move = new Move(board, selectedPiece, row, col);
            if (move.isValid()) {
                move.makeMove();
                System.out.println("Move made to [" + row + "," + col + "]");
                selectedPiece = null;
            } else {
                System.out.println("Invalid move.");
            }
        }

        gamePanel.updateBoard();
    }
}