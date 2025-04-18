package edu.uca.swe.GUI;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;
import edu.uca.swe.GUI.Panels.*;
import edu.uca.swe.Game.Board;
import edu.uca.swe.Game.Connection.Client;
import edu.uca.swe.Game.Pieces.Piece;

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
        this.setIconImage(new ImageIcon("src/main/java/edu/uca/swe/Icons/knight_black.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(DARK_GREEN);

        // CardLayout container
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        // Controller
        Controller controller = new Controller(container);

        // Board
        Board board = new Board(controller);

        // Get player role
        String playerRole = controller.getPlayerRole();
        Client client = controller.getClient();

        // Add panels to container
        container.add(new MainMenuPanel(controller), "mainmenu");
        container.add(new LoginPanel(controller), "login");
        container.add(new CreateAccountPanel(controller), "createaccount");
        container.add(new CreditsPanel(controller), "credits");
        container.add(new PlayMenuPanel(controller), "playmenu");
        container.add(new HostPanel(controller), "host");
        container.add(new JoinPanel(controller), "join");
        container.add(new GamePanel(board, playerRole, client), "game");
        container.add(new GameOverPanel(controller, ""), "gameover");

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
