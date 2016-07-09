package de.pjog.prinzJuliano.TerminalRPG.gfx;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.TextGUIGraphics;

public class BasicImageRenderer extends AbstractComponent<BasicImageRenderer>{

	private TextImage img;
	
	public BasicImageRenderer(TextImage img){
		this.img = img;
	}
	
	@Override
	protected ComponentRenderer<BasicImageRenderer> createDefaultRenderer() {
		return new ComponentRenderer<BasicImageRenderer>() {
            public TerminalSize getPreferredSize(BasicImageRenderer component) {
                return img.getSize();
            }

            public void drawComponent(TextGUIGraphics graphics, BasicImageRenderer component) {
                
                for(int y = 0; y < img.getSize().getRows(); y++)
                {
                	for(int x = 0; x < img.getSize().getColumns(); x++)
                	{
                		graphics.setCharacter(x, y, img.getCharacterAt(x, y));
                	}
                }
                
                
            }
        };
	}

}
