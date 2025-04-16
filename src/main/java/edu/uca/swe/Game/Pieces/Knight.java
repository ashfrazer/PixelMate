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
            if (targetPiece.getColor().equals(this.getColor()) || !board.isWithinBounds(toRow, toCol))
                return false;

            else return true;

            //I think all of these todos could come from the Rules() class
            //todo: add check to make sure King is not in Check or if in check move will block check
            //todo: add logic to make sure move wont put our king in Check
            //setPosition(toRow, toCol);

            //todo: add logic to take a piece if positions overlap
            //todo: pass the turn to next player (shouldn't this go in Move class in/at end of the
            //      makeMove() method? -carson
        }

        else {return false;}
    }
}
