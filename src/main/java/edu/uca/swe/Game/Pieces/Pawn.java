package edu.uca.swe.Game.Pieces;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    //Constructor
    public Pawn(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }


    @Override
    public boolean isValidMove(int toRow, int toCol) {
        int fromRow = getRow();
        int fromCol = getCol();

        //todo: add first move (up 2 squares) logic and capture logic
        if (getColor().equalsIgnoreCase("White")) {
            // white starts at row 1, moves down (+1)
            if (fromCol == toCol && fromRow + 1 == toRow) return true;
            if (fromCol == toCol && fromRow == 1 && toRow == 3) return true;
        }

        if (getColor().equalsIgnoreCase("Black")) {
            // black starts at row 6, moves up (-1)
            if (fromCol == toCol && fromRow - 1 == toRow) return true;
            if (fromCol == toCol && fromRow == 6 && toRow == 4) return true;
        }

        return false;
    }

//    //Checks to make sure move is valid and if so sets the pieces' new position and passes turn
//    public void isValidMove(int toRow, int toCol) {
//        int currentRow = getRow();
//        int currentCol = getCol();
//
//        // Pawns can move forward 1 spce normally, White moves up the rows
//        if (currentCol == toCol && getColor().equals("white") && currentRow == toRow + 1) {
//
//            //I think all of these todos could come from the Rules() class
//            //todo: add check to make sure King is not in Check or if in check move will block check
//            //todo: add logic to make sure move wont put our king in Check
//            setPosition(toRow, toCol);
//            //todo: add logic to take a piece if positions overlap
//            //todo: pass the turn to next player
//        }
//
//        // Pawns can move forward 1 spce normally, Black moves down the rows
//        if (currentCol == toCol && getColor().equals("Black") && currentRow == toRow - 1) {
//
//            //I think all of these todos could come from the Rules() class
//            //todo: add check to make sure King is not in Check or if in check move will block check
//            //todo: add logic to make sure move wont put our king in Check
//            setPosition(toRow, toCol);
//            //todo: add logic to take a piece if positions overlap
//            //todo: pass the turn to next player
//        }
//
//        //pawns move forward 2 spaces if on starting position
//        if (currentCol == toCol && getColor().equals("White") && currentRow == toRow + 2 && currentRow == 1) {
//
//            //I think all of these todos could come from the Rules() class
//            //todo: add check to make sure King is not in Check or if in check move will block check
//            //todo: add logic to make sure move wont put our king in Check
//            setPosition(toRow, toCol);
//            //todo: add logic to take a piece if positions overlap
//            //todo: pass the turn to next player
//        }
//
//        //pawns move forward 2 spaces if on starting position
//        if (currentCol == toCol && getColor().equals("Black") && currentRow == toRow - 2 && currentRow == 6) {
//
//            //I think all of these todos could come from the Rules() class
//            //todo: add check to make sure King is not in Check or if in check move will block check
//            //todo: add logic to make sure move wont put our king in Check
//            setPosition(toRow, toCol);
//            //todo: add logic to take a piece if positions overlap
//            //todo: pass the turn to next player
//        }
//
//        //todo: add logic to attack diagonal pieces (need some kind of checkPiece() thing in board probably
//        //todo: add en pesant logic, need to check game history
//        //todo: add check for promotion
//    }
}
