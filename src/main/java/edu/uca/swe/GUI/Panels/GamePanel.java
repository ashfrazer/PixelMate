package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;
import edu.uca.swe.GUI.Controllers.GameController;
import edu.uca.swe.Game.Board;
import edu.uca.swe.Game.Connection.Client;
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
    private GameController gameController;
    private Client client;

    public GamePanel(Board board, String playerRole, Client client) {
        // Initialize constants and board
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;
        font = Constants.FONT;

        // Set board and player role (client/host)
        this.board = board;
        this.playerRole = playerRole;
        this.client = client;
        gameController = new GameController(this, board);

        // Set layout and arrange board
        setLayout(new BorderLayout());
        tileButtons = new JButton[8][8];

        boardPanel = new JPanel(new GridLayout(8, 8, 0, 0));
        boardPanel.setPreferredSize(new Dimension(600, 660));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize tiles
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton tile = new JButton();
                tile.setBackground((row + col) % 2 == 0 ? LIGHT_GREEN : DARK_GREEN);
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                tile.addActionListener(gameController);
                tile.setMargin(new Insets(0, 0, 0, 0));

                // Store tiles in normal positions
                tileButtons[row][col] = tile;
                boardPanel.add(tile);
            }
        }

        // Initialize pieces
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
                        Image scaled = icon.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
                        tile.setIcon(new ImageIcon(scaled));
                    }
                } else {
                    tile.setIcon(null);
                }
            }
        }

        // Add board to panel
        add(boardPanel, BorderLayout.CENTER);

        setBoardPanel(boardPanel);

        // Update the board to ensure correct initial display
        updateBoard();
    }

    public void updateBoard() {
        // Update board
        Piece[][] pieces = board.getBoard();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // If player is host (white), flip the display coordinates
                int displayRow = playerRole.equals("host") ? 7 - row : row;
                int displayCol = playerRole.equals("host") ? 7 - col : col;

                JButton tile = tileButtons[displayRow][displayCol];
                Piece piece = pieces[row][col];

                if (piece != null) {
                    String color = piece.getColor().toLowerCase();
                    String type = piece.getClass().getSimpleName().toLowerCase();
                    String path = "src/main/java/edu/uca/swe/Icons/" + type + "_" + color + ".png";
                    File imgFile = new File(path);

                    if (imgFile.exists()) {
                        ImageIcon icon = new ImageIcon(imgFile.getAbsolutePath());
                        Image scaled = icon.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
                        tile.setIcon(new ImageIcon(scaled));
                    }
                } else {
                    tile.setIcon(null);
                }
            }
        }
        this.revalidate();
        this.repaint();
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

    public String getPlayerRole(){return playerRole;}

    public Client getClient(){return client;}

    public GameController getGameController(){return gameController;}
}