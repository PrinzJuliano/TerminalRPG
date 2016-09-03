package de.pjog.prinzJuliano.TerminalRPG.models.items;

import java.util.ArrayList;

import de.pjog.prinzJuliano.TerminalRPG.models.FightingClasses;

/**
 * Represents a simple item
 * @author PrinzJuliano
 */
public interface Item{
    String getPrefix();
    String getName();
    String getSuffix();
    String getType();
    ArrayList<FightingClasses> getSuitableClasses();
    boolean isSuitableFor(FightingClasses c);
    long getID();
    Rarity getRarity();
    int getLevel();
    int getQuantity();
    boolean addToQuantity(int amount);
    boolean isStackable();
    int getStackSize();
    boolean equals(Item o);
}
