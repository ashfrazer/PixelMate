package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreditsPanel extends JPanel {
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;
    private BufferedImage knightIcon;
    private JLabel messageLabel;

    public CreditsPanel(Controller controller) {
        // Initialize colors
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;

        // Initialize font
        font = Constants.FONT;

        // Set layout of panel
        this.setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);

        // TITLE PANEL
        JPanel northPanel = new JPanel();
        northPanel.setBackground(LIGHT_GREEN);
        northPanel.setLayout(new BorderLayout());
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // LABEL PANEL
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false);

        // TITLE LABEL
        JLabel titleLabel = new JLabel("PixelMate");
        titleLabel.setFont(new Font(font.getName(), Font.BOLD, 80));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // KNIGHT ICON
        JLabel iconLabel = new JLabel();

        try {
            File iconFile = new File("src/main/java/edu/uca/swe/Icons/knight_black.png");
            knightIcon = ImageIO.read(iconFile);
        } catch (IOException e) {
            knightIcon = null;
        }

        ImageIcon icon = new ImageIcon(knightIcon);
        iconLabel.setIcon(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ADD LABELS TO LABEL PANEL
        labelPanel.add(titleLabel);
        labelPanel.add(iconLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ADD LABEL PANEL TO NORTH PANEL
        northPanel.add(labelPanel, BorderLayout.CENTER);

        // ADD TO MAIN PANEL
        mainPanel.add(northPanel, BorderLayout.NORTH);

        // CREDITS PANEL
        JPanel creditsPanel = new JPanel();
        creditsPanel.setLayout(new GridLayout(6, 2, 5, 5));
        creditsPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 80, 30));
        mainPanel.add(creditsPanel, BorderLayout.CENTER);

        // DEVELOPER TITLE
        JLabel[] devLabels = new JLabel[6];

        // NAME LABELS
        JLabel[] nameLabels = new JLabel[6];
        for (int i = 0; i < 5; i++) {
            // Configure and add name label
            nameLabels[i] = new JLabel();
            nameLabels[i].setFont(new Font(font.getFontName(), Font.PLAIN, 30));
            nameLabels[i].setForeground(Color.BLACK);
            nameLabels[i].setHorizontalAlignment(JLabel.CENTER);
            creditsPanel.add(nameLabels[i]);

            // Configure and add dev label
            devLabels[i] = new JLabel("Developer");
            devLabels[i].setFont(new Font(font.getFontName(), Font.ITALIC, 30));
            devLabels[i].setForeground(Color.BLACK);
            devLabels[i].setHorizontalAlignment(JLabel.CENTER);
            creditsPanel.add(devLabels[i]);
            switch (i) {
                case 0:
                    nameLabels[i].setText("Ash Frazer");
                    break;
                case 1:
                    nameLabels[i].setText("Camryn Joyner");
                    break;
                case 2:
                    nameLabels[i].setText("Miranda Nichols");
                    break;
                case 3:
                    nameLabels[i].setText("Axel Pinard");
                    break;
                case 4:
                    nameLabels[i].setText("Carson Stell");
                    break;
            }
        }

        // BACK BUTTON
        Dimension buttonSize = new Dimension(200, 50);

        JButton backButton = new JButton("Back");
        backButton.setBackground(BROWN);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        backButton.setFont(new Font(font.getName(), Font.PLAIN, 30));
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(buttonSize);
        backButton.setMaximumSize(buttonSize);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(controller);
        creditsPanel.add(Box.createRigidArea(new Dimension(100, 30)));
        creditsPanel.add(backButton);
    }
}
