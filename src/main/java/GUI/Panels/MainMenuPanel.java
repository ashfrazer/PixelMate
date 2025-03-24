package GUI.Panels;

import GUI.Colors.Constants;
import GUI.Controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenuPanel extends JPanel {
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;
    private BufferedImage knightIcon;

    public MainMenuPanel(Controller controller) {
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

        // WELCOME LABEL
        JLabel welcomeLabel = new JLabel("Welcome back! What would you like to do next?");
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // PAWN ICON
        JLabel iconLabel = new JLabel();

        try {
            File iconFile = new File("src/main/java/Icons/knight_white.png");
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
        labelPanel.add(welcomeLabel);

        // ADD LABEL PANEL TO NORTH PANEL
        northPanel.add(labelPanel, BorderLayout.CENTER);

        // ADD TO MAIN PANEL
        mainPanel.add(northPanel, BorderLayout.NORTH);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        Dimension buttonSize = new Dimension(300, 50);

        // LOGIN BUTTON
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(BROWN);
        loginButton.setForeground(Color.BLACK);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        loginButton.setFont(new Font(font.getName(), Font.PLAIN, 30));
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(buttonSize);
        loginButton.setMaximumSize(buttonSize);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(controller);
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // CREATE ACCOUNT BUTTON
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBackground(BROWN);
        createAccountButton.setForeground(Color.BLACK);
        createAccountButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        createAccountButton.setFont(new Font(font.getName(), Font.PLAIN, 30));
        createAccountButton.setFocusPainted(false);
        createAccountButton.setPreferredSize(buttonSize);
        createAccountButton.setMaximumSize(buttonSize);
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.addActionListener(controller);
        buttonPanel.add(createAccountButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // CREATE ACCOUNT BUTTON
        JButton creditsButton = new JButton("Credits");
        creditsButton.setBackground(BROWN);
        creditsButton.setForeground(Color.BLACK);
        creditsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        creditsButton.setFont(new Font(font.getName(), Font.PLAIN, 30));
        creditsButton.setFocusPainted(false);
        creditsButton.setPreferredSize(buttonSize);
        creditsButton.setMaximumSize(buttonSize);
        creditsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsButton.addActionListener(controller);
        buttonPanel.add(creditsButton);

    }

}
