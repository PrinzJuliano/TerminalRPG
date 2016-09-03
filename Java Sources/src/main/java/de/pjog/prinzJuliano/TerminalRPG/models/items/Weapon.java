package de.pjog.prinzJuliano.TerminalRPG.models.items;

import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;

public abstract class Weapon implements Equipable{
	
	private int strength;     //How hard we hit
    private int dexterity;    //How agile we are
    private int intelligence; //How smart we are
    private int luck;         //Superstition based stuff
    private int stealth;      //Determine if we start a fight or not
    private int defense;      //How much damage will be subtracted
	
	public Weapon(int strength, int dexterity, int intelligence, int luck, int stealth, int defense){
		this.defense = defense;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.luck = luck;
		this.stealth = stealth;
		this.strength = strength;
	}
	
	@Override
	public boolean isStackable() {
		return false;
	}
	
	@Override
	public boolean isEquipable() {
		return true;
	}
	
	@Override
	public void onEquip(RPGCharacter character) {
		character.getStats().setDefense(character.getStats().getDefense() + defense);
		character.getStats().setDexterity(character.getStats().getDexterity() + dexterity);
		character.getStats().setIntelligence(character.getStats().getIntelligence() + intelligence);
		character.getStats().setLuck(character.getStats().getLuck() + luck);
		character.getStats().setStealth(character.getStats().getStealth() + stealth);
		character.getStats().setStrength(character.getStats().getStrength() + strength);
	}
	
	@Override
	public void onUnEquip(RPGCharacter character) {
		character.getStats().setDefense(character.getStats().getDefense() - defense);
		character.getStats().setDexterity(character.getStats().getDexterity() - dexterity);
		character.getStats().setIntelligence(character.getStats().getIntelligence() - intelligence);
		character.getStats().setLuck(character.getStats().getLuck() - luck);
		character.getStats().setStealth(character.getStats().getStealth() - stealth);
		character.getStats().setStrength(character.getStats().getStrength() - strength);
	}

}
