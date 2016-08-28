package de.pjog.prinzJuliano.TerminalRPG;

import java.io.IOException;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Entry Point for the Game.
 * @author PrinzJuliano
 *
 */
public class Main {

	Storyboard story; //The Storyboard
	Terminal term; //The outer terminal
	Screen screen; //The inner rendering component

	//Do debugging or not
	public static final boolean DEBUG = true;

	/**
	 * Create a Terminal, Screen and start the Storyboard
	 */
	public Main() {
		try {
			//Create a Terminal and Screen
			term = new DefaultTerminalFactory().createTerminalEmulator();
			term.enterPrivateMode();
			screen = new TerminalScreen(term);

			//Initialize the Screen and clear it from text, etc.
			screen.startScreen();
			screen.clear();

			//Start our Tale
			story = new Storyboard(screen);
			story.start();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				screen.stopScreen();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Initialize the class for non static processing
	 * @param args Cheat Codes
	 */
	public static void main(String[] args) {
		new Main();
	}

}
