package de.pjog.prinzJuliano.TerminalRPG.models;

public class Stats implements Cloneable{
	private int vitality;     //How much life points we got
	private int strength;     //How hard we hit
	private int dexterity;    //How agile we are
	private int intelligence; //How smart we are
	private int luck;         //Superstition based stuff
	private int stealth;      //Determine if we start a fight or not
	private int defense;      //How much damage will be subtracted
	
	/**
	 * Convenience COnstructor for creation from Files
	 */
	public Stats(){
		//Convenience
	}
	
	/**
	 * Simple Constructor
	 * @param vitality
	 * @param strength
	 * @param dexterity
	 * @param intelligence
	 * @param luck
	 * @param stealth
	 * @param defense
	 */
	public Stats(int vitality, int strength, int dexterity, int intelligence, int luck, int stealth, int defense)
	{
		this.vitality = vitality;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.luck = luck;
		this.stealth = stealth;
		this.defense = defense;
	}

	public int getVitality() {
		return vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		this.stealth = stealth;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public String toString(){
		String head = "%-12s%-12s%-12s%-12s%s";
		String body = "%-12d%-12d%-12d%-12d%d";
		
		return String.format(head+"\n"+body, "VIT", "STR", "DEX", "INT", "LCK", vitality, strength, dexterity, intelligence, luck);
	}
	
	public Stats clone(){
		return new Stats(vitality, strength, dexterity, intelligence, luck, stealth, defense);
	}
	
}
