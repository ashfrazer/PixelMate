package edu.uca.swe.GUI.Colors;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Constants {
    public static final Color BROWN = new Color(185, 148, 112);
    public static final Color DARK_GREEN = new Color(95, 111, 82);
    public static final Color LIGHT_GREEN = new Color(169, 179, 136);
    public static final Color CREAM = new Color(254, 250, 224);

    public static final Font FONT = loadFont();

    public static final ImageIcon board = new ImageIcon(loadBoard());

    private Constants() {}

    private static Font loadFont() {
        try {
            File fontFile = new File("src/main/resources/Jersey10-Regular.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont((float) 12);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (FontFormatException | IOException e) {
            return new Font("Arial", Font.PLAIN, 12);
        }
    }

    private static BufferedImage loadBoard() {
        try {
            File iconFile = new File("src/main/java/edu/uca/swe/Icons/board.png");
            return ImageIO.read(iconFile);
        } catch (IOException e) {
            return null;
        }
    }

}