package de.pjog.prinzJuliano.TerminalRPG.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

import de.pjog.prinzJuliano.TerminalRPG.Main;
import de.pjog.prinzJuliano.TerminalRPG.util.Resources;

/**
 * Class for loading images from the Resources folder utilizing the
 * {@link Resources#extractFile(String)} method.
 *
 * @author PrinzJuliano
 */
public class SpriteLoader {

    public SpriteLoader() {

    }

    /**
     * <p>
     * Load a Sprite from a file.<br>
     * </p>
     *
     * @param path the path in resources
     * @return the image in [y][x] form
     */
    public static BasicImageRenderer loadSprite(String path, int expectedCols, int expectedRows) {
        try {
            if (Main.DEBUG) {
                System.out.println("Loading " + path);
            }

            BufferedImage bi = ImageIO.read(Resources.extractFile(path));

            int columns = bi.getWidth();
            int rows = bi.getHeight();

            TextCharacter[][] img = new TextCharacter[rows][columns];

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    Color c = new Color(bi.getRGB(x, y));
                    img[y][x] = new TextCharacter(' ', TextColor.ANSI.BLACK, new TextColor.RGB(c.getRed(), c.getGreen(), c.getBlue()));
                }
            }
            return new BasicImageRenderer(img);

        } catch (Exception e) {
            System.out.println("Failed to load x2");
            e.printStackTrace();
            return new BasicImageRenderer(new MissingNO().getImage(expectedCols, expectedRows));
        }
    }

}
