package de.pjog.prinzJuliano.TerminalRPG.models;

public class Character implements Cloneable{
	
	private String name;
	private FightingClasses fightingClass;
	
	private int level;
	
	public Character(){
		name = "Brian";
		fightingClass = FightingClasses.WARRIOR;
		level = 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FightingClasses getFightingClass() {
		return fightingClass;
	}

	public void setFightingClass(FightingClasses fightingClass) {
		this.fightingClass = fightingClass;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public String toString(){
		return String.format("Character \"%s\"[%d] of class [%s]", name, level, fightingClass);
		
	}
	
	public Character clone(){
		Character c = new Character();
		c.setName(this.name);
		c.setLevel(this.level);
		c.setFightingClass(this.fightingClass);
		
		return c;
	}

}
