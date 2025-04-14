package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * This class is a popup that appears when a pawn reaches the end of the board
 * and must be promoted. The player can choose between Queen, Rook, Bishop, or Knight
 */

public class PawnPromotionPopup extends JDialog {
    private final Color BROWN;
    private final Color DARK_GREEN;
    private final Color LIGHT_GREEN;
    private final Color CREAM;
    private final Font font;

    private String selectedPiece = null;

    public static String showPromotionDialog(String color) {
        PawnPromotionPopup popup = new PawnPromotionPopup(color);
        return popup.getSelectedPiece(); // Returns the user’s choice
    }

    public PawnPromotionPopup(String color) {
        this.setModal(true); // Prevents interaction to other windows until it closes
        this.setTitle("Promote Your Pawn");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null); // Centers to screen
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Load constants
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;
        font = Constants.FONT;

        // Sets up main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(DARK_GREEN);

        // Title label
        JLabel title = new JLabel("Choose a Piece");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(font.getName(), Font.BOLD, 32));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(title, BorderLayout.NORTH);

        // Panel that holds promotion piece buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 15, 15));
        buttonPanel.setBackground(DARK_GREEN);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        // Adds button for each piece type
        addPieceButton("Queen", color, buttonPanel);
        addPieceButton("Rook", color, buttonPanel);
        addPieceButton("Bishop", color, buttonPanel);
        addPieceButton("Horsey", color, buttonPanel);

        // Adds everything to the dialog and displays
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        this.add(mainPanel);
        this.setVisible(true);
    }

    // Creates a button with an icon
    private void addPieceButton(String type, String color, JPanel panel) {
        JButton button = new JButton();
        try {
            // Loads and scales icon image for the piece
            String path = "src/main/java/edu/uca/swe/Icons/" + type.toLowerCase() + "_" + color.toLowerCase() + ".png";
            BufferedImage image = ImageIO.read(new File(path));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
            button.setIcon(icon);
        } catch (IOException e) {
            // If icon not found, fallback to text label
            button.setText(type);
        }

        // Style the button with theme colors
        button.setBackground(CREAM);
        button.setBorder(BorderFactory.createLineBorder(BROWN, 3));
        button.setFocusPainted(false);
        button.setToolTipText("Promote to " + type);

        // Once clicked, pass selection to callback and close popup
        button.addActionListener(e -> {
            selectedPiece = type;
            dispose(); // Close the dialog
        });

        panel.add(button);
    }

    private String getSelectedPiece() {
        return selectedPiece;
    }
}
