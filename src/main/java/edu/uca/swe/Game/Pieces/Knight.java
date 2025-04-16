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
        Piece targetPiece = board.getPieceAt(toRow, toCol);

        //Horseys can move in one direction 1 space and in a perpendicular direction 2 spaces
        //Horseys can also jump over pieces so no checks for collisions needed.
        if (abs(fromCol - toCol) == 2 && abs(fromRow - toRow) == 1) {
            // Check to see if desired position is occupied by a piece of same color as one being moved
            // or if the desired position is out of bounds
            if(targetPiece != null){
                if (targetPiece.getColor().equals(this.getColor()) || !board.isWithinBounds(toRow, toCol))
                    return false;
            }

            else return true;
        }

        if (abs(fromCol - toCol) == 1 && abs(fromRow - toRow) == 2) {
            // Check to see if desired position is occupied by a piece of same color as one being moved
            // or if the desired position is out of bounds
            if(targetPiece != null){
                if (targetPiece.getColor().equals(this.getColor()) || !board.isWithinBounds(toRow, toCol))
                    return false;
            }

            else return true;
        }

        return false;
    }
}
