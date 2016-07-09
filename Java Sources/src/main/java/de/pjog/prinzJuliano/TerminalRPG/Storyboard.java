package de.pjog.prinzJuliano.TerminalRPG;

import java.io.EOFException;
import java.util.HashMap;

import org.json.JSONObject;

import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.AsynchronousTextGUIThread;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.SeparateTextGUIThread;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;

import de.pjog.prinzJuliano.TerminalRPG.gfx.CommonSprites;
import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;
import de.pjog.prinzJuliano.TerminalRPG.views.CrashDialog;
import de.pjog.prinzJuliano.TerminalRPG.views.Dialog;
import de.pjog.prinzJuliano.TerminalRPG.views.EndCardView;
import de.pjog.prinzJuliano.TerminalRPG.views.LoadGameView;
import de.pjog.prinzJuliano.TerminalRPG.views.MainMenuView;
import de.pjog.prinzJuliano.TerminalRPG.views.NewGameView;
import de.pjog.prinzJuliano.TerminalRPG.views.SettingsView;
import de.pjog.prinzJuliano.TerminalRPG.views.View;

/**
 * Managing Class for Views, Scene switching, etc.
 * 
 * @author PrinzJuliano
 *
 */
public class Storyboard {

	// CONSTANTS
	/**
	 * Links to {@link MainMenuView}.
	 */
	public static final int MAINMENU = 0;

	/**
	 * Links to {@link EndCardView}.
	 */
	public static final int ENDCARD = 1;

	/**
	 * Links to {@link NewGameView}.
	 */
	public static final int NEWGAME = 2;

	/**
	 * Links to {@link LoadGameView}.
	 */
	public static final int LOADGAME = 3;

	/**
	 * Links to {@link SettingsView}.
	 */
	public static final int SETTINGS = 4;

	/**
	 * Links to {@link CrashDialog}.
	 */
	public static final int CRASHDIALOG = 5;

	/**
	 * Links to {@link Dialog}.
	 */
	public static final int DIALOG = 6;

	public static CommonSprites Commons;

	Screen screen; // The screen from Main
	MultiWindowTextGUI textGUI; // Manager for all Windows
	AsynchronousTextGUIThread guiThread; // Keeps the GUI running

	HashMap<Integer, View> views; // All the Views

	int currentViewID = -1; // What view are we in?

	private boolean running = true; // Are we running (needed for closing the
									// game)

	private static TextGUI.Listener listener; // The only Listener for the
												// entire game (gets switched by
												// view)

	private static RPGCharacter loadedCharacter; // The Main Character sheet

	/**
	 * Initialize the screen, text based GUI and Views
	 * 
	 * @param screen
	 *            the screen from {@link Main}'s {@link Main#screen}.
	 */
	public Storyboard(Screen screen) {
		// Enable rendering, setup GUI
		this.screen = screen;

		Commons = new CommonSprites();

		textGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen); // Initialize
																						// textGUI
																						// with
																						// asynchronous
																						// thread
		textGUI.setBlockingIO(false); // Enable Input through the Keyboard
		textGUI.setEOFWhenNoWindows(false); // Keep screen alive if no windows
											// are present (essential for view
											// switching)
		textGUI.setTheme(LanternaThemes.getRegisteredTheme("default")); // Default
																		// Theme
																		// to be
																		// applied

		// Initialize View Map
		views = new HashMap<Integer, View>();
		// add views
		views.put(CRASHDIALOG, new CrashDialog());
		views.put(MAINMENU, new MainMenuView());
		views.put(ENDCARD, new EndCardView());
		views.put(NEWGAME, new NewGameView());
		views.put(DIALOG, new Dialog());

	}

	/**
	 * Let the Story begin. (Switch to MAINMENU + main Game Loop)
	 */
	public void start() {
		switchToView(MAINMENU);

		// Start thread
		guiThread = (AsynchronousTextGUIThread) textGUI.getGUIThread();
		guiThread.start();

		// Main Game Loop
		while (running) {
			try {
				if (screen.doResizeIfNecessary() != null) {
					views.get(currentViewID).onResize(screen.getTerminalSize());
				} else {
					textGUI.processInput();
				}
				if (textGUI.isPendingUpdate())
					textGUI.updateScreen();

			} catch (EOFException e) {
				System.out.println("Storyboard: Closing though outer EOF!");
				requestClose();

			} catch (Exception e) {
				// ignore the exception
			}
		}
	}

	/**
	 * <p>
	 * Switch to a specified view. View must be in {@link Storyboard#views} See
	 * {@link Storyboard#switchToView(int, String)}
	 * </p>
	 * 
	 * @param id
	 *            switch to that view
	 */
	public void switchToView(int id) {
		switchToView(id, null);
	}

	/**
	 * <p>
	 * Switch to a specified view. View must be in {@link Storyboard#views}.
	 * Uses an extra String for communication across Views. See
	 * {@link Storyboard#switchToView(int)}
	 * </p>
	 * 
	 * @param id
	 *            switch to that view
	 * @param com
	 *            give the view this information. Usually of type
	 *            {@link JSONObject}.
	 */
	public void switchToView(int id, String com) {
		// Debug message
		if (Main.DEBUG) {
			System.out.println("Storyboard: Try to load view " + id + " with Data: " + com);
		}

		// Do not switch if id is the current
		if (currentViewID == id) {
			return;
		}

		// Display error if switching to a non defined view occurs.
		if (!views.containsKey(id)) {
			((CrashDialog) views.get(CRASHDIALOG)).setMessage("View [" + id + "] could not be loaded");

			switchToView(CRASHDIALOG);

			return;
		}

		// Delete all present windows
		if (!textGUI.getWindows().isEmpty())
			for (Window b : textGUI.getWindows()) {
				b.close();
			}

		// Obligatory view-id handling
		currentViewID = id;

		// Remove the Listener so no interference or malbehavior can occur.
		textGUI.removeListener(listener);

		// If there is no message do not initialize with message
		if (com == null || com.isEmpty()) {
			views.get(id).init(this, textGUI);
		} else { // Initialize the new view with parameters.
			views.get(id).init(this, textGUI, com);
		}

		// Get the new listener
		listener = views.get(id).getListener(this);

		// Only set the listener if it is required.
		if (views.get(id).overridesListener())
			textGUI.addListener(listener);

		try {
			// Update the screen so no weird flickering or ghost windows appear.
			textGUI.updateScreen();
		} catch (Exception e) {
		}
	}

	/**
	 * Close the Game.
	 */
	public void requestClose() {
		running = false;
		guiThread.stop();
	}

	/**
	 * Getter for {@link Storyboard#running}
	 * 
	 * @return if the game is still running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Get a specified view. Used for pre-initialization data setup.
	 * 
	 * @param id
	 *            the view to search for
	 * @return {@code null} if the view is not found or the view it self
	 */
	public View getViewByID(int id) {
		if (views.containsKey(id))
			return views.get(id);
		else
			return null;
	}

	/**
	 * Getter for {@link Storyboard#loadedCharacter}. Basically the main
	 * Character Sheet.
	 * 
	 * @return The Main Character Sheet
	 */
	public RPGCharacter getCharacter() {
		return loadedCharacter;
	}

	/**
	 * Setter for {@link Storyboard#loadedCharacter}. Basically the main
	 * Character Sheet.
	 * 
	 * @param c
	 *            the new Character
	 */
	public void setCharacter(RPGCharacter c) {
		loadedCharacter = c.clone();
	}

}
