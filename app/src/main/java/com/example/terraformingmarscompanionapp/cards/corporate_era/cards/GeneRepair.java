package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class GeneRepair extends Card {
    public GeneRepair() {
        super(Type.GREEN);
        name = "Gene repair";
        price = 12;
        tags.add(Tag.SCIENCE);
        requirements.setMinScienceTags(3);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setMoneyProduction(2);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
