package de.pjog.prinzJuliano.TerminalRPG.models;

import org.json.JSONObject;

/**
 * The Character Sheet. All handling of typical RPG Elements will be done in here.
 *
 * @author PrinzJuliano
 */
public class RPGCharacter implements Cloneable {

    private String name; //The name
    private FightingClasses fightingClass; // Her / His class
    private Stats stats; // Her / His Stats

    private int level; //Her / His Level
    private int xp;

    /**
     * <p>
     * Convenience Initialization code.<br>
     * <table style="border:1px solid black">
     * <tr><th>Key</th><th>Value</th></tr>
     * <tr><td>name</td><td>Brian</td></tr>
     * <tr><td>fightingClass</td><td>{@linkplain FightingClasses}.WORRIOR</td></tr>
     * <tr><td>Level</td><td>1</td></tr>
     * <tr><td>Stats</td><td>All values to 5</td></tr>
     * </table>
     * </p>
     */
    public RPGCharacter() {
        name = "Brian";
        fightingClass = FightingClasses.WARRIOR;
        level = 1;
        xp = 0;
        stats = new Stats(5, 5, 5, 5, 5, 0, 0);
    }

    /**
     * Constructor for {@link RPGCharacter}
     *
     * @param name          Her / His name
     * @param fightingClass Her / His class to play with
     * @param level         Her / His Level
     * @param stats         Her / His fighting Stats.
     */
    public RPGCharacter(String name, FightingClasses fightingClass, int level, int xp, Stats stats) {
        this.name = name;
        this.fightingClass = fightingClass;
        this.level = level;
        this.stats = stats;
        this.xp = xp;
    }

    /**
     * Generic Getter
     *
     * @return the character's {@link RPGCharacter#name}
     */
    public String getName() {
        return name;
    }

    /**
     * Generic Setter
     *
     * @param name set the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Generic Getter
     *
     * @return the class
     */
    public FightingClasses getFightingClass() {
        return fightingClass;
    }

    /**
     * Generic Setter
     *
     * @param fightingClass the class
     */
    public void setFightingClass(FightingClasses fightingClass) {
        this.fightingClass = fightingClass;
    }

    /**
     * Generic Getter
     *
     * @return the value
     */
    public int getLevel() {
        return level;
    }

    /**
     * Generic Setter
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Generic getter
     *
     * @return the value
     */
    public int getXp() {
        return xp;
    }

    /**
     * Generic Setter
     *
     * @param xp the xp
     */
    public void setXp(int xp) {
        this.xp = xp;
        while (true) {
            int nextUp = (int) (getLevel() * Math.log10(getLevel()) * 100 + 50);
            if (this.xp >= nextUp) {
                setLevel(getLevel() + 1);
                this.xp -= nextUp;
            } else
                break;
        }

    }

    @Override
    public String toString() {
        return String.format("Character \"%s\"[LVL %d] of class [%s]", name, level, fightingClass);
    }

    /**
     * Create a JSONObject that contains everything!
     *
     * @return the object
     */
    public JSONObject toJSONObject() {
        JSONObject root = new JSONObject();

        root.put("name", name);
        root.put("level", level);
        root.put("xp", xp);
        root.put("class", fightingClass.name());

        root.put("stats", this.stats.toJSONObject());

        return root;
    }

    /**
     * Generic Getter
     *
     * @return the stats
     */
    public Stats getStats() {
        return stats;
    }

    /**
     * Generic Setter.
     *
     * @param stats the Stats.
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    /**
     * Clones the Character. Unfortunately not part of {@link Cloneable}
     *
     * @return The newly cloned character
     */
    public RPGCharacter clone() {
        RPGCharacter c = new RPGCharacter();
        c.setName(this.name);
        c.setLevel(this.level);
        c.setXp(this.xp);
        c.setStats(this.stats.clone());
        c.setFightingClass(this.fightingClass);

        return c;
    }

    public static RPGCharacter createNewFromJSONObject(JSONObject o) {
        RPGCharacter c = new RPGCharacter();
        if (c.fromJSONObject(o))
            return c;
        else
            return null;
    }

    public boolean fromJSONObject(JSONObject o) {
        if (o.has("name"))
            this.name = o.getString("name");
        else
            return false;

        if (o.has("level"))
            this.level = o.getInt("level");
        else
            return false;

        if (o.has("class"))
            this.fightingClass = FightingClasses.valueOf(o.getString("class"));
        else
            return false;

        if (o.has("xp"))
            this.xp = o.getInt("xp");
        else
            return false;

        if (o.has("stats"))
            this.stats = Stats.createNewFromJSONObject(o.getJSONObject("stats"));
        else
            return false;

        return true;
    }
}
