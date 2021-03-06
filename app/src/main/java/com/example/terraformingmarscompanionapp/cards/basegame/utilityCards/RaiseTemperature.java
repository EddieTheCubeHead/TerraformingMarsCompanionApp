package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.game.cardClasses.CardlikeOperation;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class RaiseTemperature extends CardlikeOperation {
    public RaiseTemperature(Game game) {
        super(game);
        name = "Raise temperature";
        requirements.setMinHeat(8);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.raiseTemperature(player);
        player.getResources().setHeat(player.getResources().getHeat() - 8);
        super.playWithMetadata(player, data);
    }
}
