package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.TextGUI.Listener;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

/**
 * Base for any basic view in this game.
 * @author PrinzJuliano
 *
 */
public abstract class AbstractView implements View{
	
	/**
	 * A simple window to be used.
	 */
	protected BasicWindow rootWindow;
	
	@Override
	public abstract void init(Storyboard story, WindowBasedTextGUI textGUI);
	
	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI, String communication){
		init(story, textGUI);
	}
	
	@Override
	public Listener getListener(Storyboard story) {
		return null;
	}
	
	@Override
	public void onResize(TerminalSize newSize) {
		center(newSize);
	}
	
	@Override
	public boolean overridesListener() {
		return false;
	}
	
	/**
	 * Centers the rootWindow based on the Terminal's size
	 * @param s the terminal size to calculate the center
	 */
	protected void center(TerminalSize s)
	{
		float cols = (float)s.getColumns()/2.0f-(float)rootWindow.getDecoratedSize().getColumns()/2.0f;
		float rows = (float)s.getRows()/2.0f-(float)rootWindow.getDecoratedSize().getRows()/2.0f;
		
		TerminalPosition center = new TerminalPosition((int)cols, (int)rows);
		rootWindow.setPosition(center);
	}
}
