package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NitrogenRichAsteroid extends Card {
    public NitrogenRichAsteroid(Game game) {
        super("red");
        name = "Nitrogen-rich asteroid";
        price = 31;
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onSpaceEvent(player);
        owner_game.raiseTemperature(player);
        player.changeTerraformingRating(2);
        if (player.getPlantTags() < 3) {
            player.changePlantsProduction(1);
        } else {
            player.changePlantsProduction(4);
        }
        super.onPlay(player);
    }
}
