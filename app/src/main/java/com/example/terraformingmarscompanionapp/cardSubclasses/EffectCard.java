package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Player;

/**
 * Simple interface for {@link Card} childclasses that need to be played via {@link com.example.terraformingmarscompanionapp.game.UpdateManager}
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public interface EffectCard {

    /**
     * The effect of the card. Should always be called via {@link com.example.terraformingmarscompanionapp.game.UpdateManager}
     *
     * @param player {@link Player} that played the action that triggered the event.
     */
    void cardEffect(Player player);
}
