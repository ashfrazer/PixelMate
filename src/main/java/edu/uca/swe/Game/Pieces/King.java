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
        int currentRow = getRow();
        int currentCol = getCol();

        //The King can move one space in any direction
        if ((abs(toRow - currentRow) <= 1 && abs(toCol - currentCol) <= 1)) {
            // Check if target square is occupied by piece of the same color
            Piece targetPiece = board.getPieceAt(toRow, toCol);
            if (targetPiece != null && targetPiece.getColor().equals(getColor())) {
                return false;
            }

            // Returns false if the move would put this player's king in check
            else if (board.doesPutKingInCheck(this, toRow, toCol)) {return false;}

            return true;
        }
        return false;
    }
}