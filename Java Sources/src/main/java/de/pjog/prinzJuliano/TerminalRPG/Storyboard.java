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

import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;
import de.pjog.prinzJuliano.TerminalRPG.views.CrashDialog;
import de.pjog.prinzJuliano.TerminalRPG.views.Dialog;
import de.pjog.prinzJuliano.TerminalRPG.views.EndCardView;
import de.pjog.prinzJuliano.TerminalRPG.views.MainMenuView;
import de.pjog.prinzJuliano.TerminalRPG.views.NewGameView;
import de.pjog.prinzJuliano.TerminalRPG.views.View;

/**
 * 
 * @author PrinzJuliano
 *
 */
public class Storyboard {

	// CONSTANTS
	public static final int MAINMENU	= 0;
	public static final int ENDCARD		= 1;
	public static final int NEWGAME		= 2;
	public static final int LOADGAME	= 3;
	public static final int SETTINGS	= 4;
	public static final int CRASHDIALOG = 5;
	public static final int DIALOG 		= 6;

	Screen screen;
	MultiWindowTextGUI textGUI;
	AsynchronousTextGUIThread guiThread;

	HashMap<Integer, View> views;

	int currentViewID = -1;

	private boolean running = true;
	
	private static TextGUI.Listener listener;
	
	private static RPGCharacter loadedCharacter;

	public Storyboard(Screen screen) {
		// enable rendering
		this.screen = screen;
		textGUI = new MultiWindowTextGUI(new SeparateTextGUIThread.Factory(), screen);
		textGUI.setBlockingIO(false);
		textGUI.setEOFWhenNoWindows(false);
		textGUI.setTheme(LanternaThemes.getRegisteredTheme("default"));
		
		views = new HashMap<Integer, View>();
		// add views
		views.put(CRASHDIALOG, new CrashDialog());
		views.put(MAINMENU, new MainMenuView());
		views.put(ENDCARD, new EndCardView());
		views.put(NEWGAME, new NewGameView());
		views.put(DIALOG, new Dialog());
	}

	public void start() {
		switchToView(MAINMENU);
		
		guiThread = (AsynchronousTextGUIThread)textGUI.getGUIThread();
        guiThread.start();
		
		while (running) {
			try {
				if(screen.doResizeIfNecessary() != null) {
					views.get(currentViewID).onResize(screen.getTerminalSize());
				}
				else{
					textGUI.processInput();
				}
				if(textGUI.isPendingUpdate())
					textGUI.updateScreen();
				
			}catch(EOFException e)
			{
				requestClose();
			} 
			catch (Exception e) {
				// ignore the exception
			}
		}
	}

	public void switchToView(int id) {
		switchToView(id, null);
	}
	
	public void switchToView(int id, String com)
	{
		if(Main.DEBUG)
		{
			System.out.println("Storyboard: Try to load view " + id + " with Data: " + com);
		}
		if (currentViewID == id) {
			return;
		}
		
		if(!views.containsKey(id)){
			((CrashDialog)views.get(CRASHDIALOG)).setMessage("View ["+id+"] could not be loaded");
			
			switchToView(CRASHDIALOG);
			
			return;
		}
		
		if(!textGUI.getWindows().isEmpty())
			for(Window b : textGUI.getWindows())
			{
				b.close();
			}
		currentViewID = id;
		textGUI.removeListener(listener);
		if(com == null || com.isEmpty())
		{
			views.get(id).init(this, textGUI);
		}
		else{
			views.get(id).init(this, textGUI, com);
		}
		listener = views.get(id).getListener(this);
		if(views.get(id).overridesListener())
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
	
	public View getViewByID(int id){
		return views.get(id);
	}
	
	public RPGCharacter getCharacter()
	{
		return loadedCharacter;
	}
	
	public void setCharacter(RPGCharacter c)
	{
		loadedCharacter = c.clone();
	}

}
