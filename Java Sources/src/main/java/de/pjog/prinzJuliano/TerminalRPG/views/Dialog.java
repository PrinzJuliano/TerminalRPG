package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

/**
 * A basic Dialog.
 * @author PrinzJuliano
 *
 */
public class Dialog extends AbstractView {

	private int nextView;
	private String message;
	private String communication;
	private String caption;
	
	/**
	 * Convenience Constructor.
	 */
	public Dialog(){
		
	}
	
	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background());
		rootWindow = new BasicWindow(caption);
		
		Panel p = new Panel();
		p.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Label error = new Label(message);
		p.addComponent(error);
		
		Button close = new Button("Ok", new Runnable() {
			
			@Override
			public void run() {
				story.switchToView(nextView, communication);
				
			}
		});
		p.addComponent(new Separator(Direction.HORIZONTAL));
		p.addComponent(close);
		
		rootWindow.setComponent(p);
		
		textGUI.addWindow(rootWindow);
		
		onResize(textGUI.getScreen().getTerminalSize());
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

	/**
	 * Set the message that should be displayed.
	 * @param message the message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Set the Caption of this Dialog.
	 * @param caption the caption.
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	
}
