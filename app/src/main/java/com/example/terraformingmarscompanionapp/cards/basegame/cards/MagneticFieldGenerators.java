package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MagneticFieldGenerators extends Card {
    public MagneticFieldGenerators(Game game) {
        super(Type.GREEN, game);
        name = "Magnetic field generators";
        price = 20;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(4);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-4);
        production_box.setPlantsProduction(2);
        player.changeTerraformingRating(3);
        super.playWithMetadata(player, data);
    }
}
