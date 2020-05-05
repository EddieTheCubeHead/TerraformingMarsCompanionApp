package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class LightningHarvest extends Card {
    public LightningHarvest(Game game) {
        super(Type.GREEN, game);
        name = "Lightning harvest";
        price = 8;
        tags.add(Tag.ENERGY);
        requirements.setMinScienceTags(3);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(1);
        player.changeEnergyProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
