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
        if (abs(toRow - currentRow) == abs(toCol - currentCol)) {

            //I think all of these todos could come from the Rules() class
            //todo: add check to make sure King is not in Check or if in check move will block check
            //todo: add logic to make sure move wont put our king in Check
            //todo: add logic to make sure not moving through another Piece
            setPosition(toRow, toCol);
            //todo: add logic to take a piece if positions overlap
            //todo: pass the turn to next player
        }
        return false;
    }
}
