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
        }
    }

    public boolean isWithinBounds(int row, int col)
    {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public boolean isSquareUnderAttack(int row, int col, String color)
    {
        // First check if the square is within bounds
        if (!isWithinBounds(row, col)) {
            return false;
        }

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

    //This method will return true if the move will put the king in check; false if it doesn't.
    //We DON'T want that to be able to happen, so this will play a big role in isValidMove()
    public boolean doesPutKingInCheck(Piece piece, int toRow, int toCol) {
        int fromRow = piece.getRow();
        int fromCol = piece.getCol();

        // Create a copy of the board array
        Piece[][] boardCopy = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }

        Piece captured = boardCopy[toRow][toCol];

        // Simulates move on the copy
        boardCopy[fromRow][fromCol] = null;
        boardCopy[toRow][toCol] = piece;

        // Temporarily update piece position
        int originalRow = piece.getRow();
        int originalCol = piece.getCol();
        piece.setPosition(toRow, toCol);

        // Finds the King of respective color
        String myColor = piece.getColor();
        int kingRow = -1, kingCol = -1;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = boardCopy[r][c];
                if (p instanceof King && p.getColor().equals(myColor)) {
                    kingRow = r;
                    kingCol = c;
                    break;
                }
            }
            if (kingRow != -1) break;
        }

        // Store the original board reference
        Piece[][] originalBoard = board;
        // Set the board to our copy temporarily
        board = boardCopy;

        // Checks to see if king is under attack
        boolean inCheck = isSquareUnderAttack(kingRow, kingCol, myColor);

        // Restore the original board
        board = originalBoard;

        // Restore piece's original position
        piece.setPosition(originalRow, originalCol);

        return inCheck;
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