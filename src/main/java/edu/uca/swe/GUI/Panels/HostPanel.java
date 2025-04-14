package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HostPanel extends JPanel {
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;
    private BufferedImage knightIcon;
    public JLabel waitingLabel;

    public HostPanel(Controller controller) {
        // Load constants
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;
        font = Constants.FONT;

        this.setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());
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

        JLabel titleLabel = createLabel("PixelMate", 80, true);
        JLabel subLabel = createLabel("Hosting a game.", 30, false);

        JLabel iconLabel = new JLabel();
        try {
            knightIcon = ImageIO.read(new File("src/main/java/edu/uca/swe/Icons/knight_black.png"));
            iconLabel.setIcon(new ImageIcon(knightIcon));
        } catch (IOException e) {
            knightIcon = null;
        }
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelPanel.add(titleLabel);
        labelPanel.add(iconLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(subLabel);
        northPanel.add(labelPanel, BorderLayout.CENTER);

        // CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        centerPanel.setOpaque(false);

        JLabel playersLabel = createLabel("Players:", 26, true);
        JLabel hostLabel = createLabel("You (host)", 24, false);
        waitingLabel = createLabel("waiting for player...", 24, false);

        centerPanel.add(playersLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(hostLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(waitingLabel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        buttonPanel.setOpaque(false);

        JButton returnButton = createMenuButton("Return", controller);
        JButton startButton = createMenuButton("Start", controller);

        buttonPanel.add(returnButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(startButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createLabel(String text, int size, boolean bold) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(font.getName(), bold ? Font.BOLD : Font.PLAIN, size));
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

    public void updateWaitingLabel(String msg) {
        waitingLabel.setText(msg);
        this.revalidate();
        this.repaint();
    }
}