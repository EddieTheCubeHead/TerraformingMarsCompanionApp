package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MagneticFieldGenerators extends Card {
    public MagneticFieldGenerators() {
        super(Type.GREEN);
        name = "Magnetic field generators";
        price = 20;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(4);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-4);
        production_box.setPlantsProduction(2);
        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 3);
        super.playWithMetadata(player, data);
    }
}
