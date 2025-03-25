package GUI.Panels;

import GUI.Colors.Constants;
import GUI.Controllers.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPanel extends JPanel {
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;
    private BufferedImage knightIcon;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(Controller controller) {
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
        JLabel messageLabel = new JLabel("Please log into your account.");
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setFont(new Font(font.getName(), Font.PLAIN, 30));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // KNIGHT ICON
        JLabel iconLabel = new JLabel();

        try {
            File iconFile = new File("src/main/java/Icons/knight_black.png");
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
        JPanel credentialsPanel = new JPanel();
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

        // ENTER BUTTON
        JButton loginButton = new JButton("Enter");
        loginButton.setBackground(BROWN);
        loginButton.setForeground(Color.BLACK);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        loginButton.setFont(new Font(font.getName(), Font.PLAIN, 30));
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(buttonSize);
        loginButton.setMaximumSize(buttonSize);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(controller);

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
        credentialsPanel.add(backButton);
        credentialsPanel.add(loginButton);
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
