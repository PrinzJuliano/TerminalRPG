package de.pjog.prinzJuliano.TerminalRPG.models;

import org.json.JSONObject;

/**
 * All the Stats any entity could have.
 *
 * @author PrinzJuliano
 */
public class Stats implements Cloneable {
    private int vitality;     //How much life points we got
    private int hp;
    private int strength;     //How hard we hit
    private int dexterity;    //How agile we are
    private int intelligence; //How smart we are
    private int luck;         //Superstition based stuff
    private int stealth;      //Determine if we start a fight or not
    private int defense;      //How much damage will be subtracted

    /**
     * Convenience Constructor for creation from Files
     */
    public Stats() {
        //Convenience
    }

    /**
     * Simple Constructor
     *
     * @param vitality     How much life points we got
     * @param strength     How hard we hit
     * @param dexterity    How agile we are
     * @param intelligence How smart we are
     * @param luck         Superstition based stuff
     * @param stealth      Determine if we start a fight or not
     * @param defense      How much damage will be subtracted
     */
    public Stats(int vitality, int strength, int dexterity, int intelligence, int luck, int stealth, int defense) {
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.luck = luck;
        this.stealth = stealth;
        this.defense = defense;

        setHp(getMaxHp());
    }

    /**
     * Simple Constructor
     *
     * @param hp           How much actual life points some one has
     * @param vitality     How much life points we got
     * @param strength     How hard we hit
     * @param dexterity    How agile we are
     * @param intelligence How smart we are
     * @param luck         Superstition based stuff
     * @param stealth      Determine if we start a fight or not
     * @param defense      How much damage will be subtracted
     */
    public Stats(int hp, int vitality, int strength, int dexterity, int intelligence, int luck, int stealth, int defense) {
        this.vitality = vitality;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.luck = luck;
        this.stealth = stealth;
        this.defense = defense;

        setHp(hp);
    }

    /**
     * Generic Getter
     *
     * @return its value
     */
    public int getHp() {
        return hp;
    }

    /**
     * Generic Setter
     *
     * @param hp the actual life some one has
     */
    public void setHp(int hp) {
        this.hp = Math.min(hp, getMaxHp());
    }

    /**
     * Get the Max HP based on vitality
     *
     * @return Calculated Max HP
     */
    public int getMaxHp() {
        return 100 + 10 * this.vitality;
    }

    /**
     * Generic Getter.
     *
     * @return its value
     */
    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    /**
     * Generic Getter.
     *
     * @return its value
     */
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Generic Getter.
     *
     * @return its value
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * Generic Setter.
     *
     * @param dexterity value
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * Generic Getter.
     *
     * @return its value
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Generic Setter.
     *
     * @param intelligence the value
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * Generic Getter.
     *
     * @return its value
     */
    public int getLuck() {
        return luck;
    }

    /**
     * Generic Setter.
     *
     * @param luck the value
     */
    public void setLuck(int luck) {
        this.luck = luck;
    }

    /**
     * Generic Getter.
     *
     * @return its value
     */
    public int getStealth() {
        return stealth;
    }

    /**
     * Generic Setter.
     *
     * @param stealth the value
     */
    public void setStealth(int stealth) {
        this.stealth = stealth;
    }

    /**
     * Generic Getter.
     *
     * @return its value
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Generic Setter.
     *
     * @param defense the value
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        String head = "%-12s%-12s%-12s%-12s%-12s%-12s%s";
        String body = "%-12d%-12d%-12d%-12d%-12d%-12d%d";

        return String.format(head + "\n" + body, "HP", "Max HP", "VIT", "STR", "DEX", "INT", "LCK", hp, getMaxHp(), vitality, strength, dexterity, intelligence, luck);
    }

    /**
     * Creates a new independent object with the same values as the called one.
     */
    public Stats clone() {
        return new Stats(hp, vitality, strength, dexterity, intelligence, luck, stealth, defense);
    }

}
