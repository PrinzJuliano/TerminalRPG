package de.pjog.prinzJuliano.TerminalRPG.gfx;

import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

import de.pjog.prinzJuliano.TerminalRPG.Main;
import de.pjog.prinzJuliano.TerminalRPG.util.Resources;

/**
 * Class for loading images from the Resources folder utilizing the
 * {@link Resources#extractFile(String)} method.
 * 
 * @author PrinzJuliano
 *
 */
public class SpriteLoader {

	public SpriteLoader() {

	}

	/**
	 * <p>
	 * Load a Sprite from a file.<br>
	 * JSON Structure:<br>
	 * {<br>
	 * ['columns'] = 8, ['rows'] = 8, ['data'] = { [0] = { ['char'] = ' ',
	 * ['foreground'] = { ['r'] = 255, ['g'] = 255, ['b'] = 255, },
	 * ['background'] = { ['r'] = 0, ['g'] = 0, ['b'] = 0, }, }, [1] ... [2] ...
	 * [3] ... [4] ... [5] ... [6] ... [7] ... [8] ... [9] ... [10] ... . . .
	 * [63] ... } }
	 * </p>
	 * 
	 * @param path
	 *            the path in resources
	 * @return the image in [y][x] form
	 * @throws FileNotFoundException,
	 *             IOException file operations guys. O_o
	 */
	public static BasicImageRenderer loadSprite(String path, int expectedCols, int expectedRows) {
		try {
			if(Main.DEBUG){
				System.out.println("Loading " + path);
			}
			JSONObject json = new JSONObject(Resources.getFileContent(path));


			int columns = 0;
			int rows = 0;

			if (json.has("columns"))
				columns = json.getInt("columns");
			if (json.has("rows"))
				rows = json.getInt("rows");

			TextCharacter[][] img = new TextCharacter[rows][columns];

			if (json.has("data")) {
				JSONArray data = json.getJSONArray("data");
				for (int i = 0; i < columns * rows; i++) {
					JSONObject info = data.getJSONObject(i);
					char c = (json.has("char")) ? info.getString("char").charAt(0) : ' ';
					TextColor fore;

					if (info.has("foreground")) {

						JSONObject foreground = info.getJSONObject("foreground");
						fore = new TextColor.RGB(foreground.getInt("r"), foreground.getInt("g"),
								foreground.getInt("b"));
					} else {
						fore = new TextColor.RGB(255, 255, 255);
					}

					TextColor back;
					if (info.has("background")) {

						JSONObject background = info.getJSONObject("background");
						back = new TextColor.RGB(background.getInt("r"), background.getInt("g"),
								background.getInt("b"));
					} else {
						back = new TextColor.RGB(255,255,0);
					}
					TextCharacter ch = new TextCharacter(c, fore, back);
					img[i / columns ][i % columns ] = ch;
				}
				return new BasicImageRenderer(img);
			} else {
				System.out.println("Failed to load x1");
				return new BasicImageRenderer(new MissingNO().getImage(expectedCols, expectedRows));
			}
		} catch (Exception e) {
			System.out.println("Failed to load x2");
			e.printStackTrace();
			return new BasicImageRenderer(new MissingNO().getImage(expectedCols, expectedRows));
		}
	}

}
