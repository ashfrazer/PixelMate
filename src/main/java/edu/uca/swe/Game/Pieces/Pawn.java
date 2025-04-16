package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;

import static java.lang.Math.abs;

public class Pawn extends Piece {
    //Constructor
    public Pawn(String color, int startRow, int startCol){
        super(startRow,startCol,color);
    }


    @Override
    public boolean isValidMove(int toRow, int toCol, Board board) {
        int fromRow = getRow();
        int fromCol = getCol();
        Piece targetPiece = board.getPieceAt(toRow, toCol);

        //todo: add capture logic
        if (getColor().equalsIgnoreCase("White")) {
            // White pawn always starts at row 2
            // Moving one forward
            if (toCol == fromCol && toRow == fromRow + 1) {
                // Check to see if there is a piece obstructing, otherwise return true
                if (targetPiece == null) return true;
                else return false;
            }

            // Moving two forward (only allowed when starting from row 2)
            else if (toCol == fromCol && toRow == 4 && fromRow == 2) {
                // Check to see if there is a piece obstructing, otherwise return true
                if (targetPiece == null) return true;
                else return false;
            }

            // If wanting to move diagonal
            else if (abs(toCol - fromCol) == 1 && toRow == fromRow + 1) {
                // Check to see if the target piece is an enemy piece,
                // Also ensure that the piece would not be moving out of bounds
                if (targetPiece.getColor().equalsIgnoreCase("Black") && board.isWithinBounds(toRow, toCol))
                    return true;
                else return false;
            }

            // Any other desired spot will return false.
            else {return false;}
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
