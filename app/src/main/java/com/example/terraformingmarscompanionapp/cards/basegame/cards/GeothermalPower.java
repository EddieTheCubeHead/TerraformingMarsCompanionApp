package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GeothermalPower extends Card {
    public GeothermalPower(Game game) {
        super(Type.GREEN);
        name = "Geothermal power";
        price = 11;
        tags.add(Tag.BUILDING);
        tags.add(Tag.ENERGY);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
