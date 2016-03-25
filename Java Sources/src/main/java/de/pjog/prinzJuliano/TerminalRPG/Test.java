package de.pjog.prinzJuliano.TerminalRPG;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
		Terminal term = new DefaultTerminalFactory().createTerminal();
		Screen screen = new TerminalScreen(term);
		
		screen.startScreen();
		screen.clear();
		
		MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
		textGUI.setBlockingIO(false);
        textGUI.setEOFWhenNoWindows(true);
        
        try {
        	init(textGUI);
        	while(!textGUI.getWindows().isEmpty()) {
                textGUI.processInput();
                if(textGUI.isPendingUpdate()) {
                    textGUI.updateScreen();
                }
                else {
                    Thread.sleep(1);
                }
            }
        }
        finally {
            screen.stopScreen();
        }
	}
	
	private static void init(WindowBasedTextGUI textGUI)
	{
		final BasicWindow window1 = new BasicWindow("Just a simple Test");
		final BasicWindow window2 = new BasicWindow("Just another Text");
		final BasicWindow closeWindow = new BasicWindow("Close Dialog");
		
		Panel mainPanel = new Panel();
		
		mainPanel.addComponent(new Label("Hello"));
		mainPanel.addComponent(new Label("this"));
		mainPanel.addComponent(new Label("is"));
		mainPanel.addComponent(new Label("a"));
		mainPanel.addComponent(new Label("Test"));
		
		window1.setComponent(mainPanel);
		window2.setComponent(mainPanel);
		
		Panel closePanel = new Panel();
		
		closePanel.addComponent(new Button("Close", new Runnable(){

			public void run() {
				window1.close();
				window2.close();
				closeWindow.close();
			}
		}));
		
		closeWindow.setComponent(closePanel);
		
		textGUI.addWindow(window1);
		textGUI.addWindow(window2);
		textGUI.addWindow(closeWindow);
		
		window2.setPosition(new TerminalPosition(30, 1));
		closeWindow.setPosition(new TerminalPosition(1, 9));
	}

}
