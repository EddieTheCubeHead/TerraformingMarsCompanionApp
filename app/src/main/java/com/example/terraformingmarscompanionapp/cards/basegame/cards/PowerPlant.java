package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PowerPlant extends Card {
    public PowerPlant(Game game) {
        super(Type.GREEN);
        name = "Power plant";
        price = 4;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(1);
        return super.onPlay(player);
    }
}
