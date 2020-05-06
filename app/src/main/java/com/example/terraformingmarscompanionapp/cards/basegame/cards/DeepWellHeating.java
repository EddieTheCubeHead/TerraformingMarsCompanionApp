package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class DeepWellHeating extends Card {
    public DeepWellHeating(Game game) {
        super(Type.GREEN, game);
        name = "Deep well heating";
        price = 13;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(1);
        owner_game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
