package de.pjog.prinzJuliano.TerminalRPG.models.items;

import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;
import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;

/**
 * @author PrinzJuliano
 */
public abstract class AbstractItem implements Item {

    public abstract String getPrefix();
    public abstract String getName();
    public abstract String getSuffix();
    public abstract String getType();
    public abstract long getID();

    public Rarity getRarity(){
        return Rarity.COMMON;
    }
    public int getLevel(){
        return 1;
    }

    public boolean isEquipable(){
        return false;
    }

    public FightingClasses[] getSuitableClasses(){
        return FightingClasses.values(); // All fighting classes are accepted
    }

    public boolean hasEffect(){
        return false;
    }

    public void onEffect(RPGCharacter character) { }
    public void onUnEffect(RPGCharacter character) { }
    public void onEquip(RPGCharacter character) { }
    public void onUnEquip(RPGCharacter character) { }
}
