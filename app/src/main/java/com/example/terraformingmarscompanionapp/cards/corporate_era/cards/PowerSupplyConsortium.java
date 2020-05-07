package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PowerSupplyConsortium extends Card {
    public PowerSupplyConsortium(Game game) {
        super(Type.GREEN, game);
        name = "Power supply consortium";
        price = 5;
        tags.add(Tag.ENERGY);
        requirements.setMinEnergyTags(2);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO vöhennä jotain energy productionia 1
        player.changeEnergyProduction(1);
        super.playWithMetadata(player, data);
    }
}
