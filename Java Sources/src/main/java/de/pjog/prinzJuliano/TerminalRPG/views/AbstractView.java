package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.TextGUI.Listener;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

import java.util.Arrays;

/**
 * Base for any basic view in this game.
 *
 * @author PrinzJuliano
 */
public abstract class AbstractView implements View {

    /**
     * A simple window to be used.
     */
    protected BasicWindow rootWindow;

    public AbstractView() {
        rootWindow = new BasicWindow();
        rootWindow.setHints(Arrays.asList(Window.Hint.CENTERED));
    }

    @Override
    public abstract void init(Storyboard story, WindowBasedTextGUI textGUI);

    @Override
    public void init(final Storyboard story, WindowBasedTextGUI textGUI, String communication) {
        init(story, textGUI);
    }

    @Override
    public Listener getListener(Storyboard story) {
        return null;
    }

    @Override
    public void onResize(TerminalSize newSize) {
        // Do Nothing
    }

    @Override
    public boolean overridesListener() {
        return false;
    }
}
