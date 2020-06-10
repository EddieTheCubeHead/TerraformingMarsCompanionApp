package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class NitrogenRichAsteroid extends Card {
    public NitrogenRichAsteroid(Game game) {
        super(Type.RED, game);
        name = "Nitrogen-rich asteroid";
        price = 31;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onSpaceEvent(player);
        owner_game.raiseTemperature(player);

        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 2);
        if (player.getTags().getPlantTags() < 3) {
            player.getResources().setPlantsProduction(player.getResources().getPlantsProduction() + 1);
        } else {
            player.getResources().setPlantsProduction(player.getResources().getPlantsProduction() + 4);
        }
        super.playWithMetadata(player, data);
    }
}
