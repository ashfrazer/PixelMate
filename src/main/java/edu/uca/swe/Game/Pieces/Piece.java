package edu.uca.swe.Game.Pieces;
import edu.uca.swe.Game.Board;

public abstract class Piece {
    //Fields
    int row;
    private int col;
    private String color;

    //Constructor
    public Piece(int row, int col, String color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }

    //other classes will call isValidMove() when trying to move a piece
    //isValidMove() will do nothing if move is not valid
    //if move is valid then isValidMove() will call setPosition() to change piece position
    public abstract boolean isValidMove(int fromRow, int fromCol);

    //Getters and setters
    public void setColor(String color){this.color = color;}
    public void setPosition(int row, int col){this.row = row; this.col = col;}
    public int getRow(){return row;}
    public int getCol(){return col;}
    public String getColor(){return color;}
}