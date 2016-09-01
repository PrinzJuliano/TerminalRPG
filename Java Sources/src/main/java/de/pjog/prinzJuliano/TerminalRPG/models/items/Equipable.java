package de.pjog.prinzJuliano.TerminalRPG.models.items;

import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;

/**
 * @author PrinzJuliano
 */
public interface Equipable extends Item{

    boolean isEquipable();
    void onEquip(RPGCharacter character);
    void onUnEquip(RPGCharacter character);
}
