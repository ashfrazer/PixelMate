package GUI.Controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Controller implements ActionListener {
    private JPanel container;
    private Font font;

    public Controller(JPanel container) {
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Login button
        if (command.equals("Login")) {
            System.out.println("Logging in!");

            /*
            LOGIN LOGIC
             */
        }
        else if (command.equals("Create Account")) {
            System.out.println("Creating Account!");

            /*
            CREATE ACCOUNT LOGIC
             */
        }


    }
}
