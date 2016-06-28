package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class CrashDialog extends AbstractView{

	String message;
	
	public CrashDialog(String string) {
		message = string;
	}
	
	public CrashDialog() {
		//Convenience
	}

	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background());
		rootWindow = new BasicWindow("Error");
		
		Panel p = new Panel();
		p.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Label caption = new Label("Something went wrong!");
		caption.setForegroundColor(TextColor.ANSI.YELLOW);
		
		p.addComponent(caption);
		
		Separator sep = new Separator(Direction.HORIZONTAL);
		p.addComponent(sep);
		
		Label error = new Label(message);
		p.addComponent(error);
		
		Button close = new Button("Close", new Runnable() {
			
			@Override
			public void run() {
				story.requestClose();
				
			}
		});
		p.addComponent(sep);
		p.addComponent(close);
		
		rootWindow.setComponent(p);
		
		textGUI.addWindow(rootWindow);
		
		onResize(textGUI.getScreen().getTerminalSize());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
