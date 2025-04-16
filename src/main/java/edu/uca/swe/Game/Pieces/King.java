package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

import static java.lang.Math.abs;

public class King extends Piece {
    //Constructor
    public King(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }

    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
    public boolean isValidMove(int toRow, int toCol, Board board) {
        int currentRow = getRow();
        int currentCol = getCol();

        //The King can move one space in any direction
        if ((abs(toRow - currentRow) == 1 && abs(toCol - currentCol) == 1) ||
                (abs(toRow - currentRow) == 1 && abs(toCol - currentCol) == 0) ||
                (abs(toRow - currentRow) == 0 && abs(toCol - currentCol) == 1)) {

            //I think all of these todos could come from the Rules() class
            //todo: add logic to make sure move wont put our king in Check
            setPosition(toRow, toCol);
            //todo: add logic to take a piece if positions overlap
            //todo: pass the turn to next player
        }
        return false;
    }
}
