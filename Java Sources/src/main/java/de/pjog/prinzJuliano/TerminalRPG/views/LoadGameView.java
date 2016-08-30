package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.*;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;
import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;
import de.pjog.prinzJuliano.TerminalRPG.util.Saves;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Load any save game you want.
 * @author PrinzJuliano
 *
 */
public class LoadGameView extends AbstractView{

	@Override
	public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
		rootWindow.setTitle("Load a Save");

        // Open up folder
        File folder = new File("Saves");

        Panel loader = new Panel();

        // Conditional content
        if(!folder.exists() || !folder.isDirectory()) {

            loader.addComponent(new Label("Sorry but there are no Save files!"));

        }else {

            // Filter the files (Code by Mohamed Mansour @ StackOverflow)
            String[] directories = folder.list(new FilenameFilter() {
                @Override
                public boolean accept(File current, String name) {
                    return new File(current, name).isDirectory();
                }
            });

            if (directories != null) {
                for(String s : directories)
                {
                    RPGCharacter r = Saves.loadCharacter(s);
                    if(r != null) {
                        Panel cha = new Panel();
                        cha.setLayoutManager(new GridLayout(2));
                        cha.addComponent(new Label("Class:"));
                        cha.addComponent(new Label(r.getFightingClass().name()));
                        cha.addComponent(new Label("Level:"));
                        cha.addComponent(new Label("" + r.getLevel()));

                        cha.addComponent(new Button("Load", new Runnable() {
                            @Override
                            public void run() {
                                //TODO add loading code
                            }
                        }));
                        cha.addComponent(new Button("Delete", new Runnable() {
                            @Override
                            public void run() {
                                //TODO add delete code
                            }
                        }));

                        loader.addComponent(cha.withBorder(Borders.singleLine(r.getName())));
                    }
                }
            }
            else
            {
                loader.addComponent(new Label("Sorry but there are no Save files!"));
            }

        }

        loader.addComponent(new EmptySpace());
        loader.addComponent(new Button("Back to main menu", new Runnable() {
            @Override
            public void run() {
                story.switchToView(Storyboard.MAINMENU);
            }
        }));
        rootWindow.setComponent(loader);

		textGUI.addWindow(rootWindow);
	}

}
