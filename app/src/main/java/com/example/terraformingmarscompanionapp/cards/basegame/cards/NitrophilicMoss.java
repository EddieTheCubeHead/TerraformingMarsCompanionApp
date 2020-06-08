package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class NitrophilicMoss extends Card {
    public NitrophilicMoss(Game game) {
        super(Type.GREEN, game);
        name = "Nitrophilic moss";
        price = 8;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(3);
        requirements.setMinPlants(2);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(-2);
        production_box.setPlantsProduction(2);
        super.playWithMetadata(player, data);
    }
}
