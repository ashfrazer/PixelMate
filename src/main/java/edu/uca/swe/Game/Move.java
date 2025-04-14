package edu.uca.swe.Game;

import edu.uca.swe.Game.Pieces.*;

public class Move {
    private Piece piece;
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private Board board;

    public Move(Board board, Piece piece, int fromRow, int fromCol, int toRow, int toCol) {
        this.board = board;
        this.piece = piece;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    // Check if move is legal
    public boolean isValid() {
        // Check if out of bounds
        if (toRow < 0 || toRow >= 8 || toCol < 0 || toCol >= 8) return false;

        // Store piece in desired spot
        Piece destinationPiece = board.getBoard()[toRow][toCol];

        // if the piece is the same color, invalid
        if (destinationPiece != null && destinationPiece.getColor().equals(piece.getColor())) return false;

        // return whether valid or not
        return piece.isValidMove(toRow, toCol);
    }


    public void makeMove() {
        // Store piece into new spot if valid move
        if (isValid()) {
            Piece[][] boardArray = board.getBoard();
            boardArray[fromRow][fromCol] = null;
            boardArray[toRow][toCol] = piece;
            piece.setPosition(toRow, toCol);
        } else {
            System.out.println("Invalid move.");
        }
    }

}