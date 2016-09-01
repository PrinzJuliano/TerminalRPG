package de.pjog.prinzJuliano.TerminalRPG.models.items;

import de.pjog.prinzJuliano.TerminalRPG.models.RPGCharacter;

/**
 * @author PrinzJuliano
 */
public interface EffectItem extends Item{

    boolean hasEffect();
    void onEffect(RPGCharacter character);
    void onUnEffect(RPGCharacter character);
}
