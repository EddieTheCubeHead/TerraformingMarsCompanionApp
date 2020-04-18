package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PeroxidePower extends Card {
    public PeroxidePower(Game game) {
        super(Type.GREEN);
        name = "Peroxide power";
        price = 7;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-1);
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
