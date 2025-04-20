package edu.uca.swe.Game.PawnPromotion;

import edu.uca.swe.Game.Board;
import edu.uca.swe.GUI.Panels.PawnPromotionPanel;
import edu.uca.swe.Game.Pieces.*;

import javax.swing.*;

public class PawnPromotion {
    private Piece pawn;
    private Piece pieceChoice;
    private final Board board;

    public PawnPromotion(Piece pawn, Board board) {
        this.pawn = pawn;
        this.board = board;
    }

    //Promotion logic
    public void promotePawn(){
        System.out.println("Attempting to promote: " + pawn.getClass().getSimpleName());

        // If pawn is null then return error
        if (pawn == null) {
            System.out.println("Promotion failed: missing pawn.");
            return;
        }

        // Ensures selected piece is a pawn if not produces an error
        if (!pawn.getClass().getSimpleName().equals("Pawn")) {
            System.out.println("Cannot promote: Selected piece is not a pawn.");
            return;
        }

        int row = pawn.getRow();
        int col = pawn.getCol();
        String color = pawn.getColor();

        System.out.println("Pawn at [" + row + "," + col + "] of color " + color);

        // Checks if pawn reached the final rank
        boolean reachedEnd = (color.equalsIgnoreCase("White") && row == 0)
                || (color.equalsIgnoreCase("Black") && row == 7);

        if (reachedEnd) {
            SwingUtilities.invokeLater(() -> {
                // Shows promotion popup and gets selected piece type
                String selectedType = PawnPromotionPanel.showPromotionDialog(color);
                // Creates new piece based on selected type
                pieceChoice = createPromotedPiece(selectedType, color, row, col);
                // Replace pawn on the board with new piece
                applyPromotion();
            });
        } else {
            System.out.println("Cannot promote: Pawn is not on final rank.");
        }
    }

    // Applies selected piece to board and replaces the pawn
    private void applyPromotion() {
        if (pieceChoice != null) {
            pieceChoice.setColor(pawn.getColor()); // Sets the correct color
            board.setPieceAt(pawn.getRow(), pawn.getCol(), pieceChoice); // Updates board
            System.out.println("Pawn promoted to: " + pieceChoice.getClass().getSimpleName());
        }
    }

    // Creates new piece based on user selection
    public Piece createPromotedPiece(String type, String color, int row, int col) {
        return switch (type.toLowerCase()) {
            case "queen" -> new Queen(color, row, col);
            case "rook" -> new Rook(color, row, col);
            case "bishop" -> new Bishop(color, row, col);
            case "knight" -> new Knight(color, row, col);
            default -> new Queen(color, row, col);
        };
    }

    // Getters and setters
    public void setChoice(Piece choice) {
        this.pieceChoice = choice;
    }

    public Piece getChoice() {
        return pieceChoice;
    }

    public void setPawn(Piece currentPawn) {
        this.pawn = currentPawn;
    }

    public Piece getPawn() {
        return pawn;
    }
}