package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.TextGUIGraphics;

import de.pjog.prinzJuliano.TerminalRPG.Main;


public class Background extends AbstractComponent<Background> {

	private TextColor color;
	
	public Background()
	{
		this(42, 42, 42);
	}
	
	public Background(int r, int g, int b)
	{
		this(new TextColor.RGB(r, g, b));
	}
	
	public Background(TextColor color)
	{
		this.color = color;
	}
	
	@Override
	protected ComponentRenderer<Background> createDefaultRenderer() {
		return new ComponentRenderer<Background>() {
            public TerminalSize getPreferredSize(Background component) {
                return TerminalSize.ONE;
            }

            public void drawComponent(TextGUIGraphics graphics, Background component) {
            	graphics.setForegroundColor(TextColor.ANSI.WHITE);
                graphics.setBackgroundColor(color); 
                graphics.fill(' ');
                
                if(Main.DEBUG)
                {
                	graphics.putString(1, 1, String.format("Size: %dx%d", graphics.getSize().getColumns(), graphics.getSize().getRows()));
                }
                
                graphics.setForegroundColor(TextColor.ANSI.CYAN);
                String text = "Copyright (c) 2016 PrinzJuliano";
                graphics.putString(graphics.getSize().getColumns() - text.length() - 1, graphics.getSize().getRows() - 1, text, SGR.BOLD);
                
            }
        };
	}
	
}
