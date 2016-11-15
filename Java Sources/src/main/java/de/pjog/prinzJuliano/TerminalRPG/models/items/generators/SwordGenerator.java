package de.pjog.prinzJuliano.TerminalRPG.models.items.generators;

import java.util.ArrayList;

import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;
import de.pjog.prinzJuliano.TerminalRPG.models.items.Item;
import de.pjog.prinzJuliano.TerminalRPG.models.items.Rarity;
import de.pjog.prinzJuliano.TerminalRPG.models.items.Weapon;

public class SwordGenerator {
	
	public SwordGenerator(){
		
	}
	
	public static Weapon generateSword(int level){
		Weapon x = new Weapon(level, level, level, level, level, level) {
			
			@Override
			public boolean isSuitableFor(FightingClasses c) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ArrayList<FightingClasses> getSuitableClasses() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getSuffix() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getStackSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Rarity getRarity() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getQuantity() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getPrefix() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getLevel() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getID() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean equals(Item o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean addToQuantity(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		return x;
	}

}
