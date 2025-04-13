package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;
import edu.uca.swe.Game.Board;
import edu.uca.swe.Game.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GamePanel extends JPanel {

    private JButton[][] tileButtons;
    private Board board;
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;
    private String playerRole;

    public GamePanel(Controller controller, String playerRole) {
        // Load constants
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;
        font = Constants.FONT;
        this.playerRole = playerRole;

        setLayout(new BorderLayout());
        board = new Board();
        tileButtons = new JButton[8][8];

        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setPreferredSize(new Dimension(600, 610));

        Piece[][] pieces = board.getBoard();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton tile = new JButton();
                tile.setOpaque(true);
                tile.setBorderPainted(false);

                // Set colors of tiles
                if ((row + col) % 2 == 0) {
                    tile.setBackground(CREAM);
                } else {
                    tile.setBackground(BROWN);
                }

                // Get piece at current tile position
                Piece piece = pieces[row][col];

                if (piece != null) {
                    String color = piece.getColor().toLowerCase();

                    // CLIENT: flip color
                    if (playerRole.equals("host")) {
                        color = color.equals("white") ? "black" : "white";
                    }

                    // Get type of piece
                    String type = piece.getClass().getSimpleName().toLowerCase();

                    // Build image
                    String path = "src/main/java/edu/uca/swe/Icons/" + type + "_" + color + ".png";
                    File imgFile = new File(path);

                    // Set icon for piece
                    if (imgFile.exists()) {
                        ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                        Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        tile.setIcon(new ImageIcon(scaled));
                    } else {
                        System.err.println("Could not find image at: " + path);
                    }
                }

                // Add to boardPanel
                tileButtons[row][col] = tile;
                boardPanel.add(tile);
            }
        }

        // Flip pieces and rows for client
        if (playerRole.equals("client")) {
            flipBoard(boardPanel);
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void flipBoard(JPanel boardPanel) {
        Piece[][] flippedPieces = new Piece[8][8];
        Piece[][] originalPieces = board.getBoard();

        // Flip pieces
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // CLIENT: flip rows and colors
                if (playerRole.equals("client")) {
                    flippedPieces[7 - row][col] = originalPieces[row][col];

                    // Swap color of piece
                    if (flippedPieces[7 - row][col] != null) {
                        Piece piece = flippedPieces[7 - row][col];
                        String currentColor = piece.getColor().toLowerCase();

                        // Flip color
                        piece.setColor(currentColor.equals("white") ? "black" : "white");
                    }
                } else {
                    // Host
                    flippedPieces[row][col] = originalPieces[row][col];
                }
            }
        }

        // Update board with flipped pieces
        boardPanel.removeAll();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton tile = new JButton();
                tile.setOpaque(true);
                tile.setBorderPainted(false);

                // Set colors of tiles
                if ((row + col) % 2 == 0) {
                    tile.setBackground(CREAM);
                } else {
                    tile.setBackground(BROWN);
                }

                // Get piece at current tile position
                Piece piece = flippedPieces[row][col];
                if (piece != null) {
                    String color = piece.getColor().toLowerCase();

                    // Get type
                    String type = piece.getClass().getSimpleName().toLowerCase();

                    // Build image
                    String path = "src/main/java/edu/uca/swe/Icons/" + type + "_" + color + ".png";
                    File imgFile = new File(path);

                    // Set icon for piece
                    if (imgFile.exists()) {
                        ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                        Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        tile.setIcon(new ImageIcon(scaled));
                    } else {
                        System.err.println("Could not find image at: " + path);
                    }
                }

                tileButtons[row][col] = tile;
                boardPanel.add(tile);
            }
        }

        // Update display
        boardPanel.revalidate();
        boardPanel.repaint();
    }



}