package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

public class Rook extends Piece {

    //Constructor
    public Rook(String color, int startRow, int startCol){
        super(startRow,startCol,color);
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

        // Rooks move in straight lines
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }

        // Check if there are any pieces in the path
        if (fromRow == toRow) {
            // Moving horizontally
            int colStep = (toCol > fromCol) ? 1 : -1;
            int currentCol = fromCol + colStep;
            while (currentCol != toCol) {
                if (board.getPieceAt(fromRow, currentCol) != null) {
                    return false;
                }
                currentCol += colStep;
            }
        } else {
            // Moving vertically
            int rowStep = (toRow > fromRow) ? 1 : -1;
            int currentRow = fromRow + rowStep;
            while (currentRow != toRow) {
                if (board.getPieceAt(currentRow, fromCol) != null) {
                    return false;
                }
                currentRow += rowStep;
            }
        }

        return true;
    }
}
