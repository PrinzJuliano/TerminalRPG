package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.*;
import de.pjog.prinzJuliano.TerminalRPG.Storyboard;
import de.pjog.prinzJuliano.TerminalRPG.gfx.BasicImageRenderer;
import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;
import de.pjog.prinzJuliano.TerminalRPG.util.Saves;

/**
 * Main Game view. All the interaction starts here!
 *
 * @author PrinzJuliano
 */
public class HomeView extends AbstractView {

    private String communication;

    @Override
    public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
        rootWindow.setTitle("-[Hub]-");

        // Main panel
        Panel root = new Panel();
        root.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

        // Add character Preview
        Panel showCase = new Panel();
        showCase.setLayoutManager(new GridLayout(2));

        RPGCharacter character = story.getCharacter();

        // Name
        Label nameLabel = new Label("Name: ");
        Label name = new Label(character.getName());

        // Preview
        Label previewLabel = new Label("Looks: ");
        BasicImageRenderer previewImg = Storyboard.Commons.getImage(character.getFightingClass().name(), 8, 8);

        // Stats
        Label statsLabel = new Label("Stats:");

        Label hpLabel = new Label("Health:");
        Label hpValue = new Label("" + character.getStats().getHp() + " / " + character.getStats().getMaxHp());

        Label vitLabel = new Label("Vitality:");
        Label vitPoints = new Label("" + character.getStats().getVitality());

        Label strLabel = new Label("Strength:");
        Label strPoints = new Label("" + character.getStats().getStrength());

        Label dexLabel = new Label("Dexterity:");
        Label dexPoints = new Label("" + character.getStats().getDexterity());

        Label intLabel = new Label("Intelligence:");
        Label intPoints = new Label("" + character.getStats().getIntelligence());

        Label lckLabel = new Label("Luck:");
        Label lckPoints = new Label("" + character.getStats().getLuck());

        Label stealthLabel = new Label("Stealth:");
        Label stealthPoints = new Label("" + character.getStats().getStealth());

        Label defenceLabel = new Label("Defence:");
        Label defencePoints = new Label("" + character.getStats().getDefense());

        // Add everything to showCase Panel
        showCase.addComponent(nameLabel);
        showCase.addComponent(name);

        showCase.addComponent(new EmptySpace());
        showCase.addComponent(new EmptySpace());

        showCase.addComponent(previewLabel);
        showCase.addComponent(new EmptySpace());
        showCase.addComponent(previewImg);

        showCase.addComponent(new EmptySpace());
        showCase.addComponent(new EmptySpace());
        showCase.addComponent(new EmptySpace());

        showCase.addComponent(statsLabel);
        showCase.addComponent(new EmptySpace());
        showCase.addComponent(hpLabel);
        showCase.addComponent(hpValue);
        showCase.addComponent(vitLabel);
        showCase.addComponent(vitPoints);
        showCase.addComponent(strLabel);
        showCase.addComponent(strPoints);
        showCase.addComponent(dexLabel);
        showCase.addComponent(dexPoints);
        showCase.addComponent(intLabel);
        showCase.addComponent(intPoints);
        showCase.addComponent(lckLabel);
        showCase.addComponent(lckPoints);
        showCase.addComponent(stealthLabel);
        showCase.addComponent(stealthPoints);
        showCase.addComponent(defenceLabel);
        showCase.addComponent(defencePoints);

        // Menu
        Panel menu = new Panel();

        menu.addComponent(new Button("Save and back to main menu", new Runnable() {
            @Override
            public void run() {
                Saves.SaveCharacter(story.getCharacter());
                story.switchToView(Storyboard.MAINMENU);
            }
        }));

        root.addComponent(showCase.withBorder(Borders.doubleLine("Character")));
        root.addComponent(menu);

        rootWindow.setComponent(root);

        textGUI.addWindow(rootWindow);
    }

    @Override
    public void init(Storyboard story, WindowBasedTextGUI textGUI, String communication) {
        this.communication = communication;
        init(story, textGUI);
    }

}
