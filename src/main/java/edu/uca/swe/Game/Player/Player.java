package edu.uca.swe.Game.Player;

import edu.uca.swe.Game.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private boolean isHost;
    private boolean inCheck;
    private boolean inCheckMate;
    private final List<Piece> pieces;
    private String pieceColor;

    public Player(String username, boolean isHost, String pieceColor) {
        this.username = username;
        this.isHost = isHost;
        this.inCheck = false;
        this.inCheckMate = false;
        this.pieces = new ArrayList<>();
        this.pieceColor = pieceColor;
    }

    //public void makeMove(Move move) {
        //Implement move logic
    //    System.out.println(username + " made a move: " + move);}

    public void addPiece(Piece p) {
         pieces.add(p);
     }
    public void removePiece(Piece p) {
        pieces.remove(p);
    }
    public List<Piece> getPieces() {
        return pieces;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setPieceColor(String color) {
        this.pieceColor = color;
    }
    public String getPieceColor() {
        return pieceColor;
    }
    public void setIsHost(boolean isHost) {
        this.isHost = isHost;
    }
    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }
    public void setInCheckMate(boolean inCheckMate) {
        this.inCheckMate = inCheckMate;
    }
    public boolean isInCheck() {
        return inCheck;
    }
    public boolean isInCheckMate() {
        return inCheckMate;
    }
    public boolean isHost() {
        return isHost;
    }
}
