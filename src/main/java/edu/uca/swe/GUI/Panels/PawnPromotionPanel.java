package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.Game.Pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is a popup that appears when a pawn reaches the end of the board
 * and must be promoted. The player can choose between Queen, Rook, Bishop, or Knight
 */

public class PawnPromotionPanel extends JDialog {
    private final Color BROWN;
    private final Color DARK_GREEN;
    private final Color LIGHT_GREEN;
    private final Color CREAM;
    private final Font font;

    private String selectedPiece = null;

    public PawnPromotionPanel(String color) {
        System.out.println("DEBUG: Creating PawnPromotionPanel for color: " + color);

        // Load constants
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;
        font = Constants.FONT;

        // Configure dialog
        this.setModal(true);
        this.setTitle("Pawn Promotion");
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DARK_GREEN);
        this.add(mainPanel);

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(LIGHT_GREEN);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Which piece would you like to promote to?");
        titleLabel.setFont(new Font(font.getName(), Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Pieces panel
        JPanel piecesPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        piecesPanel.setBackground(DARK_GREEN);
        piecesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add piece buttons
        addPieceButton("Queen", color, piecesPanel);
        addPieceButton("Rook", color, piecesPanel);
        addPieceButton("Bishop", color, piecesPanel);
        addPieceButton("Knight", color, piecesPanel);

        mainPanel.add(piecesPanel, BorderLayout.CENTER);
        System.out.println("DEBUG: PawnPromotionPanel UI setup complete");
    }

    private void addPieceButton(String pieceType, String color, JPanel panel) {
        System.out.println("DEBUG: Adding button for " + pieceType);
        JButton button = new JButton();
        button.setBackground(BROWN);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setFocusPainted(false);

        try {
            String path = "src/main/java/edu/uca/swe/Icons/" + pieceType.toLowerCase() + "_" + color.toLowerCase() + ".png";
            System.out.println("DEBUG: Loading icon from: " + path);
            BufferedImage image = ImageIO.read(new File(path));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(80, 80, Image.SCALE_SMOOTH));
            button.setIcon(icon);
        } catch (IOException e) {
            System.out.println("DEBUG: Failed to load icon, using text instead");
            button.setText(pieceType);
        }

        button.addActionListener(e -> {
            System.out.println("DEBUG: " + pieceType + " button clicked");
            selectedPiece = pieceType;
            dispose();
        });

        panel.add(button);
    }

    public String getSelectedPiece() {
        System.out.println("DEBUG: Getting selected piece: " + selectedPiece);
        return selectedPiece;
    }

    public static String showPromotionDialog(String color) {
        System.out.println("DEBUG: Showing promotion dialog for color: " + color);
        PawnPromotionPanel dialog = new PawnPromotionPanel(color);
        dialog.setVisible(true);
        String result = dialog.getSelectedPiece();
        System.out.println("DEBUG: Dialog closed, selected piece: " + result);
        return result;
    }
}