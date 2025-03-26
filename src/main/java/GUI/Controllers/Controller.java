package GUI.Controllers;

import GUI.Panels.CreateAccountPanel;
import GUI.Panels.LoginPanel;
import GUI.Panels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Controller implements ActionListener {
    private JPanel container;
    private Font font;
    private boolean loginSuccessful;

    public Controller(JPanel container) {
        this.container = container;
        this.loginSuccessful = false;
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
            /*
            LOGIN LOGIC
             */
            cardLayout.show(container, "login");
        }
        // Create an account
        else if (command.equals("Create Account")) {
            System.out.println("Creating Account!");
            cardLayout.show(container, "createaccount");

            /*
            CREATE ACCOUNT LOGIC
             */
        }
        // Display credits
        else if (command.equals("Credits")) {
            System.out.println("Taking you to Credits!");
            cardLayout.show(container, "credits");
            /*
            CREDITS LOGIC
             */
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

            if (username.equals("test") && password.equals("1234")) {
                loginSuccessful = true;
                System.out.println("Welcome, " + username + "!");
                loginPanel.setLoginSuccessful();
            } else {
                loginSuccessful = false;
                loginPanel.setError("Login failed. Please try again.");
                System.out.println("Login failed.");
            }
            /*
            LOGIN ATTEMPT LOGIC
             */
        }
        else if (command.equals("Register")) {
            //TODO
            // REGISTER ACCOUNT IN DATABASE
            System.out.println("Registering...");
            CreateAccountPanel createAccountPanel = (CreateAccountPanel) container.getComponent(2);
            String username = createAccountPanel.getUsername();
            boolean isVerified = createAccountPanel.verifyPassword();
            if (isVerified) {
                System.out.println("Welcome aboard, " + username + "!");
            }
            else {
                System.out.println("Password creation failed.");
            }
        }
        else if (command.equals("Host")) {
            System.out.println("Now hosting...");
            loginPanel.setHosting();
        }
        else if (command.equals("Join")) {
            System.out.println("Now joining...");;
            loginPanel.setJoining();
        }
        else if (command.equals("Logout")) {
            //FIXME
            // IF LOGIN -> LOGOUT -> LOGIN, USER AUTOMATICALLY LOGS IN
            // WITHOUT HAVING TO RE-ENTER CREDENTIALS. USER DOES NOT FULLY
            // LOGOUT. BELIEVED TO BE RELATED TO CURRENT LOGINPANEL STATE.

            System.out.println("Logging out!");
            loginSuccessful = false;
            cardLayout.show(container, "mainmenu");
        }
        else if (command.equals("Return")) {
            System.out.println("Going back to menu.");
            loginPanel.setLoginSuccessful();
        }
    }
}
