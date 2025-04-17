package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

import static java.lang.Math.abs;

public class Queen extends Piece {
    //Constructor
    public Queen(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }

    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
    public boolean isValidMove(int toRow, int toCol, Board board) {
        int currentRow = getRow();
        int currentCol = getCol();

        // Queens can move like Rooks or Bishops
        if (((currentRow == toRow && currentCol != toCol) || (currentCol == toCol && currentRow != toRow)) ||
                (abs(toRow - currentRow) == abs(toCol - currentCol))) {

            // Determine movement direction
            int rowStep = (toRow > currentRow) ? 1 : (toRow < currentRow) ? -1 : 0;
            int colStep = (toCol > currentCol) ? 1 : (toCol < currentCol) ? -1 : 0;
            int checkRow = currentRow + rowStep;
            int checkCol = currentCol + colStep;

            // Check if path is clear
            while (checkRow != toRow || checkCol != toCol) {
                if (board.getPieceAt(checkRow, checkCol) != null) {
                    return false;
                }
                checkRow += rowStep;
                checkCol += colStep;
            }

            // Check if destination is empty or has opponent's piece
            Piece destinationPiece = board.getPieceAt(toRow, toCol);
            if (destinationPiece == null || !destinationPiece.getColor().equals(this.getColor())) {
                return true;
            }

            // Returns false if the move would put this player's king in check
            if (board.doesPutKingInCheck(this, toRow, toCol)) {return false;}
        }
        return false;
    }

}
