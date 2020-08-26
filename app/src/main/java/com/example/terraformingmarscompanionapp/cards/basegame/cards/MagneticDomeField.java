package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MagneticDomeField extends Card {
    public MagneticDomeField() {
        super(Type.GREEN);
        name = "Magnetic dome field";
        price = 5;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(2);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-2);
        production_box.setPlantsProduction(1);
        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 1);
        super.playWithMetadata(player, data);
    }
}
