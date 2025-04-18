package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

import static java.lang.Math.abs;

public class King extends Piece {
    //Constructor
    public King(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }

    //Checks to make sure move is valid
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

        // Kings can move one square in any direction
        int rowDiff = Math.abs(fromRow - toRow);
        int colDiff = Math.abs(fromCol - toCol);

        return rowDiff <= 1 && colDiff <= 1;
    }
}