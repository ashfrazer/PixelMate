package edu.uca.swe.GUI.Controllers;

import edu.uca.swe.GUI.Panels.*;
import edu.uca.swe.Game.Board;
import edu.uca.swe.Game.Connection.Client;
import edu.uca.swe.Game.Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Controller implements ActionListener {
    private JPanel container;
    private CardLayout cardLayout;
    private Font font;
    private boolean loginSuccessful;
    private Database database;
    private String username;
    private String playerRole;
    private edu.uca.swe.Game.Connection.Client client;
    private edu.uca.swe.Game.Connection.Server server;

    public Controller(JPanel container) {
        this.container = container;
        this.cardLayout = (CardLayout) container.getLayout();
        this.loginSuccessful = false;
        this.database = new Database();
        this.playerRole = "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
        HostPanel hostPanel = (HostPanel) container.getComponent(5);
        JoinPanel joinPanel = (JoinPanel) container.getComponent(6);
        GamePanel gamePanel = (GamePanel) container.getComponent(7);

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
        else if (command.equals("Back") || command.equals("mainmenu")) {
            System.out.println("Going back to the main menu!");
            // If we're in a game, send quit message to server
            if (client != null) {
                try {
                    client.sendToServer("quit");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            cardLayout.show(container, "mainmenu");
        }
        // Restart the game
        else if (command.equals("playagain")) {
            System.out.println("Restarting game!");
            // Create a new board
            Board board = new Board(this);
            // Create a new game panel with the same role and client
            gamePanel = new GamePanel(board, playerRole, client);
            // Update the client's game panel
            client.setGamePanel(gamePanel);
            // Remove the old game panel
            container.remove(7);
            // Add the new game panel
            container.add(gamePanel, "game");
            // Show the game panel
            cardLayout.show(container, "game");
            // Send start message to server
            try {
                client.sendToServer("start");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // Go to Play Menu
        else if (command.equals("playmenu")) {
            System.out.println("Going to play menu!");
            cardLayout.show(container, "playmenu");
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
            // Register new account
        } else if (command.equals("Register")) {
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
            // Host a game
        } else if (command.equals("Host")) {
            System.out.println("Now hosting...");
            playerRole = "host";

            // Prompt user to enter IP address to host game on (ipconfig LOCAL IP address)
            String serverIP = JOptionPane.showInputDialog(container, "Enter the server IP address:",
                    "Host Game", JOptionPane.QUESTION_MESSAGE);

            if (serverIP != null && !serverIP.trim().isEmpty()) {
                try {
                    client = new edu.uca.swe.Game.Connection.Client(serverIP.trim(), 5555, username, container,
                            cardLayout);
                    client.setController(this);
                    client.setHostPanel(hostPanel);
                    client.setJoinPanel(joinPanel);
                    client.openConnection();
                    client.sendToServer("host");
                    client.sendToServer("H_USERNAME: " + username);
                    cardLayout.show(container, "host");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(container, "Failed to connect to server: " + ex.getMessage(),
                            "Connection Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Host cancelled or invalid IP.");
            }
            // Join a game
        } else if (command.equals("Join")) {
            System.out.println("Now joining...");
            playerRole = "client";

            // Prompt user to enter local IP to join game on
            String serverIP = JOptionPane.showInputDialog(container, "Enter the server IP address:",
                    "Join Game", JOptionPane.QUESTION_MESSAGE);

            if (serverIP != null && !serverIP.trim().isEmpty()) {
                try {
                    client = new edu.uca.swe.Game.Connection.Client(serverIP.trim(), 5555, username, container,
                            cardLayout);
                    client.setController(this);
                    client.setHostPanel(hostPanel);
                    client.setJoinPanel(joinPanel);
                    client.openConnection();
                    client.sendToServer("client");
                    client.sendToServer("C_USERNAME: " + username);
                    System.out.println("Connected to server at " + serverIP);
                    this.client = client;
                    cardLayout.show(container, "join");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(container, "Failed to connect to host: " + ex.getMessage(),
                            "Connection Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Join cancelled or invalid IP.");
            }
            // Log out of account
        } else if (command.equals("Logout")) {
            System.out.println("Logging out!");
            loginSuccessful = false;
            cardLayout.show(container, "mainmenu");
            // Go back to menu
        } else if (command.equals("Return")) {
            System.out.println("Going back to menu.");
            cardLayout.show(container, "playmenu");
            // Start the game
        } else if (command.equals("Start")) {
            System.out.println("Starting game!");
            // Pass a board into a new GamePanel obj (to maintain state)
            Board board = new Board(this);
            gamePanel = new GamePanel(board, playerRole, client);

            client.setGamePanel(gamePanel);
            if (client != null) {
                try {
                    client.sendToServer("start");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            // Display board
            container.add(gamePanel, "game");
            gamePanel.revalidate();
            gamePanel.repaint();

            cardLayout.show(container, "game");
        }
    }

    private boolean validateLogin(String username, String password) {
        boolean isValid = false;

        try {
            // Database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pixelmate_login",
                    "player", "letsplaychess2025");

            // SQL query to fetch password for given username
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Get stored password
                String storedPassword = rs.getString("password");

                // Compare stored password with entered password
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

    public Client getClient(){return client;}

    public void showGameOver(String winner) {
        // Remove any existing game over panel
        Component[] components = container.getComponents();
        for (Component comp : components) {
            if (comp instanceof GameOverPanel) {
                container.remove(comp);
            }
        }

        // Create and add new game over panel
        GameOverPanel gameOverPanel = new GameOverPanel(this, winner);
        container.add(gameOverPanel, "gameover");

        // Show the game over panel
        cardLayout.show(container, "gameover");

        // Force a repaint
        container.revalidate();
        container.repaint();
    }
}