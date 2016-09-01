package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

/**
 * A Dialog that exists the Game on pressing the ok button.
 *
 * @author PrinzJuliano
 */
public class CrashDialog extends AbstractView {

    String message;

    /**
     * Constructor
     *
     * @param message what message should be displayed
     */
    public CrashDialog(String message) {
        super();
        this.message = message;
    }

    /**
     * Convenience Constructor
     */
    public CrashDialog() {
        super();
    }

    @Override
    public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
        textGUI.getBackgroundPane().setComponent(new Background());
        rootWindow.setTitle("Error");

        Panel p = new Panel();
        p.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        Label caption = new Label("Something went wrong!");
        caption.setForegroundColor(TextColor.ANSI.YELLOW);

        p.addComponent(caption);

        Separator sep = new Separator(Direction.HORIZONTAL);
        p.addComponent(sep);

        Label error = new Label(message);
        p.addComponent(error);

        Button close = new Button("Close", new Runnable() {

            @Override
            public void run() {
                story.requestClose();

            }
        });
        p.addComponent(sep);
        p.addComponent(close);

        rootWindow.setComponent(p);

        textGUI.addWindow(rootWindow);

        onResize(textGUI.getScreen().getTerminalSize());
    }

    /**
     * Generic Getter.
     *
     * @return the Message to be displayed.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Generic Setter.
     *
     * @param message the message to be displayed
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
