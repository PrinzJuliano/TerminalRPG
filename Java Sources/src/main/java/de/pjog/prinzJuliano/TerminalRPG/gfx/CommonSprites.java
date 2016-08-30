package de.pjog.prinzJuliano.TerminalRPG.gfx;

import java.util.HashMap;

public class CommonSprites {
	
	private HashMap<String, BasicImageRenderer> images;
	
	public CommonSprites(){
		images = new HashMap<>();

		images.put("WARRIOR", SpriteLoader.loadSprite("Warrior.png", 8, 8));
		images.put("ARCHER", SpriteLoader.loadSprite("Archer.png", 8, 8));
		images.put("MAGE", SpriteLoader.loadSprite("Mage.png", 8, 8));
		images.put("ROGUE", SpriteLoader.loadSprite("Rogue.png", 8, 8));
	}
	
	public BasicImageRenderer getImage(String s, int expC, int expR)
	{
		if(images.containsKey(s))
			return images.get(s);
		else
			return new BasicImageRenderer(new MissingNO().getImage(expC, expR));
		
	}

}
