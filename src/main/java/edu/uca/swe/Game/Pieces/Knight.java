package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

import static java.lang.Math.abs;

public class Knight extends Piece{

    //Constructor
    public Knight(String color, int startRow, int startCol) {
        super(startRow, startCol, color);
    }

    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
    public boolean isValidMove(int toRow, int toCol, Board board) {
        int fromRow = getRow();
        int fromCol = getCol();

        // First check if the target square is within bounds
        if (!board.isWithinBounds(toRow, toCol)) {
            return false;
        }

        // Check if target square has a piece of the same color
        Piece targetPiece = board.getPieceAt(toRow, toCol);
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        // Knights move in L-shape: 2 squares in one direction and 1 square perpendicular
        boolean validMove = (abs(fromCol - toCol) == 2 && abs(fromRow - toRow) == 1) ||
                (abs(fromCol - toCol) == 1 && abs(fromRow - toRow) == 2);

        if (!validMove) {
            return false;
        }

        // Check if this move would put our king in check
        // Only check if there's a king on the board
        boolean kingExists = false;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.getPieceAt(r, c);
                if (piece instanceof King && piece.getColor().equals(this.getColor())) {
                    kingExists = true;
                    break;
                }
            }
            if (kingExists) break;
        }

        if (kingExists && board.doesPutKingInCheck(this, toRow, toCol)) {
            return false;
        }

        return true;
    }
}