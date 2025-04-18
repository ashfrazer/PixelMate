package edu.uca.swe.Game.Pieces;

import edu.uca.swe.Game.Board;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KnightTest {
    private Board board;
    private Knight whiteKnight;
    private Knight blackKnight;

    @Before
    public void setUp() {
        board = new Board();
        // Get knights from starting positions
        whiteKnight = (Knight) board.getPieceAt(0, 1);
        blackKnight = (Knight) board.getPieceAt(7, 1);
        // Clear pawns at (1,3) and (6,3) to allow knights to move there
        board.removePiece(1, 3);
        board.removePiece(6, 3);
    }

    @Test
    public void testValidLShapedMoves() {
        // Test valid L-shaped moves for white knight
        assertTrue("Should allow move to (2,0)", whiteKnight.isValidMove(2, 0, board));
        assertTrue("Should allow move to (2,2)", whiteKnight.isValidMove(2, 2, board));
        assertTrue("Should allow move to (1,3)", whiteKnight.isValidMove(1, 3, board));

        // Test valid L-shaped moves for black knight
        assertTrue("Should allow move to (5,0)", blackKnight.isValidMove(5, 0, board));
        assertTrue("Should allow move to (5,2)", blackKnight.isValidMove(5, 2, board));
        assertTrue("Should allow move to (6,3)", blackKnight.isValidMove(6, 3, board));
    }

    @Test
    public void testInvalidMoves() {
        // Test invalid moves that are not L-shaped
        assertFalse("Should not allow move to (1,1)", whiteKnight.isValidMove(1, 1, board));
        assertFalse("Should not allow move to (2,1)", whiteKnight.isValidMove(2, 1, board));
        assertFalse("Should not allow move to (0,2)", whiteKnight.isValidMove(0, 2, board));

        assertFalse("Should not allow move to (6,1)", blackKnight.isValidMove(6, 1, board));
        assertFalse("Should not allow move to (7,2)", blackKnight.isValidMove(7, 2, board));
        assertFalse("Should not allow move to (5,1)", blackKnight.isValidMove(5, 1, board));
    }

    @Test
    public void testCaptureOpponentPiece() {
        // Place an opponent's piece in a capture position for white knight
        Pawn blackPawn = new Pawn("Black", 2, 2);
        board.setPieceAt(2, 2, blackPawn);
        assertTrue("Should allow capture of opponent's piece", whiteKnight.isValidMove(2, 2, board));

        // Place an opponent's piece in a capture position for black knight
        Pawn whitePawn = new Pawn("White", 5, 2);
        board.setPieceAt(5, 2, whitePawn);
        assertTrue("Should allow capture of opponent's piece", blackKnight.isValidMove(5, 2, board));
    }

    @Test
    public void testCannotCaptureSameColor() {
        // Place a same-color piece in a valid capture position for white knight
        Pawn whitePawn = new Pawn("White", 2, 2);
        board.setPieceAt(2, 2, whitePawn);
        assertFalse("Should not allow capture of same color piece", whiteKnight.isValidMove(2, 2, board));

        // Place a same-color piece in a valid capture position for black knight
        Pawn blackPawn = new Pawn("Black", 5, 2);
        board.setPieceAt(5, 2, blackPawn);
        assertFalse("Should not allow capture of same color piece", blackKnight.isValidMove(5, 2, board));
    }

    @Test
    public void testJumpOverPieces() {
        // Place pieces in the path (not capture but within L-shape path)
        Pawn whitePawn = new Pawn("White", 1, 1);
        Pawn blackPawn = new Pawn("Black", 1, 2);
        board.setPieceAt(1, 1, whitePawn);
        board.setPieceAt(1, 2, blackPawn);

        // Knight should be able to move to L-shaped positions
        assertTrue("Should be able to jump over pieces to (2,0)", whiteKnight.isValidMove(2, 0, board));
        assertTrue("Should be able to jump over pieces to (2,2)", whiteKnight.isValidMove(2, 2, board));
    }
} 