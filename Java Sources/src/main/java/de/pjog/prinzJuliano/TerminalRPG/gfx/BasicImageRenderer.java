package de.pjog.prinzJuliano.TerminalRPG.gfx;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.TextGUIGraphics;

public class BasicImageRenderer extends AbstractComponent<BasicImageRenderer>{

	private TextCharacter[][] img;
	
	public BasicImageRenderer(TextCharacter[][] img){
		this.img = img;
	}
	
	@Override
	protected ComponentRenderer<BasicImageRenderer> createDefaultRenderer() {
		return new ComponentRenderer<BasicImageRenderer>() {
            public TerminalSize getPreferredSize(BasicImageRenderer component) {
                return new TerminalSize(img[0].length, img.length);
            }

            public void drawComponent(TextGUIGraphics graphics, BasicImageRenderer component) {
                
                for(int y = 0; y < img.length; y++)
                {
                	for(int x = 0; x < img[0].length; x++)
                	{
                		if(img[y][x] != null)
                			graphics.setCharacter(x, y, img[y][x]);
                		else{
                			graphics.setCharacter(x, y, new TextCharacter('x'));
                		}
                	}
                }
                
                
            }
        };
	}

}
