package de.pjog.prinzJuliano.TerminalRPG.gfx;

import java.util.HashMap;

public class CommonSprites {
	
	private HashMap<String, BasicImageRenderer> images;
	
	public CommonSprites(){
		images = new HashMap<String, BasicImageRenderer>();
		
		images.put("Warrior", SpriteLoader.loadSprite("/Graphics/Warrior.json", 8, 8));
	}
	
	public BasicImageRenderer getImage(String s, int expC, int expR)
	{
		if(images.containsKey(s))
			return images.get(s);
		else
			return new BasicImageRenderer(new MissingNO().getImage(expC, expR));
		
	}

}
