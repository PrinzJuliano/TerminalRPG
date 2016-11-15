package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.BorderLayout;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;
import de.pjog.prinzJuliano.TerminalRPG.gfx.BasicImageRenderer;
import de.pjog.prinzJuliano.TerminalRPG.gfx.SpriteLoader;

public class PictureViewer extends AbstractView{

	public String imageSrc = "gfx/Missingno.png";
	
	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background());
		
		rootWindow.setTitle("Picture Viewer");
		
		Panel p = new Panel();
		p.setLayoutManager(new BorderLayout());
		
		BasicImageRenderer r = SpriteLoader.loadSprite(imageSrc, 8, 8);
		
		p.addComponent(r, BorderLayout.Location.CENTER);
		
		Panel top = new Panel();
		
		top.addComponent(new Label("Sprite: " + imageSrc));
		final TextBox input = new TextBox().addTo(top);
		new Button("Load", new Runnable() {
			
			public void run() {
				imageSrc = input.getText();
				LoadingDialog d = (LoadingDialog)story.getViewByID(Storyboard.LOADINGDIALOG);
				d.setNextView(Storyboard.PICTUREVIEWER);
				story.switchToView(Storyboard.LOADINGDIALOG);
			}
		}).addTo(top);
		
		p.addComponent(top.withBorder(Borders.singleLine()));
		
		Button close = new Button("Back to Main", new Runnable() {

            @Override
            public void run() {
                story.switchToView(Storyboard.MAINMENU);

            }
        });
		
		p.addComponent(close, BorderLayout.Location.BOTTOM);
		
		rootWindow.setComponent(p);
		
		textGUI.addWindow(rootWindow);
		
	}
	
	

}
