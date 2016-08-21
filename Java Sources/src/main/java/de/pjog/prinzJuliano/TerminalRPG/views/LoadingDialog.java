package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class LoadingDialog extends AbstractView{
	private int nextView;
	private String communication;

	
	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background());
		rootWindow.setTitle("Loading . . .");
		
		Panel p = new Panel();
		p.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Label text = new Label("The next section is loading!");
		p.addComponent(text);
		
		rootWindow.setComponent(p);
		
		textGUI.addWindow(rootWindow);
		
		onResize(textGUI.getScreen().getTerminalSize());
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	story.switchToView(nextView, communication);
		            }
		        }, 
		        500 
		);
	}

	@Override
	public void init(Storyboard story, WindowBasedTextGUI textGUI, String communication) {
		this.communication = communication;
		init(story, textGUI);
		
	}

	/**
	 * Sets the view to change to.
	 * @param nextView the view based on {@link Storyboard}'s views.
	 */
	public void setNextView(int nextView) {
		this.nextView = nextView;
	}

	

}
