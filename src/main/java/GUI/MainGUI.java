package GUI;

import GUI.Colors.Constants;
import GUI.Controllers.Controller;
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(DARK_GREEN);

        // CardLayout container
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        container.setPreferredSize(new Dimension(700, 700));
        container.setMinimumSize(new Dimension(700, 700));
        container.setMaximumSize(new Dimension(700, 700));

        // Controller
        Controller controller = new Controller(container);

        // Add panels to container
        container.add(new MainMenuPanel(controller), "mainmenu");

        // Display Main Menu
        container.setPreferredSize(new Dimension(550, 550));
        container.setMinimumSize(new Dimension(550, 550));
        container.setMaximumSize(new Dimension(550, 550));
        cardLayout.show(container, "mainmenu");

        for (Component view:container.getComponents()){
            view.setBackground(DARK_GREEN);
        }

        // Configure JFrame
        this.setLayout(new GridBagLayout());
        this.add(container);
        this.setSize(800, 800);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
