package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class LightningHarvest extends Card {
    public LightningHarvest() {
        super(Type.GREEN);
        name = "Lightning harvest";
        price = 8;
        tags.add(Tag.ENERGY);
        requirements.setMinScienceTags(3);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setMoneyProduction(1);
        production_box.setEnergyProduction(1);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
