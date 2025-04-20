import edu.uca.swe.Game.Database.Database;
import edu.uca.swe.GUI.Panels.LoginPanel;
import edu.uca.swe.GUI.Controllers.Controller;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JPanel;
import java.awt.CardLayout;

import org.junit.*;
import static org.junit.Assert.*;

public class DatabaseIT {

    private static Database db;
    private LoginPanel loginPanel;
    private Controller controller;

    @BeforeClass
    public static void setupClass() throws Exception {
        // Initialize database connection
        System.out.println("Initializing database connection...");
        db = new Database();
        System.out.println("Database connection initialized");

        // Check connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pixelmate_login",
                "player", "letsplaychess2025")) {
            System.out.println("Successfully connected to database");
        }
    }

    @Before
    public void setup() throws Exception {
        // Create mock container for controller
        JPanel container = new JPanel(new CardLayout());
        controller = new Controller(container);
        loginPanel = new LoginPanel(controller);
    }

    @AfterClass
    public static void cleanUp() throws Exception {
        // Delete new_user account
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pixelmate_login",
                "player", "letsplaychess2025")) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM users WHERE username IN ('new_user', 'new_user_login_panel')");
        }
    }

    @Test
    public void testCreateAndVerifyAccount() {
        String username = "new_user";
        String password = "123456";

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

    @Test
    public void testLoginPanelIntegration() {
        // Create test account
        String username = "new_user_login_panel";
        String password = "123456";

        boolean created = db.createNewAccount(username, password);
        assertTrue("Account creation should succeed", created);

        // Test successful login
        boolean loginSuccess = db.verifyAccount(username, password);
        assertTrue("Login should succeed with correct credentials", loginSuccess);

        // Test failed login with wrong password
        boolean wrongPass = db.verifyAccount(username, "wrong_pass");
        assertFalse("Login should fail with incorrect password", wrongPass);

        // Test failed login with wrong username
        boolean wrongUser = db.verifyAccount("wrong_user", password);
        assertFalse("Login should fail with incorrect username", wrongUser);

        // Test empty credentials
        boolean emptyUser = db.verifyAccount("", password);
        assertFalse("Login should fail with empty username", emptyUser);

        boolean emptyPass = db.verifyAccount(username, "");
        assertFalse("Login should fail with empty password", emptyPass);
    }

    @Test
    public void testLoginPanelErrorHandling() {
        // Test with null credentials
        boolean nullUser = db.verifyAccount(null, "password");
        assertFalse("Login should fail with null username", nullUser);

        boolean nullPass = db.verifyAccount("username", null);
        assertFalse("Login should fail with null password", nullPass);
    }

    @Test
    public void testVerifyTestAccountExists() {
        String username = "test";
        String password = "1234";

        // Create account if it doesn't exist
        boolean created = db.createNewAccount(username, password);

        // Verify existence and credentials
        boolean verified = db.verifyAccount(username, password);

        assertTrue("Test account should exist and verify correctly", verified);
    }
}
