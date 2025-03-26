package Game.Pieces;

import static java.lang.Math.abs;

public class Horsey  extends Piece{

    //Constructor
    public Horsey(String color, int startRow, int startCol) {
        super(startRow, startCol, color);
    }

    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
    public void isValidMove(int toRow, int toCol) {
        int currentRow = getRow();
        int currentCol = getCol();

        //Horseys can move in one direction 1 space and in a perpendicular direction 2 spaces
        //Horseys can also jump over pieces so no checks for collisions needed.
        if ((abs(currentRow - toRow) == 1 && abs(currentCol - toCol) == 2) ||
                (abs(currentRow - toRow) == 2 && abs(currentCol - toCol) == 1)) {

            //I think all of these todos could come from the Rules() class
            //todo: add check to make sure King is not in Check or if in check move will block check
            //todo: add logic to make sure move wont put our king in Check
            setPosition(toRow, toCol);
            //todo: add logic to take a piece if positions overlap
            //todo: pass the turn to next player
        }
    }
}
