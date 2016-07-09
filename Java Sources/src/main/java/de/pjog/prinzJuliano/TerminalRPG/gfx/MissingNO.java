package de.pjog.prinzJuliano.TerminalRPG.gfx;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.BasicTextImage;

public class MissingNO {
	
	public BasicTextImage getImage(int cols, int rows){
		BasicTextImage g = new BasicTextImage(cols, rows);
		
		g.setAll(new TextCharacter('/', (TextColor)TextColor.ANSI.GREEN, (TextColor)TextColor.ANSI.CYAN));
		
		return g;
	}

}
