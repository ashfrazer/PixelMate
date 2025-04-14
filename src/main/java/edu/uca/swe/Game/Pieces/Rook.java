package edu.uca.swe.Game.Pieces;

public class Rook extends Piece {

    //Constructor
    public Rook(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }

    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
    public boolean isValidMove(int toRow, int toCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        //Rooks can move on the same row or the same col but not both
        if ((currentRow == toRow && currentCol != toCol ) || (currentCol == toCol && currentRow != toRow)){

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
