package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.TextGUI.Listener;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class MainMenuView implements View{

	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background());
		
		final BasicWindow rootWindow = new BasicWindow("MainMenu.exe");
		
		Panel mainPanel = new Panel();
		mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL).setSpacing(1));
		
		mainPanel.addComponent(new Label("Welcome to my Game!"));
		
		Button bNewGame, loadGame, settings, exit;
		
		bNewGame = new Button("New Game");
		
		loadGame = new Button("Load a Save");
		
		settings = new Button("Settings");
		
		exit = new Button("Exit Game", new Runnable(){

			public void run() {
				story.switchToScreen(Storyboard.ENDCARD);
			}
			
		});
		
		mainPanel.addComponent(bNewGame);
		mainPanel.addComponent(loadGame);
		mainPanel.addComponent(settings);
		mainPanel.addComponent(exit);
		
		rootWindow.setComponent(mainPanel);
		
		textGUI.addWindow(rootWindow);
		
	}
	@Override
	public Listener getListener(Storyboard story) {
		return null;
	}

}
