package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Player;

/**
 * Simple interface for cards that need to be played via update manager
 */
public interface EffectCard {
    void cardEffect(Player player);
}
