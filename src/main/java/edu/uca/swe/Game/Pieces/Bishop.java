package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

import static java.lang.Math.abs;

public class Bishop extends Piece {

    //Constructor
    public Bishop(String color, int startRow, int startCol) {
        super(startRow, startCol, color);
    }

    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
    public boolean isValidMove(int toRow, int toCol, Board board) {
        int currentRow = getRow();
        int currentCol = getCol();

        //Bishops can move horizontally so the absolute difference of
        //their next position minus their current position should be the
        //same for both Row and Col.
        if (Math.abs(toRow - currentRow) == Math.abs(toCol - currentCol)) {
            // Determine the direction of movement
            int rowStep = (toRow > currentRow) ? 1 : -1;
            int colStep = (toCol > currentCol) ? 1 : -1;

            // Check each square along the path
            int checkRow = currentRow + rowStep;
            int checkCol = currentCol + colStep;

            while (checkRow != toRow && checkCol != toCol) {
                if (board.getPieceAt(checkRow, checkCol) != null) {
                    return false; // Path is blocked
                }
                checkRow += rowStep;
                checkCol += colStep;
            }

            // Check the destination square
            Piece destinationPiece = board.getPieceAt(toRow, toCol);
            if (destinationPiece == null || !destinationPiece.getColor().equals(this.getColor())) {
                return true; // Destination is empty or has opponent's piece
            }

            // Returns false if the move would put this player's king in check
            if (board.doesPutKingInCheck(this, toRow, toCol)) {return false;}
        }
        return false;
    }
}
