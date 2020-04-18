package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class DeepWellHeating extends Card {
    public DeepWellHeating(Game game) {
        super(Type.GREEN);
        name = "Deep well heating";
        price = 13;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(1);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
