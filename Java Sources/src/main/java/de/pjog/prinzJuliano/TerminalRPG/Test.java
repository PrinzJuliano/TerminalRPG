package de.pjog.prinzJuliano.TerminalRPG;


import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.AbstractComponent;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComponentRenderer;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Panels;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.TextGUI;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Test {
	
	private static TextGUI.Listener listener;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Terminal term = new DefaultTerminalFactory().createTerminal();
		Screen screen = new TerminalScreen(term);
		
		screen.startScreen();
		screen.clear();
		
		MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
		textGUI.setBlockingIO(false);
        textGUI.setEOFWhenNoWindows(true);
        
        //textGUI.setTheme(LanternaThemes.getRegisteredTheme("default"));
        
        try {
        	init(textGUI);
        	while(!textGUI.getWindows().isEmpty()) {
                textGUI.processInput();
                
                if(textGUI.isPendingUpdate()) {
                	textGUI.updateScreen();
                }
            }
        	
        	textGUI.removeListener(listener);
        	textGUI.getBackgroundPane().setComponent(new CloseDialog());
        	textGUI.updateScreen();
        	screen.readInput();
        	
        }
        catch(EOFException e)
        {
        	e.printStackTrace();
        }
        finally {
            screen.stopScreen();
        }
	}
	
	private static void init(final WindowBasedTextGUI textGUI)
	{
		textGUI.getBackgroundPane().setComponent(new BackgroundComponent());
		final BasicWindow window1 = new BasicWindow("Choose a file to be opened");
		
		
		Panel mainPanel = new Panel();
		mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL).setSpacing(1));
		
		Panel gridPanel = new Panel();
		GridLayout gridLayout = new GridLayout(2);
		gridLayout.setTopMarginSize(1);
        gridLayout.setVerticalSpacing(1);
        gridLayout.setHorizontalSpacing(1);
        gridPanel.setLayoutManager(gridLayout);
        
        Panel left = new Panel();
        left.setLayoutManager(new LinearLayout(Direction.HORIZONTAL).setSpacing(1));
		
		left.addComponent(new Label("Any"));
		left.addComponent(new Label("given"));
		left.addComponent(new Label("file"));
		left.addComponent(new Label("can"));
		left.addComponent(new Label("be"));
		left.addComponent(new Label("opened!"));
		
		Panel right = new Panel();
		right.setLayoutManager(new LinearLayout(Direction.VERTICAL).setSpacing(1));
		
		right.addComponent(new Label("Keep"));
		right.addComponent(new Label("that"));
		right.addComponent(new Label("in"));
		right.addComponent(new Label("mind"));
		right.addComponent(new Label("bro"));
		right.addComponent(new Label("!"));
		
		gridPanel.addComponent(left);
		gridPanel.addComponent(right);
		
		mainPanel.addComponent(gridPanel.withBorder(Borders.singleLine()));
		
		Panel closePanel = new Panel();
		closePanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL).setSpacing(1));
		
		closePanel.addComponent(new Button("Open", new Runnable(){

			public void run() {
				
				File input = new FileDialogBuilder()
                        .setTitle("Open File")
                        .setDescription("Choose a file")
                        .setActionLabel("Open")
                        .build()
                        .showDialog(textGUI);
            	System.out.println(input);
				
            	if(input != null && input.exists()){
            	
            		MyDialog dialog = new MyDialog(input);
            		dialog.setTheme(LanternaThemes.getRegisteredTheme("bigsnake"));
            		textGUI.addWindow(dialog);
            	}
			}
			
		}));
		
		closePanel.addComponent(new Button("Close", new Runnable(){

			public void run() {
				window1.close();
			}
		}));
		
		mainPanel.addComponent(closePanel);
		
		window1.setComponent(mainPanel);
		
		textGUI.addWindow(window1);
		
		listener = new TextGUI.Listener() {
			
			public boolean onUnhandledKeyStroke(TextGUI textGUI, KeyStroke keyStroke) {
				if(keyStroke.isAltDown() && keyStroke.getKeyType() == KeyType.ArrowDown)
				{
					Window w = (Window)textGUI.getFocusedInteractable().getBasePane();
					w.setPosition(w.getPosition().withRelativeRow(1));
				}
				else if(keyStroke.isAltDown() && keyStroke.getKeyType() == KeyType.ArrowUp){
					Window w = (Window)textGUI.getFocusedInteractable().getBasePane();
					w.setPosition(w.getPosition().withRelativeRow(-1));
				}
				else if(keyStroke.isAltDown() && keyStroke.getKeyType() == KeyType.ArrowLeft){
					Window w = (Window)textGUI.getFocusedInteractable().getBasePane();
					w.setPosition(w.getPosition().withRelativeColumn(-1));
				}
				else if(keyStroke.isAltDown() && keyStroke.getKeyType() == KeyType.ArrowRight){
					Window w = (Window)textGUI.getFocusedInteractable().getBasePane();
					w.setPosition(w.getPosition().withRelativeColumn(1));
				}
				else if(keyStroke.isCtrlDown() && keyStroke.getKeyType() == KeyType.Tab) {
                    ((WindowBasedTextGUI)textGUI).cycleActiveWindow(false);
                }
                else if(keyStroke.isCtrlDown() && keyStroke.getKeyType() == KeyType.ReverseTab) {
                    ((WindowBasedTextGUI)textGUI).cycleActiveWindow(true);
                } 
				else {
					return false;
				}
				return true;
			}
		};
		
		textGUI.addListener(listener);
		
	}
	
	private static class BackgroundComponent extends AbstractComponent<BackgroundComponent> {

		@Override
		protected ComponentRenderer<BackgroundComponent> createDefaultRenderer() {
			return new ComponentRenderer<BackgroundComponent>() {
                public TerminalSize getPreferredSize(BackgroundComponent component) {
                    return TerminalSize.ONE;
                }

                public void drawComponent(TextGUIGraphics graphics, BackgroundComponent component) {
                	graphics.setForegroundColor(TextColor.ANSI.BLACK);
                    graphics.setBackgroundColor(new TextColor.RGB(42,42,42)); 
                    graphics.fill(' ');
                    
                    graphics.setForegroundColor(TextColor.ANSI.GREEN);
                    
                    String text = "Use Alt + Arrow keys to move. Use Ctrl + Tab to cycle.";
                    graphics.putString(graphics.getSize().getColumns() - text.length() - 1, graphics.getSize().getRows() - 1, text, SGR.BOLD);
                    
                    graphics.setForegroundColor(TextColor.ANSI.CYAN);
                    text = "Copyright (c) 2016 PrinzJuliano";
                    graphics.putString(graphics.getSize().getColumns() - text.length() - 1, graphics.getSize().getRows() - 2, text, SGR.BOLD);
                    
                }
            };
		}
		
	}
	
	private static class MyDialog extends BasicWindow {
		
		
		public MyDialog(File s){
			super("File: " + s.toString());
			
			Panel contentPane = new Panel();
			
			contentPane.setLayoutManager(new LinearLayout(Direction.VERTICAL).setSpacing(1));
            
            String content = "", line = "";
            
            try {
				BufferedReader f = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
				
				while((line = f.readLine()) != null){
					content += line + "\n";
				}
				f.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            contentPane.addComponent(new TextBox(content).setReadOnly(true));
			
			Button okButton = new Button("Ok", new Runnable() {
                public void run() {
                	
                    close();
                }
            });
			
			Button cancelButton = new Button("Cancel", new Runnable() {
                public void run() {
                    close();
                }
            });

            contentPane.addComponent(
                    Panels.horizontal(okButton, cancelButton));
            setComponent(contentPane);
		}
	}
	
	private static class CloseDialog extends AbstractComponent<CloseDialog> {
		
		@Override
		protected ComponentRenderer<CloseDialog> createDefaultRenderer() {
			return new ComponentRenderer<CloseDialog>() {
                public TerminalSize getPreferredSize(CloseDialog component) {
                    return TerminalSize.ONE;
                }

                public void drawComponent(TextGUIGraphics graphics, CloseDialog component) {
                    graphics.setForegroundColor(TextColor.ANSI.WHITE);
                    graphics.setBackgroundColor(TextColor.ANSI.BLUE); //new TextColor.RGB(4, 50, 255)
                    graphics.fill(' ');
                    
                    
                    String[] text = {"This Program is about to close", "", "Thanks for using this digital software.", "Copyright (c) 2016 PrinzJuliano", "This software is licensed under the terms and conditions", "of the MIT Liscense.", "", "PRESS ANY KEY TO CONTINUE . . ."};
                    
                    TextColor[] FG = {TextColor.ANSI.BLUE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.WHITE, TextColor.ANSI.GREEN};
                    TextColor[] BG = {TextColor.ANSI.WHITE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE, TextColor.ANSI.BLUE};
                    
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
	}

}
