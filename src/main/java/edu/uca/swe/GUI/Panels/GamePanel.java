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
    private JPanel boardPanel;

    public GamePanel(Controller controller, String playerRole) {
        // Initialize constants and board
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;
        font = Constants.FONT;
        this.playerRole = playerRole;

        setLayout(new BorderLayout());
        board = new Board();
        tileButtons = new JButton[8][8];

        boardPanel = new JPanel(new GridLayout(8, 8));
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

                // Set piece icon
                Piece piece = pieces[row][col];
                if (piece != null) {
                    String color = piece.getColor().toLowerCase();
                    String type = piece.getClass().getSimpleName().toLowerCase();
                    String path = "src/main/java/edu/uca/swe/Icons/" + type + "_" + color + ".png";
                    File imgFile = new File(path);
                    if (imgFile.exists()) {
                        ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                        Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        tile.setIcon(new ImageIcon(scaled));
                    }
                }

                // Add button to panel
                tileButtons[row][col] = tile;
                tile.addActionListener(controller);
                boardPanel.add(tile);
            }
        }

        // Add panel to main board
        add(boardPanel, BorderLayout.CENTER);

        setBoardPanel(boardPanel);
    }

    public void updateBoard() {
        Piece[][] pieces = board.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton tile = tileButtons[row][col];
                Piece piece = pieces[row][col];

                if (piece != null) {
                    String color = piece.getColor().toLowerCase();
                    String type = piece.getClass().getSimpleName().toLowerCase();
                    String path = "src/main/java/edu/uca/swe/Icons/" + type + "_" + color + ".png";
                    File imgFile = new File(path);

                    if (imgFile.exists()) {
                        ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                        Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        tile.setIcon(new ImageIcon(scaled));
                    }
                } else {
                    tile.setIcon(null);
                }
            }
        }
        revalidate();
        repaint();
    }

    public JButton[][] getTileButtons() {
        return tileButtons;
    }

    public JButton getTileButton(int row, int col) {
        return tileButtons[row][col];
    }

    public void setBoardPanel(JPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }
}