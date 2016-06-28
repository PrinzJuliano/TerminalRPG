package de.pjog.prinzJuliano.TerminalRPG.views;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.TextGUI.Listener;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

import de.pjog.prinzJuliano.TerminalRPG.Storyboard;

public class EndCardView extends AbstractComponent<EndCardView> implements View {

	@Override
	public void init(Storyboard story, WindowBasedTextGUI textGUI) {
		textGUI.getBackgroundPane().setComponent(this);
		
	}

	protected ComponentRenderer<EndCardView> createDefaultRenderer() {
		return new ComponentRenderer<EndCardView>() {
			
			public TerminalSize getPreferredSize(EndCardView component)
			{
				return TerminalSize.ONE;
			}

			@Override
			public void drawComponent(TextGUIGraphics graphics, EndCardView component) {
				graphics.setForegroundColor(TextColor.ANSI.WHITE);
                graphics.setBackgroundColor(TextColor.ANSI.BLUE); //new TextColor.RGB(4, 50, 255)
                graphics.fill(' ');
                
                
                String[] text = {"This Program is about to close", "", "Thanks for playing my Game!", "Copyright (c) 2016 PrinzJuliano", "This software is liscensed under the terms and conditions", "of the Creative Commons: NonCommercial-ShareAlike 4.0 ", "International License.", "", "Read the LICENSE file for futher information", "", "PRESS ANY KEY TO CONTINUE . . ."};
                
                TextColor[] FG = {TextColor.ANSI.BLUE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, new TextColor.RGB(255, 128, 0), TextColor.ANSI.WHITE, TextColor.ANSI.GREEN};
                TextColor[] BG = {TextColor.ANSI.WHITE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE};
                
                int start = (int)Math.round(graphics.getSize().getRows()/2-text.length/2-1);
                System.out.println(start);
                
                for(int i = 0; i < text.length; i++){
                	String t = " " + text[i] + " ";
                	graphics.setForegroundColor(FG[i]);
                	graphics.setBackgroundColor(BG[i]);
                	int column = (int)Math.round(graphics.getSize().getColumns()/2-t.length()/2-1);
                	graphics.putString(column, start, t);
                	start++;
                }
				
			}
			
		};
	}

	@Override
	public Listener getListener(final Storyboard story) {
		return new TextGUI.Listener() {
			
			public boolean onUnhandledKeyStroke(TextGUI textGUI, KeyStroke keyStroke) {
				story.requestClose();
				return true;
			}
		};
	}
	
	@Override
	public boolean overridesListener() {
		return true;
	}

	@Override
	public void onResize(TerminalSize newSize) {
		//Background Components auto resize
	}

}
