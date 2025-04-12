package edu.uca.swe.Game;

import java.sql.*;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate() {
        String url = "jdbc\\:mysql\\://localhost\\:3306/pixelmate_login";
        String db_username = "player";
        String db_password = "letsplaychess2025";

        try (Connection connection = DriverManager.getConnection(url, db_username, db_password)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, this.username);
                stmt.setString(2, this.password);
                ResultSet resultSet = stmt.executeQuery();

                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
