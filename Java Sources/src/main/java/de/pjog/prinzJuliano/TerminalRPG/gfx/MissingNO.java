package de.pjog.prinzJuliano.TerminalRPG.gfx;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class MissingNO {
	
	public TextCharacter[][] getImage(int cols, int rows){
		TextCharacter[][] g = new TextCharacter[rows][cols];
		
		for(int y = 0; y < rows; y++)
			for(int x = 0; x < cols; x++)
				g[y][x] = new TextCharacter('/', (TextColor)TextColor.ANSI.GREEN, (TextColor)TextColor.ANSI.CYAN);
		
		return g;
	}

}
