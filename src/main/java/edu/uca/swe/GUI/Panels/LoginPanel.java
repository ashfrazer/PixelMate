package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;

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
    private JButton button1, button2, button3;
    private JLabel messageLabel;
    private JPanel credentialsPanel;

    public LoginPanel(Controller controller) {
        //TODO
        // CLEAN UP AND MAKE CODE DRY. POSSIBLY DIVIDE HOST/JOIN/LOGOUT
        // INTO NEW PANEL FOR CONSISTENCY.

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

        // MESSAGE LABEL
        messageLabel = new JLabel("Please log into your account.");
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        labelPanel.add(messageLabel);

        // ADD LABEL PANEL TO NORTH PANEL
        northPanel.add(labelPanel, BorderLayout.CENTER);

        // ADD TO MAIN PANEL
        mainPanel.add(northPanel, BorderLayout.NORTH);

        // CREDENTIALS PANEL
        credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new GridLayout(5, 2, 5, 10));
        credentialsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 80, 20));
        mainPanel.add(credentialsPanel, BorderLayout.CENTER);

        // USERNAME FIELD
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usernameField = new JTextField();
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font(font.getName(), Font.PLAIN, 30));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // PASSWORD FIELD
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font(font.getName(), Font.PLAIN, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // BACK BUTTON
        Dimension buttonSize = new Dimension(200, 50);

        button1 = new JButton("Back");
        button1.setBackground(BROWN);
        button1.setForeground(Color.BLACK);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button1.setFont(new Font(font.getName(), Font.PLAIN, 30));
        button1.setFocusPainted(false);
        button1.setPreferredSize(buttonSize);
        button1.setMaximumSize(buttonSize);
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button1.addActionListener(controller);

        // ENTER BUTTON
        button2 = new JButton("Enter");
        button2.setBackground(BROWN);
        button2.setForeground(Color.BLACK);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button2.setFont(new Font(font.getName(), Font.PLAIN, 30));
        button2.setFocusPainted(false);
        button2.setPreferredSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.addActionListener(controller);

        // BUTTON 3
        button3 = new JButton();
        button3.setBackground(BROWN);
        button3.setForeground(Color.BLACK);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button3.setFont(new Font(font.getName(), Font.PLAIN, 30));
        button3.setFocusPainted(false);
        button3.setPreferredSize(buttonSize);
        button3.setMaximumSize(buttonSize);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.addActionListener(controller);

        // ADD FIELDS TO CREDENTIALS PANEL
        Dimension padding = new Dimension(100, 30);
        credentialsPanel.add(usernameLabel);
        credentialsPanel.add(usernameField);
        credentialsPanel.add(passwordLabel);
        credentialsPanel.add(passwordField);
        credentialsPanel.add(Box.createRigidArea(padding));
        credentialsPanel.add(Box.createRigidArea(padding));
        credentialsPanel.add(Box.createRigidArea(padding));
        credentialsPanel.add(Box.createRigidArea(padding));
        credentialsPanel.add(button1);
        credentialsPanel.add(button2);
    }

    public void setLoginSuccessful() {
        // Update message
        messageLabel.setText("Welcome back, " + usernameField.getText() + "!");
        messageLabel.setForeground(Color.BLACK);

        // Update button texts
        button1.setText("Host");
        button2.setText("Join");
        button3.setText("Logout");

        // Clear credentialsPanel
        credentialsPanel.removeAll();

        // Refresh display
        revalidate();
        repaint();

        // Reset credential panel
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // Configure buttons
        Dimension buttonSize = new Dimension(300, 50);

        button1.setPreferredSize(buttonSize);
        button1.setMaximumSize(buttonSize);
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);

        button2.setPreferredSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        button3.setPreferredSize(buttonSize);
        button3.setMaximumSize(buttonSize);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to credentialsPanel
        credentialsPanel.add(button1);
        credentialsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        credentialsPanel.add(button2);
        credentialsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        credentialsPanel.add(button3);
    }

    public void setHosting() {
        // Update message
        messageLabel.setText("Waiting for player...");

        // Remove components
        credentialsPanel.removeAll();

        // Refresh display
        revalidate();
        repaint();

        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        JLabel[] labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setFont(new Font(font.getName(), Font.PLAIN, 30));
            labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            labels[i].setForeground(Color.BLACK);
            labels[i].setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            switch(i) {
                case 0:
                    labels[i].setText("PLAYERS");
                    labels[i].setFont(new Font(font.getName(), Font.BOLD, 45));
                    break;
                case 1:
                    labels[i].setText(getUsername() + " (host)");
                    break;
                case 2:
                    labels[i].setText("Waiting...");
                    break;
            }
            credentialsPanel.add(labels[i]);
        }
        JPanel buttonPanel = new JPanel();

        button1.setText("Return");
        button2.setText("Start");

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        credentialsPanel.add(buttonPanel);
        credentialsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    }

    public void setJoining() {
        // Update message
        messageLabel.setText("Waiting for host...");

        // Remove components
        credentialsPanel.removeAll();

        // Refresh display
        revalidate();
        repaint();

        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));

        JLabel[] labels = new JLabel[3];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setFont(new Font(font.getName(), Font.PLAIN, 30));
            labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            labels[i].setForeground(Color.BLACK);
            labels[i].setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            switch(i) {
                case 0:
                    labels[i].setText("PLAYERS");
                    labels[i].setFont(new Font(font.getName(), Font.BOLD, 45));
                    break;
                case 1:
                    labels[i].setText("Waiting (host)...");
                    break;
                case 2:
                    labels[i].setText(getUsername());
                    break;
            }
            credentialsPanel.add(labels[i]);
        }
        JPanel buttonPanel = new JPanel();

        button1.setText("Return");
        button2.setText("Start");

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        credentialsPanel.add(buttonPanel);
        credentialsPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    }

    public void setError(String msg) {
        messageLabel.setText(msg);
        messageLabel.setForeground(Color.RED);
    }
    public void setUsername(String username) {
        usernameField.setText(username);
    }
    public String getUsername() {
        return usernameField.getText();
    }
    public void setPassword(String password) {
        passwordField.setText(password);
    }
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
