package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TerraformingGanymede extends Card {
    public TerraformingGanymede(Game game) {
        super(Type.BLUE, game);
        name = "Terraforming ganymede";
        price = 33;
        tags.add(Tag.JOVIAN);
        tags.add(Tag.SPACE);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.playWithMetadata(player, data);
        player.changeTerraformingRating(player.getJovianTags());
        owner_game.update_manager.onVpCardPlayed(player);
    }
}
