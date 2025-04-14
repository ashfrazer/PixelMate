package edu.uca.swe.Game;

import edu.uca.swe.Game.Pieces.*;

public class Board {
    private Piece[][] board;

    // Constructor initializes the board and pieces
    public Board() {
        board = new Piece[8][8];
        String[] InitializeColor = {"White", "Black"};
        int[] InitalizeHelper = {0, 1, 2, 7, 6, 5};
        int[] SideofBoard = {0, 7};

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                board[SideofBoard[j]][InitalizeHelper[i * 3]] = new Rook(InitializeColor[j], SideofBoard[j], InitalizeHelper[i * 3]);
                board[SideofBoard[j]][InitalizeHelper[i * 3 + 1]] = new Knight(InitializeColor[j], SideofBoard[j], InitalizeHelper[i * 3 + 1]);
                board[SideofBoard[j]][InitalizeHelper[i * 3 + 2]] = new Bishop(InitializeColor[j], SideofBoard[j], InitalizeHelper[i * 3 + 2]);
            }
            for (int i = 0; i < 8; i++) {
                board[j * 5 + 1][i] = new Pawn(InitializeColor[j], j * 5 + 1, i);
            }
            board[SideofBoard[j]][3] = new King(InitializeColor[j], SideofBoard[j], 3);
            board[SideofBoard[j]][4] = new Queen(InitializeColor[j], SideofBoard[j], 4);
        }
    }

    public Piece[][] getBoard() {
        return board;
    }
    public void setPieceAt(int row, int col, Piece piece) {
        board[row][col] = piece;
    }

}