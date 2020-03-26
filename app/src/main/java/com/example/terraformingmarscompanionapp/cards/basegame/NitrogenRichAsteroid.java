package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NitrogenRichAsteroid extends Card {
    public NitrogenRichAsteroid(Game game) {
        name = "Nitrogen-rich asteroid";
        price = 31;
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onSpaceEvent(player);
        owner_game.raiseTemperature(player);
        player.changeTerraformingRating(2);
        if (player.getPlantTags() < 3) {
            player.changePlantsProduction(1);
        } else {
            player.changePlantsProduction(4);
        }
        player.addRed(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
