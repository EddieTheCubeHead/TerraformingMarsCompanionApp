package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MagneticFieldGenerators extends Card {
    public MagneticFieldGenerators(Game game) {
        super(Type.GREEN);
        name = "Magnetic field generators";
        price = 20;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(4);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-4);
        player.changePlantsProduction(2);
        player.changeTerraformingRating(3);
        super.onPlay(player);
    }
}
