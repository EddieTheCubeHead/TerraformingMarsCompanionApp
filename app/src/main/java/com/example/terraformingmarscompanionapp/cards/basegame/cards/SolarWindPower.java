package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SolarWindPower extends Card {
    public SolarWindPower(Game game) {
        super(Type.GREEN);
        name = "Solar wind power";
        price = 11;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SPACE);
        tags.add(Tag.ENERGY);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(1);
        player.changeTitanium(2);
        return super.onPlay(player);
    }
}
