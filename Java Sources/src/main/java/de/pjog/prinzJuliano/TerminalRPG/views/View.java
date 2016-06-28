package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public interface View {
	
	public void init(final Storyboard story, WindowBasedTextGUI textGUI);
	
	public TextGUI.Listener getListener(Storyboard story);
	
	public boolean overridesListener();
	
	public void onResize(TerminalSize newSize);

}
