package GUI;

import GUI.Colors.Constants;
import GUI.Controllers.Controller;
import GUI.Panels.CreateAccountPanel;
import GUI.Panels.LoginPanel;
import GUI.Panels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private Color BROWN;
    private Color DARK_GREEN;
    private Color LIGHT_GREEN;
    private Color CREAM;
    private Font font;

    public MainGUI() {
        // Initialize colors
        BROWN = Constants.BROWN;
        DARK_GREEN = Constants.DARK_GREEN;
        LIGHT_GREEN = Constants.LIGHT_GREEN;
        CREAM = Constants.CREAM;

        // Initialize font
        font = Constants.FONT;

        // Configure JFrame
        this.setTitle("PixelMate");
        this.setIconImage(new ImageIcon("src/main/java/Icons/knight_black.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(DARK_GREEN);

        // CardLayout container
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        // Controller
        Controller controller = new Controller(container);

        // Add panels to container
        container.add(new MainMenuPanel(controller), "mainmenu");
        container.add(new LoginPanel(controller), "login");
        container.add(new CreateAccountPanel(controller), "createaccount");

        // Display Main Menu
        container.setPreferredSize(new Dimension(600, 600));
        container.setMinimumSize(new Dimension(600, 600));
        container.setMaximumSize(new Dimension(600, 600));
        cardLayout.show(container, "mainmenu");

        // Make background dark green
        for (Component view:container.getComponents()){
            view.setBackground(DARK_GREEN);
        }

        // Configure JFrame
        this.setLayout(new GridBagLayout());
        this.add(container);
        this.setSize(600, 600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
