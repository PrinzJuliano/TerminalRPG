package de.pjog.prinzJuliano.TerminalRPG.models.items.generators;

import de.pjog.prinzJuliano.TerminalRPG.models.items.Weapon;

public class ItemGenerator {
	
	public static Weapon generate(int level){
		Weapon x = SwordGenerator.generateSword(level);
		return x;
	}

}
