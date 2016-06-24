package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.TextGUIGraphics;


public class Background extends AbstractComponent<Background> {

	@Override
	protected ComponentRenderer<Background> createDefaultRenderer() {
		return new ComponentRenderer<Background>() {
            public TerminalSize getPreferredSize(Background component) {
                return TerminalSize.ONE;
            }

            public void drawComponent(TextGUIGraphics graphics, Background component) {
            	graphics.setForegroundColor(TextColor.ANSI.BLACK);
                graphics.setBackgroundColor(new TextColor.RGB(42,42,42)); 
                graphics.fill(' ');
                
                graphics.setForegroundColor(TextColor.ANSI.CYAN);
                String text = "Copyright (c) 2016 PrinzJuliano";
                graphics.putString(graphics.getSize().getColumns() - text.length() - 1, graphics.getSize().getRows() - 1, text, SGR.BOLD);
                
            }
        };
	}
	
}
