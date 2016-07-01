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
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;
import de.pjog.prinzJuliano.TerminalRPG.models.Character;
import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;

public class NewGameView extends AbstractView {

	@Override
	public void init(final Storyboard story, final WindowBasedTextGUI textGUI) {
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

		// Stats
		labels.addComponent(new Label("Assign Points:"));
		labels.addComponent(new Label("VIT: "));
		labels.addComponent(new Label("STR: "));
		labels.addComponent(new Label("DEX: "));
		labels.addComponent(new Label("INT: "));
		labels.addComponent(new Label("LCK: "));

		final Label points = new Label("25");
		fields.addComponent(points);

		/// VITALITY
		Panel vit = new Panel();
		vit.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		final Label vitPoints = new Label("0");
		vit.addComponent(vitPoints);
		vit.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nVitPoints = Integer.parseInt(vitPoints.getText());
				if (nVitPoints > 0) {
					nVitPoints--;
					totalPoints++;
					points.setText("" + totalPoints);
					vitPoints.setText("" + nVitPoints);

				}
			}
		}));
		vit.addComponent(new Button(">", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nVitPoints = Integer.parseInt(vitPoints.getText());
				if (totalPoints > 0) {
					nVitPoints++;
					totalPoints--;
					points.setText("" + totalPoints);
					vitPoints.setText("" + nVitPoints);

				}
			}
		}));
		fields.addComponent(vit);

		/// STRENGTH
		Panel str = new Panel();
		str.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		final Label strPoints = new Label("0");
		str.addComponent(strPoints);
		str.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nStrPoints = Integer.parseInt(strPoints.getText());
				if (nStrPoints > 0) {
					nStrPoints--;
					totalPoints++;
					points.setText("" + totalPoints);
					strPoints.setText("" + nStrPoints);

				}
			}
		}));
		str.addComponent(new Button(">", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nStrPoints = Integer.parseInt(strPoints.getText());
				if (totalPoints > 0) {
					nStrPoints++;
					totalPoints--;
					points.setText("" + totalPoints);
					strPoints.setText("" + nStrPoints);

				}
			}
		}));
		fields.addComponent(str);

		/// DEXTERITY
		Panel dex = new Panel();
		dex.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		final Label dexPoints = new Label("0");
		dex.addComponent(dexPoints);
		dex.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nDexPoints = Integer.parseInt(dexPoints.getText());
				if (nDexPoints > 0) {
					nDexPoints--;
					totalPoints++;
					points.setText("" + totalPoints);
					dexPoints.setText("" + nDexPoints);

				}
			}
		}));
		dex.addComponent(new Button(">", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nDexPoints = Integer.parseInt(dexPoints.getText());
				if (totalPoints > 0) {
					nDexPoints++;
					totalPoints--;
					points.setText("" + totalPoints);
					dexPoints.setText("" + nDexPoints);

				}
			}
		}));
		fields.addComponent(dex);

		/// INTELLIGENCE
		Panel INT = new Panel();
		INT.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		final Label INTPoints = new Label("0");
		INT.addComponent(INTPoints);
		INT.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nINTPoints = Integer.parseInt(INTPoints.getText());
				if (nINTPoints > 0) {
					nINTPoints--;
					totalPoints++;
					points.setText("" + totalPoints);
					INTPoints.setText("" + nINTPoints);

				}
			}
		}));
		INT.addComponent(new Button(">", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nINTPoints = Integer.parseInt(INTPoints.getText());
				if (totalPoints > 0) {
					nINTPoints++;
					totalPoints--;
					points.setText("" + totalPoints);
					INTPoints.setText("" + nINTPoints);

				}
			}
		}));
		fields.addComponent(INT);

		/// LUCK
		Panel LUCK = new Panel();
		LUCK.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		final Label LUCKPoints = new Label("0");
		LUCK.addComponent(LUCKPoints);
		LUCK.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nLUCKPoints = Integer.parseInt(LUCKPoints.getText());
				if (nLUCKPoints > 0) {
					nLUCKPoints--;
					totalPoints++;
					points.setText("" + totalPoints);
					LUCKPoints.setText("" + nLUCKPoints);

				}
			}
		}));
		LUCK.addComponent(new Button(">", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nLUCKPoints = Integer.parseInt(LUCKPoints.getText());
				if (totalPoints > 0) {
					nLUCKPoints++;
					totalPoints--;
					points.setText("" + totalPoints);
					LUCKPoints.setText("" + nLUCKPoints);

				}
			}
		}));
		fields.addComponent(LUCK);

		content.addComponent(labels);
		content.addComponent(fields);

		Panel buttons = new Panel();
		buttons.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

		buttons.addComponent(new Button("Back to main menu", new Runnable() {

			public void run() {
				story.switchToScreen(Storyboard.MAINMENU);
			}

		}));
		buttons.addComponent(new Separator(Direction.VERTICAL));

		buttons.addComponent(new Button("Start", new Runnable() {

			public void run() {
				Character ch = new Character();
				if(name.getText().isEmpty())
				{
					MessageDialog.showMessageDialog(textGUI, "", "", MessageDialogButton.OK);
				}
				else{
					ch.setName(name.getText());
	
					ch.setFightingClass(FightingClasses.valueOf(classes.getSelectedItem().toUpperCase()));
	
					story.setCharacter(ch);
				}
			}
		}));

		rootP.addComponent(content);

		rootP.addComponent(buttons);

		rootWindow.setComponent(rootP);

		textGUI.addWindow(rootWindow);

		// position in the center
		onResize(textGUI.getScreen().getTerminalSize());
	}

}
