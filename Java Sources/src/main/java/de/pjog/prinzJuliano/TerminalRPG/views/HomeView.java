package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class HomeView extends AbstractView {

	private String communication;
	
	@Override
	public void init(Storyboard story, WindowBasedTextGUI textGUI) {
		// TODO Auto-generated method stub
		rootWindow.setTitle("Hi");
		
		textGUI.addWindow(rootWindow);
	}
	
	@Override
	public void init(Storyboard story, WindowBasedTextGUI textGUI, String communication) {
		this.communication = communication;
		init(story, textGUI);
	}

}
