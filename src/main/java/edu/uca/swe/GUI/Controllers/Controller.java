package edu.uca.swe.GUI.Controllers;

import edu.uca.swe.GUI.Panels.CreateAccountPanel;
import edu.uca.swe.GUI.Panels.LoginPanel;
import edu.uca.swe.Game.Database.Database;
import edu.uca.swe.GUI.Panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Controller implements ActionListener {
    private JPanel container;
    private Font font;
    private boolean loginSuccessful;
    private Database database;
    private String username;
    private String playerRole;
    private edu.uca.swe.Game.Connection.Client client;
    private edu.uca.swe.Game.Connection.Server server;

    public Controller(JPanel container) {
        this.container = container;
        this.loginSuccessful = false;
        this.database = new Database();
        this.playerRole = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cardLayout = (CardLayout) container.getLayout();
        LoginPanel loginPanel = (LoginPanel) container.getComponent(1);

        // Go to Login menu
        if (command.equals("Login")) {
            System.out.println("Logging in!");
            loginSuccessful = false;
            cardLayout.show(container, "login");
        }
        // Create an account
        else if (command.equals("Create Account")) {
            System.out.println("Creating Account!");
            cardLayout.show(container, "createaccount");
        }
        // Display credits
        else if (command.equals("Credits")) {
            System.out.println("Taking you to Credits!");
            cardLayout.show(container, "credits");
        }
        // Go back to the Main Menu
        else if (command.equals("Back")) {
            System.out.println("Going back to the main menu!");
            cardLayout.show(container, "mainmenu");
        }
        // Submit credentials for validation
        else if (command.equals("Enter")) {
            String username = loginPanel.getUsername();
            String password = loginPanel.getPassword();

            // Verify login
            if (validateLogin(username, password)) {
                loginSuccessful = true;
                System.out.println("Welcome, " + username + "!");
                setUsername(username);
                cardLayout.show(container, "playmenu");
            } else {
                loginSuccessful = false;
                loginPanel.setError("Login failed. Please try again.");
                System.out.println("Login failed.");
            }
        }
        else if (command.equals("Register")) {
            System.out.println("Registering...");
            CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
            String username = createAccountPanel.getUsername();
            boolean isVerified = createAccountPanel.verifyPassword();
            if (isVerified) {
                boolean accountCreated = database.createNewAccount(username, createAccountPanel.getPassword());
                if (accountCreated) {
                    System.out.println("Welcome aboard, " + username + "!");
                    setUsername(username);
                } else {
                    System.out.println("Account creation failed.");
                }
            } else {
                System.out.println("Password creation failed.");
            }
        }
        else if (command.equals("Host")) {
            System.out.println("Now hosting...");
            playerRole = "host";

            new Thread(() -> {
                server = new edu.uca.swe.Game.Connection.Server(5555);
                try {
                    server.listen();
                    System.out.println("Server listening...");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            client = new edu.uca.swe.Game.Connection.Client("localhost", 5555);
            try {
                client.openConnection();
                client.sendToServer("host");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            cardLayout.show(container, "host");
        }
        else if (command.equals("Join")) {
            System.out.println("Now joining...");
            playerRole = "client";

            String hostIP = JOptionPane.showInputDialog(container, "Enter the host IP address:", "Join Game", JOptionPane.QUESTION_MESSAGE);

            if (hostIP != null && !hostIP.trim().isEmpty()) {
                try {
                    edu.uca.swe.Game.Connection.Client client = new edu.uca.swe.Game.Connection.Client(hostIP.trim(), 5555);
                    client.openConnection();
                    System.out.println("Connected to server at " + hostIP);
                    this.client = client;
                    cardLayout.show(container, "join");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(container, "Failed to connect to host: " + ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Join cancelled or invalid IP.");
            }
        }
        else if (command.equals("Logout")) {
            System.out.println("Logging out!");
            loginSuccessful = false;
            cardLayout.show(container, "mainmenu");
        }
        else if (command.equals("Return")) {
            System.out.println("Going back to menu.");
            cardLayout.show(container, "playmenu");
        }
        else if (command.equals("Start")) {
            System.out.println("Starting game!");
            GamePanel gamePanel = new GamePanel(this, playerRole);
            container.add(gamePanel, "game");
            cardLayout.show(container, "game");
        }
    }

    private boolean validateLogin(String username, String password) {
        boolean isValid = false;

        try {
            // Database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pixelmate_login", "player", "letsplaychess2025");

            // Prepare SQL query to fetch the password for the given username
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Get the stored password
                String storedPassword = rs.getString("password");

                // Compare the stored password with entered password
                if (storedPassword.equals(password)) {
                    isValid = true;
                }
            }

            // Close SQL objs
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isValid;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getUsername() {
        return username;
    }

    public String getPlayerRole() {
        return playerRole;
    }

}
