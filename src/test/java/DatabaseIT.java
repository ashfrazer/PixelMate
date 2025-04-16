import edu.uca.swe.Game.Database.Database;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseIT {

    private static Database db;

    @BeforeClass
    public static void setupClass() throws Exception {
        // Set properties
        Properties props = new Properties();
        props.setProperty("url", "jdbc:mysql://localhost:3306/pixelmate_login");
        props.setProperty("user", "player");
        props.setProperty("password", "letsplaychess2025");

        try (FileOutputStream fos = new FileOutputStream("db.properties")) {
            props.store(fos, null);
        }

        // Ensure users table exists
        db = new Database();
        try (Connection conn = DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("user"),
                props.getProperty("password"))
            )
        {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id SERIAL PRIMARY KEY, " +
                        "username VARCHAR(50) UNIQUE NOT NULL, " +
                        "password VARCHAR(50) NOT NULL)"
                );
        }
    }

    @Before
    public void clearDatabase() throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pixelmate_login",
                "player", "letsplaychess2025")) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM users");
        }
    }

    @Test
    public void testCreateAndVerifyAccount() {
        String username = "test";
        String password = "1234";

        // Test account creation
        boolean created = db.createNewAccount(username, password);
        assertTrue("Account should be created", created);

        // Test duplicate account
        boolean duplicate = db.createNewAccount(username, password);
        assertFalse("Duplicate account creation should fail", duplicate);

        // Test account verification
        boolean verified = db.verifyAccount(username, password);
        assertTrue("Account should verify with correct credentials", verified);

        // Test account verification with wrong password
        boolean wrongPass = db.verifyAccount(username, "wrong_pw");
        assertFalse("Verification should fail with incorrect password", wrongPass);

        // Test account verification with wrong username
        boolean wrongUser = db.verifyAccount("not_a_user", password);
        assertFalse("Verification should fail with incorrect username", wrongUser);
    }
}
