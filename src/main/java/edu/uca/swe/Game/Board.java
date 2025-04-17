package edu.uca.swe.Game;

import edu.uca.swe.Game.Pieces.*;

public class Board {
    private Piece[][] board;

    // Constructor initializes the board and pieces
    public Board() {
        board = new Piece[8][8];
        String[] InitializeColor = {"White", "Black"};
        int[] InitializeHelper = {0, 1, 2, 7, 6, 5};
        int[] SideOfBoard = {0, 7};

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                board[SideOfBoard[j]][InitializeHelper[i * 3]] = new Rook(InitializeColor[j], SideOfBoard[j], InitializeHelper[i * 3]);
                board[SideOfBoard[j]][InitializeHelper[i * 3 + 1]] = new Knight(InitializeColor[j], SideOfBoard[j], InitializeHelper[i * 3 + 1]);
                board[SideOfBoard[j]][InitializeHelper[i * 3 + 2]] = new Bishop(InitializeColor[j], SideOfBoard[j], InitializeHelper[i * 3 + 2]);
            }
            for (int i = 0; i < 8; i++) {
                board[j * 5 + 1][i] = new Pawn(InitializeColor[j], j * 5 + 1, i);
            }
            board[SideOfBoard[j]][3] = new King(InitializeColor[j], SideOfBoard[j], 3);
            board[SideOfBoard[j]][4] = new Queen(InitializeColor[j], SideOfBoard[j], 4);
        }
    }

    public Piece[][] getBoard() {
        return board;
    }
    public void setPieceAt(int row, int col, Piece piece) {
        board[row][col] = piece;
    }
    public Piece getPieceAt(int row, int col){
        if (isWithinBounds(row, col)){
            return board[row][col];
        }
        return null;
    }

    public void removePiece(int row, int col) {
        if(isWithinBounds(row, col)){
            board[row][col] = null;
        }
    }

    public void movePiece(Piece piece, int toRow, int toCol)
    {
        int fromRow = piece.getRow();
        int fromCol = piece.getCol();

        // First check if it's the correct player's turn
        if (!piece.getColor().equals(currentTurn)) {
            System.out.println("Not your turn! Current turn: " + currentTurn);
            return;
        }

        // Then check if the move is valid
        if(isWithinBounds(toRow, toCol) && piece.isValidMove(toRow, toCol, this))
        {
            // Store any piece that might be captured
            Piece capturedPiece = board[toRow][toCol];

            // Update the piece's position first
            piece.setPosition(toRow, toCol);

            // Then update the board
            board[toRow][toCol] = piece;
            board[fromRow][fromCol] = null;

            // Switch turns after a successful move
            switchTurn();
            System.out.println("Turn switched to: " + currentTurn + " after " + piece.getColor() + " moved");
        }
    }

    public boolean isWithinBounds(int row, int col)
    {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean isSquareUnderAttack(int row, int col, String color)
    {
        String opponentColor = color.equals("White") ? "Black" : "White";

        for(int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Piece piece = board[i][j];
                if (piece != null && piece.getColor().equals(opponentColor))  {
                    // Temporarily move the piece to check if it can attack the square
                    Piece originalPiece = board[row][col];
                    board[row][col] = null;
                    boolean canAttack = piece.isValidMove(row, col, this);
                    board[row][col] = originalPiece;
                    if (canAttack) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //This method will return true if the move will puts the king in check; false if it doesn't.
    //We DON'T want that to be able to happen, so this will play a big role in isValidMove()
    public boolean doesPutKingInCheck()
    {
        return true;
    }

    private String currentTurn = "White";

    public String getCurrentTurn()
    {
        return currentTurn;
    }

    public void switchTurn()
    {
        currentTurn = currentTurn.equals("White") ? "Black" : "White";
    }
}