package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;
import edu.uca.swe.Game.Database.Database;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private Controller controller;
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;
    private BufferedImage knightIcon;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton button1, button2;
    private JLabel messageLabel;
    private JPanel credentialsPanel;
    private Database database;

    public LoginPanel(Controller controller) {
        // Initialize controller
        this.controller = controller;

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
        JPanel northPanel = createTitlePanel();

        // ADD TITLE PANEL TO MAIN PANEL
        mainPanel.add(northPanel, BorderLayout.NORTH);

        // CREDENTIALS PANEL
        credentialsPanel = createCredentialsPanel();
        mainPanel.add(credentialsPanel, BorderLayout.CENTER);

        // Initialize the database object
        database = new Database();
    }

    private JPanel createTitlePanel() {
        // Create Title Panel
        JPanel northPanel = new JPanel();
        northPanel.setBackground(LIGHT_GREEN);
        northPanel.setLayout(new BorderLayout());
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create Label Panel
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.setOpaque(false);

        // Create Title Label
        JLabel titleLabel = createTitleLabel();

        // Create Message Label
        messageLabel = createMessageLabel();

        // Create Icon Label
        JLabel iconLabel = createIconLabel();

        // Add components to Label Panel
        labelPanel.add(titleLabel);
        labelPanel.add(iconLabel);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(messageLabel);

        // Add Label Panel to North Panel
        northPanel.add(labelPanel, BorderLayout.CENTER);

        return northPanel;
    }

    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("PixelMate");
        titleLabel.setFont(new Font(font.getName(), Font.BOLD, 80));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }

    private JLabel createMessageLabel() {
        JLabel messageLabel = new JLabel("Please log into your account.");
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return messageLabel;
    }

    private JLabel createIconLabel() {
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
        return iconLabel;
    }

    private JPanel createCredentialsPanel() {
        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new GridLayout(5, 2, 5, 10));
        credentialsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 80, 20));

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        usernameLabel.setForeground(Color.BLACK);
        usernameField = new JTextField();
        usernameField.setFont(new Font(font.getName(), Font.PLAIN, 30));
        usernameField.setForeground(Color.BLACK);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        passwordLabel.setForeground(Color.BLACK);
        passwordField = new JPasswordField();
        passwordField.setFont(new Font(font.getName(), Font.PLAIN, 30));
        passwordField.setForeground(Color.BLACK);

        // Buttons
        button1 = new JButton("Back");
        button2 = new JButton("Enter");

        for (JButton button : new JButton[]{button1, button2}) {
            button.setBackground(BROWN);
            button.setForeground(Color.BLACK);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
            button.setFont(new Font(font.getName(), Font.PLAIN, 30));
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(200, 50));
            button.addActionListener(controller);
        }

        // Add components to panel
        credentialsPanel.add(usernameLabel);
        credentialsPanel.add(usernameField);
        credentialsPanel.add(passwordLabel);
        credentialsPanel.add(passwordField);
        credentialsPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        credentialsPanel.add(Box.createRigidArea(new Dimension(0, 0)));
        credentialsPanel.add(button1);
        credentialsPanel.add(button2);

        return credentialsPanel;
    }

    private void addFieldToPanel(JPanel panel, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        label.setFont(new Font(font.getName(), Font.PLAIN, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        field.setForeground(Color.BLACK);
        field.setFont(new Font(font.getName(), Font.PLAIN, 30));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(field);
    }

    private void addButtonToPanel(JPanel panel, String buttonText, JButton button) {
        button.setBackground(BROWN);
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setFont(new Font(font.getName(), Font.PLAIN, 30));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        button.setMaximumSize(new Dimension(200, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(controller);
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    public void setError(String errorMessage) {
        messageLabel.setText(errorMessage);
        messageLabel.setForeground(Color.RED);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}