package de.pjog.prinzJuliano.TerminalRPG.views;

import org.json.JSONObject;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.ComboBox.Listener;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Main;
import de.pjog.prinzJuliano.TerminalRPG.Storyboard;
import de.pjog.prinzJuliano.TerminalRPG.gfx.BasicImageRenderer;
import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;
import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;
import de.pjog.prinzJuliano.TerminalRPG.models.Stats;

/**
 * a View for Character creation. See {@link RPGCharacter}.
 * 
 * @author PrinzJuliano
 *
 */
public class NewGameView extends AbstractView {

	private ComboBox<String> classes;
	private Label points, vitPoints, strPoints, dexPoints, INTPoints, LUCKPoints;
	private TextBox name;
	private boolean sure = false;
	private BasicImageRenderer classPreview;
	private NewGameView instance;
	private boolean classesUpdated = false;

	/**
	 * Initialize everything with default values. Used for cross view
	 * communication
	 */
	public NewGameView() {
		instance = this;
		points = new Label("20");
		vitPoints = new Label("1");
		strPoints = new Label("1");
		dexPoints = new Label("1");
		INTPoints = new Label("1");
		LUCKPoints = new Label("1");
		classes = new ComboBox<>();
		name = new TextBox();
		classPreview = Storyboard.Commons.getImage(FightingClasses.WARRIOR.name(), 8, 8);
	}

