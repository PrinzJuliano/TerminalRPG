package de.pjog.prinzJuliano.TerminalRPG.gfx;

import java.util.HashMap;

public class CommonSprites {
	
	private HashMap<String, BasicImageRenderer> images;
	
	public CommonSprites(){
		images = new HashMap<String, BasicImageRenderer>();
		
		images.put("Warrior", SpriteLoader.loadSprite("Warrior.png", 8, 8));
		images.put("Archer", SpriteLoader.loadSprite("Archer.png", 8, 8));
		images.put("Mage", SpriteLoader.loadSprite("Mage.png", 8, 8));
		images.put("Rogue", SpriteLoader.loadSprite("Rogue.png", 8, 8));
	}
	
	public BasicImageRenderer getImage(String s, int expC, int expR)
	{
		if(images.containsKey(s))
			return images.get(s);
		else
			return new BasicImageRenderer(new MissingNO().getImage(expC, expR));
		
	}

}
