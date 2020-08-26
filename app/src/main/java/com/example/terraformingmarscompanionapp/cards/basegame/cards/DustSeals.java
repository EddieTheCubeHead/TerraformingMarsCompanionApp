package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class DustSeals extends Card {
    public DustSeals() {
        super(Type.GREEN);
        name = "Dust seals";
        price = 2;
        requirements.setMaxOxygen(3);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
