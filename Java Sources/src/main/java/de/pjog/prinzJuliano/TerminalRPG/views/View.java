package de.pjog.prinzJuliano.TerminalRPG.views;

import org.json.JSONObject;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

/**
 * Any view that gets drawn and handled by {@link Storyboard}
 * @author PrinzJuliano
 *
 */
public interface View {
	
	/**
	 * Initialize all the components for a working GUI.
	 * @param story the storyboard needed for view switching and other story related elements.
	 * @param textGUI the GUI to draw to
	 */
	public void init(final Storyboard story, WindowBasedTextGUI textGUI);
	
	/**
	 * Set the communication string (usually of type {@link JSONObject}) and call {@link View#init(Storyboard, WindowBasedTextGUI)}
	 * @param story the storyboard
	 * @param textGUI the GUI to draw to
	 * @param communication all the information for the view to enable cross view interaction.
	 */
	public void init(final Storyboard story, WindowBasedTextGUI textGUI, String communication);
	
	/**
	 * Get the Listener for specific views.
	 * @param story the storyboard
	 * @return the Listener
	 */
	public TextGUI.Listener getListener(Storyboard story);
	
	/**
	 * Is a custom Listener even needed?
	 * @return is a listener needed
	 */
	public boolean overridesListener();
	
	/**
	 * Code to do what ever when the terminal resizes
	 * @param newSize the new terminal size
	 */
	public void onResize(TerminalSize newSize);

}
