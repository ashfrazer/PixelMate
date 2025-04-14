package edu.uca.swe.Game.Pieces;

import static java.lang.Math.abs;

public class Queen extends Piece {
    //Constructor
    public Queen(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }

    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
    public boolean isValidMove(int toRow, int toCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        // Queens can move like Rooks or Bishops
        if (((currentRow == toRow && currentCol != toCol ) || (currentCol == toCol && currentRow != toRow)) ||
                (abs(toRow - currentRow) == abs(toCol - currentCol))){

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
