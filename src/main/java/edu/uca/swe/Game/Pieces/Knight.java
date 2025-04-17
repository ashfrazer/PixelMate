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
        return (abs(fromCol - toCol) == 2 && abs(fromRow - toRow) == 1) ||
                (abs(fromCol - toCol) == 1 && abs(fromRow - toRow) == 2);
    }
}