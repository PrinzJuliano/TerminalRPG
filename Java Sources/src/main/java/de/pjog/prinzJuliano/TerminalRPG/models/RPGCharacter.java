package de.pjog.prinzJuliano.TerminalRPG.models;

public class RPGCharacter implements Cloneable{
	
	private String name;
	private FightingClasses fightingClass;
	private Stats stats;
	
	private int level;
	
	public RPGCharacter(){
		name = "Brian";
		fightingClass = FightingClasses.WARRIOR;
		level = 1;
		stats = new Stats(5, 5, 5, 5, 5, 0, 0);
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
	
	public Stats getStats(){
		return stats;
	}
	
	public void setStats(Stats stats)
	{
		this.stats = stats;
	}
	
	public RPGCharacter clone(){
		RPGCharacter c = new RPGCharacter();
		c.setName(this.name);
		c.setLevel(this.level);
		c.setFightingClass(this.fightingClass);
		
		return c;
	}

}
