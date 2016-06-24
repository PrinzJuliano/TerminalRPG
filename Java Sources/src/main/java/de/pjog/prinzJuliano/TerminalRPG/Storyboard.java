package de.pjog.prinzJuliano.TerminalRPG;

import java.io.EOFException;
import java.util.HashMap;

import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.AsynchronousTextGUIThread;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.SeparateTextGUIThread;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;

import de.pjog.prinzJuliano.TerminalRPG.views.EndCardView;
import de.pjog.prinzJuliano.TerminalRPG.views.MainMenuView;
import de.pjog.prinzJuliano.TerminalRPG.views.View;

/**
 * 
 * @author PrinzJuliano
 *
 */
public class Storyboard {

	// CONSTANTS
	public static final int MAINMENU = 0;
	public static final int ENDCARD = 1;

	Screen screen;
	MultiWindowTextGUI textGUI;
	AsynchronousTextGUIThread guiThread;

	HashMap<Integer, View> views;

	int currentViewID = -1;

	private boolean running = true;
	
	private static TextGUI.Listener listener;

	public Storyboard(Screen screen) {
		// enable rendering
		this.screen = screen;
		textGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen);
		textGUI.setBlockingIO(false);
		textGUI.setEOFWhenNoWindows(false);
		textGUI.setTheme(LanternaThemes.getRegisteredTheme("default"));
		
		views = new HashMap<Integer, View>();
		// add views
		views.put(MAINMENU, new MainMenuView());
		views.put(ENDCARD, new EndCardView());
	}

	public void start() {
		switchToScreen(MAINMENU);
		
		guiThread = (AsynchronousTextGUIThread)textGUI.getGUIThread();
        guiThread.start();
		
		while (running) {
			try {
				textGUI.processInput();
				
			}catch(EOFException e)
			{
				requestClose();
			} 
			catch (Exception e) {
				// ignore the exception
			}
		}
	}

	public void switchToScreen(int id) {
		if (currentViewID == id) {
			return;
		}
		if(!textGUI.getWindows().isEmpty())
			for(Window b : textGUI.getWindows())
			{
				b.close();
			}
		currentViewID = id;
		textGUI.removeListener(listener);
		views.get(id).init(this, textGUI);
		listener = views.get(id).getListener(this);
		textGUI.addListener(listener);
		
		try {
			textGUI.updateScreen();
		} catch (Exception e) {
		}
			
	}
	
	public void requestClose(){
		running = false;
		guiThread.stop();
	}

	public boolean isRunning() {
		return running;
	}

}
