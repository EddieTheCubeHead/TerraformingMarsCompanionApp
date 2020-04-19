package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PowerGrid extends Card {
    public PowerGrid(Game game) {
        super(Type.GREEN);
        name = "Power grid";
        price = 18;
        tags.add(Tag.ENERGY);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(player.getEnergyTags());
        return super.onPlay(player);
    }
}
