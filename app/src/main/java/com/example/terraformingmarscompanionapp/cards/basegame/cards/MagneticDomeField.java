package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MagneticDomeField extends Card {
    public MagneticDomeField(Game game) {
        super(Type.GREEN);
        name = "Magnetic dome field";
        price = 5;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(2);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(-2);
        player.changePlantsProduction(1);
        player.changeTerraformingRating(1);
        return super.onPlay(player);
    }
}
