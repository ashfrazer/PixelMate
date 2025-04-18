package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    //Constructor
    public Pawn(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }


    public String toString(){return "p";}

    @Override
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

        // Pawns can only move forward
        int direction = this.getColor().equals("White") ? 1 : -1;
        if ((toRow - fromRow) * direction <= 0) {
            return false;
        }

        // Check if moving diagonally (capturing)
        if (fromCol != toCol) {
            // Must be moving exactly one square diagonally
            if (Math.abs(fromCol - toCol) != 1 || Math.abs(fromRow - toRow) != 1) {
                return false;
            }
            // Must be capturing an opponent's piece
            return targetPiece != null && !targetPiece.getColor().equals(this.getColor());
        }

        // Moving straight forward
        // Can't capture when moving straight
        if (targetPiece != null) {
            return false;
        }

        // Can move one or two squares forward from starting position
        if (fromRow == (this.getColor().equals("White") ? 1 : 6)) {
            return Math.abs(toRow - fromRow) <= 2;
        }

        // Otherwise can only move one square forward
        return Math.abs(toRow - fromRow) == 1;
    }
}
