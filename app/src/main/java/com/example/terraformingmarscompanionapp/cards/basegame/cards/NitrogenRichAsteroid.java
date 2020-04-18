package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NitrogenRichAsteroid extends Card {
    public NitrogenRichAsteroid(Game game) {
        super(Type.RED);
        name = "Nitrogen-rich asteroid";
        price = 31;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
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
