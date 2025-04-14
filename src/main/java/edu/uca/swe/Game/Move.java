package edu.uca.swe.Game;

import edu.uca.swe.Game.Pieces.*;

public class Move {
    private Piece piece;
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private Board board;

    public Move(Board board, Piece piece, int toRow, int toCol) {
        this.board = board;
        this.piece = piece;
        this.fromRow = piece.getRow();
        this.fromCol = piece.getCol();
        this.toRow = toRow;
        this.toCol = toCol;
    }

    public boolean isValid() {
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) {
            return false;
        }

        piece.isValidMove(toRow, toCol);

        Piece destinationPiece = board.getBoard()[toRow][toCol];
        if (destinationPiece != null && destinationPiece.getColor().equals(piece.getColor())) {
            return false;
        }

        return true;
    }

    public void makeMove() {
        if (isValid()) {
            Piece[][] boardArray = board.getBoard();
            boardArray[fromRow][fromCol] = null;
            piece.setPosition(toRow, toCol);
            boardArray[toRow][toCol] = piece;
        } else {
            System.out.println("Invalid move.");
        }
    }
}