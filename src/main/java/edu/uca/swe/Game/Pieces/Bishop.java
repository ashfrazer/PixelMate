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

        // Bishops move diagonally
        if (Math.abs(fromRow - toRow) != Math.abs(fromCol - toCol)) {
            return false;
        }

        // Check if there are any pieces in the path
        int rowStep = (toRow > fromRow) ? 1 : -1;
        int colStep = (toCol > fromCol) ? 1 : -1;
        int currentRow = fromRow + rowStep;
        int currentCol = fromCol + colStep;

        while (currentRow != toRow && currentCol != toCol) {
            if (board.getPieceAt(currentRow, currentCol) != null) {
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        return true;
    }
}
