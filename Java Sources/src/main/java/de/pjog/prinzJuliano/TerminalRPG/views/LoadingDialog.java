package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.gui2.*;
import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class LoadingDialog extends AbstractView {
    private int nextView;
    private String communication;


    @Override
    public void init(final Storyboard story, WindowBasedTextGUI textGUI) {
        textGUI.addWindow(rootWindow);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        story.switchToView(nextView, communication);
                    }
                },
                10
        );
    }

    @Override
    public void init(Storyboard story, WindowBasedTextGUI textGUI, String communication) {
        this.communication = communication;
        init(story, textGUI);

    }

    /**
     * Sets the view to change to.
     *
     * @param nextView the view based on {@link Storyboard}'s views.
     */
    public void setNextView(int nextView) {
        this.nextView = nextView;
    }


}
