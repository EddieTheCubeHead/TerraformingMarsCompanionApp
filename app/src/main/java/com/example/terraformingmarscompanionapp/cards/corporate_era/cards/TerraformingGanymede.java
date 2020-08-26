package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class TerraformingGanymede extends Card {
    public TerraformingGanymede() {
        super(Type.BLUE);
        name = "Terraforming ganymede";
        price = 33;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        super.playWithMetadata(player, data);
        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + player.getTags().getJovianTags() + 1);
        game.update_manager.onVpCardPlayed(player);
    }
}
