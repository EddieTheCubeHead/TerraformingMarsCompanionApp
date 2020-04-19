package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class IndustrialMicrobes extends Card {
    public IndustrialMicrobes(Game game) {
        super(Type.GREEN);
        name = "Industrial microbes";
        price = 12;
        tags.add(Tag.MICROBE);
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeSteelProduction(1);
        player.changeEnergyProduction(1);
        return super.onPlay(player);
    }
}
