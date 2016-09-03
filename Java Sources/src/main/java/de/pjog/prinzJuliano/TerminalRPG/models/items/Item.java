package de.pjog.prinzJuliano.TerminalRPG.models.items;

import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;

/**
 * Represents a simple item
 * @author PrinzJuliano
 */
public interface Item {
    String getPrefix();
    String getName();
    String getSuffix();
    String getType();
    FightingClasses[] getSuitableClasses();
    long getID();
    Rarity getRarity();
    int getLevel();
}
