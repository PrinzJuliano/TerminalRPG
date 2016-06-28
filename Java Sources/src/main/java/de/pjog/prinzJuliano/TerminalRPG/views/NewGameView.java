package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import de.pjog.prinzJuliano.TerminalRPG.models.Character;
import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;
import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class NewGameView extends AbstractView {
	
	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background(42, 128, 255));
		rootWindow = new BasicWindow("Start a new game!");
		
		
		Panel rootP = new Panel();
		
		Panel content = new Panel();
		content.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		
		Panel labels = new Panel();
		Panel fields = new Panel();
		
		labels.addComponent(new Label("Name:"));
		final TextBox name = new TextBox();
		name.setPreferredSize(new TerminalSize(20, 1));
		fields.addComponent(name);
		
		labels.addComponent(new Label("Class:"));
		final ComboBox<String> classes = new ComboBox<String>();
		classes.addItem("Warrior");
		classes.addItem("Archer");
		classes.addItem("Rogue");
		classes.addItem("Mage");
		fields.addComponent(classes);
		
		
		content.addComponent(labels);
		content.addComponent(fields);
		
		Panel buttons = new Panel();
		buttons.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		
		buttons.addComponent(new Button("Start", new Runnable() {
			
			public void run() {
				Character ch = new Character();
				ch.setName(name.getText());
				
				ch.setFightingClass(FightingClasses.valueOf(classes.getSelectedItem().toUpperCase()));
				
				story.setCharacter(ch);
			}
		}));
		buttons.addComponent(new Separator(Direction.VERTICAL));
		
		buttons.addComponent(new Button("Back to main menu", new Runnable(){

			public void run() {
				story.switchToScreen(Storyboard.MAINMENU);
			}
			
		}));
		
		rootP.addComponent(content);
		
		rootP.addComponent(buttons);
		
		rootWindow.setComponent(rootP);
		
		textGUI.addWindow(rootWindow);
		
		//position in the center
		onResize(textGUI.getScreen().getTerminalSize());
	}

}
