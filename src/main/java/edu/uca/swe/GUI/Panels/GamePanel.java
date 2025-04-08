package edu.uca.swe.GUI.Panels;

import edu.uca.swe.GUI.Colors.Constants;
import edu.uca.swe.GUI.Controllers.Controller;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Color LIGHT_GREEN;
    private ImageIcon boardIcon;

    public GamePanel(Controller controller) {
        // Initialize color
        LIGHT_GREEN = Constants.LIGHT_GREEN;

        // Initialize board GUI
        boardIcon = Constants.board;

        // Set layout of panel
        this.setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LIGHT_GREEN);
        this.add(mainPanel, BorderLayout.CENTER);

        // BOARD LABEL
        JLabel boardLabel = new JLabel(boardIcon);
        mainPanel.add(boardLabel, BorderLayout.CENTER);
    }
}
