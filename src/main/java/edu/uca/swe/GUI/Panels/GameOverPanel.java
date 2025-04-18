package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOverPanel extends JPanel {
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;
    private BufferedImage knightIcon;
    private JLabel resultLabel;

    public GameOverPanel(Controller controller, String winner) {
        // Load constants
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;
        font = Constants.FONT;

        this.setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LIGHT_GREEN);
        this.add(mainPanel, BorderLayout.CENTER);

        // NORTH PANEL
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(LIGHT_GREEN);
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(northPanel, BorderLayout.NORTH);

        // LABEL PANEL
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false);

        // Title Label
        JLabel titleLabel = createLabel("Game Over!", 80, true);

        // Result Label
        resultLabel = createLabel(winner + " wins!", 40, true);

        // Icon Label
        JLabel iconLabel = new JLabel();
        try {
            String iconColor = winner.equalsIgnoreCase("White") ? "white" : "black";
            knightIcon = ImageIO.read(new File("src/main/java/edu/uca/swe/Icons/knight_" + iconColor + ".png"));
            iconLabel.setIcon(new ImageIcon(knightIcon));
        } catch (IOException e) {
            knightIcon = null;
        }
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to label panel
        labelPanel.add(titleLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(iconLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        labelPanel.add(resultLabel);

        // Add label panel to north panel
        northPanel.add(labelPanel, BorderLayout.CENTER);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(LIGHT_GREEN);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Play Again Button
        JButton playAgainButton = createMenuButton("Play Again", controller);
        playAgainButton.setActionCommand("playagain");

        // Main Menu Button
        JButton mainMenuButton = createMenuButton("Main Menu", controller);
        mainMenuButton.setActionCommand("mainmenu");

        // Add buttons to button panel
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(playAgainButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(mainMenuButton);
        buttonPanel.add(Box.createVerticalGlue());
    }

    private JLabel createLabel(String text, int size, boolean isBold) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(font.getName(), isBold ? Font.BOLD : Font.PLAIN, size));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JButton createMenuButton(String text, Controller controller) {
        JButton button = new JButton(text);
        Dimension buttonSize = new Dimension(300, 50);
        button.setBackground(BROWN);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setFont(new Font(font.getName(), Font.PLAIN, 30));
        button.setFocusPainted(false);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(controller);
        return button;
    }
}