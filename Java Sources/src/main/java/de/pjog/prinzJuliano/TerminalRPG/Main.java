package de.pjog.prinzJuliano.TerminalRPG;

import java.io.IOException;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {

	Storyboard story;
	Terminal term;
	Screen screen;

	public static final boolean DEBUG = true;

	public Main() {
		// do init our terminal
		try {
			term = new DefaultTerminalFactory().createTerminal();
			screen = new TerminalScreen(term);

			screen.startScreen();
			screen.clear();

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

	public static void main(String[] args) {
		new Main();
	}

}
