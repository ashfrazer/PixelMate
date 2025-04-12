package edu.uca.swe.Game.Database;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;

public class Database {
    private Connection connection;
    private FileInputStream fis = null;
    private FileOutputStream fos = null;
    HashMap<String, String> database;

    public Database() {
        Properties prop = new Properties();

        try {
            fis = new FileInputStream("db.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> query(String query) {
        // Declare return val first
        ArrayList<String> list = new ArrayList<String>();
        String record = "";
        try {
            // 1. Create a Statement object
            Statement stmt = connection.createStatement();

            // 2. Execute the query on the statement
            ResultSet rs = stmt.executeQuery(query);

            // 3. Get metadata
            ResultSetMetaData rsmd = rs.getMetaData();

            int noFields = rsmd.getColumnCount();

            // 4. Iterate through each record
            while (rs.next()) {
                record = "";
                for (int i = 0; i < noFields; i++) {
                    record += rs.getString(i+1);
                    record += ",";
                }
                list.add(record);
            }
            if (list.isEmpty()) {
                return null;
            }
        } catch(SQLException e) {
            return null;
        }

        return list;
    }

    // Method for verifying a username and password.
    public boolean verifyAccount(String username, String password)
    {
        // Read the database file.
        readFile();

        // Stop if this account doesn't exist.
        if (database.get(username) == null)
            return false;

        // Check the username and password.
        if (database.get(username).equals(password))
            return true;
        else
            return false;
    }

    public boolean createNewAccount(String username, String password) {
        // Check if the username already exists in the database
        try {
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Username already exists
                return false;
            }

            // Insert new account
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
            insertStmt.setString(1, username);
            insertStmt.setString(2, password); // TODO: hash password later for security

            int rowsAffected = insertStmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized void readFile() {
        database = new HashMap<String, String>();

        try {
            fis = new FileInputStream("database.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();
            while(line != null) {
                String[] data = line.split("\\|");

                if (data.length == 2)
                    database.put(data[0], data[1]);

                line = reader.readLine();
            }

            fis.close();
        }

        catch (Exception exception) {
            return;
        }
    }
}
