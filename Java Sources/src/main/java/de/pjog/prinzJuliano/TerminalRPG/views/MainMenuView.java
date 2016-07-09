package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

/**
 * The Main Menu. Let's you select between new game, load game, settings and close.
 * @author PrinzJuliano
 *
 */
public class MainMenuView extends AbstractView{
	
	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background());

		rootWindow.setTitle("MainMenu.exe");

		Panel mainPanel = new Panel();
		mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL).setSpacing(1));

		mainPanel.addComponent(new Label("Welcome to my Game!"));

		Button bNewGame, loadGame, settings, exit;

		bNewGame = new Button("New Game", new Runnable() {

			public void run() {
				story.switchToView(Storyboard.NEWGAME);
			}

		});

		loadGame = new Button("Load a Save", new Runnable() {

			public void run() {
				story.switchToView(Storyboard.LOADGAME);
			}

		});

		settings = new Button("Settings", new Runnable() {

			public void run() {
				story.switchToView(Storyboard.SETTINGS);
			}

		});

		exit = new Button("Exit Game", new Runnable() {

			public void run() {
				story.switchToView(Storyboard.ENDCARD);
			}

		});
		
		mainPanel.addComponent(bNewGame);
		mainPanel.addComponent(loadGame);
		mainPanel.addComponent(settings);
		mainPanel.addComponent(exit);
		
		rootWindow.setComponent(mainPanel);

		textGUI.addWindow(rootWindow);

		onResize(textGUI.getScreen().getTerminalSize());

	}
}
