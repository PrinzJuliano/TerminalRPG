package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Main;
import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class DebugMenuView extends AbstractView{

	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		if(!Main.DEBUG){
			Dialog d = (Dialog) story.getViewByID(Storyboard.DIALOG);
			d.setCaption("DEBUGF -xa -ce -nxt -noaccess");
			d.setMessage("You can not use the debug tools!");
			d.setNextView(Storyboard.MAINMENU);
			story.switchToView(Storyboard.DIALOG);
		}
		rootWindow.setTitle("Debug Menu");
		
		Panel p = new Panel();
		p.setLayoutManager(new LinearLayout(Direction.VERTICAL).setSpacing(1));
		
		Button picViewer = new Button("Picture Viewer", new Runnable() {
			
			public void run() {
				story.switchToView(Storyboard.PICTUREVIEWER);
			}
		});
		Button back = new Button("back to main menu", new Runnable() {
			
			public void run() {
				story.switchToView(Storyboard.MAINMENU);
			}
		});
		
		p.addComponent(picViewer);
		p.addComponent(back);
		
		rootWindow.setComponent(p);
		
		textGUI.addWindow(rootWindow);
		
	}

}