	@Override
	public void init(final Storyboard story, final WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(new Background(42, 128, 255));
		rootWindow.setTitle("Start a new game!");

		Panel rootP = new Panel();

		Panel content = new Panel();
		content.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

		Panel labels = new Panel();
		Panel fields = new Panel();

		labels.addComponent(new Label("Name:"));
		name.setPreferredSize(new TerminalSize(20, 1));
		fields.addComponent(name);

		labels.addComponent(new Label("Class:"));

		if (classes.getItemCount() == 0) {
			classes.addItem("Warrior");
			classes.addItem("Archer");
			classes.addItem("Rogue");
			classes.addItem("Mage");
			classes.addListener(new Listener() {

				public void onSelectionChanged(int newOne, int old) {
					System.out.print("Switched from " + old);
					System.out.println(" to " + newOne);

					classPreview = Storyboard.Commons.getImage(FightingClasses.valueOf(classes.getText().toUpperCase()).name(), 8, 8);

					if(!classesUpdated){
						LoadingDialog d = (LoadingDialog) story.getViewByID(Storyboard.LOADINGDIALOG);
						d.setNextView(Storyboard.NEWGAME);
						story.switchToView(Storyboard.LOADINGDIALOG, instance.toString());
					}
					else
						classesUpdated = false;
				}

			});
		}
		fields.addComponent(classes);

		// Stats
		labels.addComponent(new Label("Assign Points:"));
		labels.addComponent(new Label("VIT: "));
		labels.addComponent(new Label("STR: "));
		labels.addComponent(new Label("DEX: "));
		labels.addComponent(new Label("INT: "));
		labels.addComponent(new Label("LCK: "));

		fields.addComponent(points);

		/// VITALITY
		Panel vit = new Panel();
		vit.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

		vit.addComponent(vitPoints);
		vit.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nVitPoints = Integer.parseInt(vitPoints.getText());
				if (nVitPoints > 1) {
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

		str.addComponent(strPoints);
		str.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nStrPoints = Integer.parseInt(strPoints.getText());
				if (nStrPoints > 1) {
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

		dex.addComponent(dexPoints);
		dex.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nDexPoints = Integer.parseInt(dexPoints.getText());
				if (nDexPoints > 1) {
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

		INT.addComponent(INTPoints);
		INT.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nINTPoints = Integer.parseInt(INTPoints.getText());
				if (nINTPoints > 1) {
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

		LUCK.addComponent(LUCKPoints);
		LUCK.addComponent(new Button("<", new Runnable() {
			public void run() {
				int totalPoints = Integer.parseInt(points.getText());
				int nLUCKPoints = Integer.parseInt(LUCKPoints.getText());
				if (nLUCKPoints > 1) {
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
				story.switchToView(Storyboard.MAINMENU);
			}

		}));
		buttons.addComponent(new Separator(Direction.VERTICAL));

		buttons.addComponent(new Button("Start", new Runnable() {

			public void run() {
				String msg = "";
				JSONObject root = new JSONObject();
				boolean isValid = true;
				if (name.getText().isEmpty()) {
					// Pack some data
					msg += "You forgot to enter your name!\n";

					root.append("class", classes.getSelectedIndex());
					root.append("VIT", vitPoints.getText());
					root.append("STR", strPoints.getText());
					root.append("DEX", dexPoints.getText());
					root.append("INT", INTPoints.getText());
					root.append("LCK", LUCKPoints.getText());
					root.append("points", points.getText());

					isValid = false;

				}
				if (!points.getText().equalsIgnoreCase("0")) {
					msg += "You forgot to spend all of your points!\n";

					root.append("name", name.getText());
					if (isValid) {
						root.append("class", classes.getSelectedIndex());
						root.append("VIT", vitPoints.getText());
						root.append("STR", strPoints.getText());
						root.append("DEX", dexPoints.getText());
						root.append("INT", INTPoints.getText());
						root.append("LCK", LUCKPoints.getText());
						root.append("points", points.getText());
					}

					isValid = false;
				}

				if (isValid) {
					if (!sure) {

						Dialog dialog = (Dialog) story.getViewByID(Storyboard.DIALOG);
						dialog.setCaption("Are you sure?");
						dialog.setMessage("Are you sure about the class and stats?");
						dialog.setNextView(Storyboard.NEWGAME);
						story.switchToView(Storyboard.DIALOG, new JSONObject().append("sure", "true").toString());

					} else {

						int nVIT = Integer.parseInt(vitPoints.getText());
                        int nSTR = Integer.parseInt(strPoints.getText());
						int nDEX = Integer.parseInt(dexPoints.getText());
						int nINT = Integer.parseInt(INTPoints.getText());
						int nLCK = Integer.parseInt(LUCKPoints.getText());

						Stats localstats = new Stats(nVIT, nSTR, nDEX, nINT, nLCK, 0, 0);

						RPGCharacter ch = new RPGCharacter(name.getText(),
								FightingClasses.valueOf(classes.getSelectedItem().toUpperCase()), 1, localstats);

						story.setCharacter(ch);
						story.switchToView(Storyboard.HOME);
					}
				}

				if (!isValid) {
					Dialog dialog = (Dialog) story.getViewByID(Storyboard.DIALOG);
					dialog.setCaption("Missing Data!");
					dialog.setMessage(msg);
					dialog.setNextView(Storyboard.NEWGAME);
					story.switchToView(Storyboard.DIALOG, root.toString());
				}
			}
		}));

		rootP.addComponent(content);

		rootP.addComponent(buttons);

		Panel realRoot = new Panel();
		realRoot.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		realRoot.addComponent(rootP);
		realRoot.addComponent(classPreview);

		rootWindow.setComponent(realRoot);

		textGUI.addWindow(rootWindow);

		// position in the center
		onResize(textGUI.getScreen().getTerminalSize());
	}

	/**
	 * Takes a {@link JSONObject} with name, points &amp; VIT &amp; STR &amp;
	 * DEX &amp; INT &amp; LCK and a class.<br>
	 * Class {@link NewGameView#init(Storyboard, WindowBasedTextGUI)}
	 * 
	 * @param story
	 *            the storyboard
	 * @param textGUI
	 *            the gui to draw to
	 */
	public void init(Storyboard story, WindowBasedTextGUI textGUI, String communication) {
		this.init(story, textGUI);
		if (Main.DEBUG)
			System.out.println("NewGameView: Got Data: " + communication);

		// Handle communication properly

		JSONObject root = new JSONObject(communication);

		if (root.has("name") && root.getString("name") != null) {
			if (Main.DEBUG)
				System.out.println("NewGameView: Found Name: " + root.getString("name"));
		}
		if (root.has("points") && root.has("VIT") && root.has("STR") && root.has("LCK") && root.has("DEX")
				&& root.has("INT")) {
			points.setText(root.getString("points"));
			vitPoints.setText(root.getString("VIT"));
			strPoints.setText(root.getString("STR"));
			dexPoints.setText(root.getString("DEX"));
			INTPoints.setText(root.getString("INT"));
			LUCKPoints.setText(root.getString("LCK"));
			if (Main.DEBUG)
				System.out.println("NewGameView: Found Attributes");
		}
		if (root.has("class")) {
			classesUpdated  = true;
			classes.setSelectedIndex(root.getInt("class"));
            classPreview = Storyboard.Commons.getImage(FightingClasses.valueOf(classes.getText().toUpperCase()).name(), 8, 8);
			
			if (Main.DEBUG)
				System.out.println("NewGameView: Found class: " + root.getInt("class"));
		}
		if (root.has("sure")) {
			this.sure = true;
		}

	}
	
	@Override
	public String toString() {
		JSONObject root = new JSONObject();
		if(!name.getText().isEmpty())root.put("name", name.getText());
		root.put("class", classes.getSelectedIndex());
		root.put("VIT", vitPoints.getText());
		root.put("STR", strPoints.getText());
		root.put("DEX", dexPoints.getText());
		root.put("INT", INTPoints.getText());
		root.put("LCK", LUCKPoints.getText());
		root.put("points", points.getText());
		return root.toString();
	}

}
