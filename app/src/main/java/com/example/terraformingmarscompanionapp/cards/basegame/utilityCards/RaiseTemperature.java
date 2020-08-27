package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.CardlikeOperation;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class RaiseTemperature extends CardlikeOperation {
    public RaiseTemperature() {
        super();
        name = "Raise temperature";
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.raiseTemperature(player);
        player.getResources().setHeat(player.getResources().getHeat() - 8);
        super.playWithMetadata(player, data);
    }
}
