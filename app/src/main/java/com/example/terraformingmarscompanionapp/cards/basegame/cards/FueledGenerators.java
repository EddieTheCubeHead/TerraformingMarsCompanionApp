package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class FueledGenerators extends Card {
    public FueledGenerators(Game game) {
        super(Type.GREEN);
        name = "Fueled generators";
        price = 1;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoneyProduction(-1);
        player.changeEnergyProduction(1);
        return super.onPlay(player);
    }
}
