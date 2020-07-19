package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class RadChemFactory extends Card {
    public RadChemFactory(Game game) {
        super(Type.GREEN, game);
        name = "Rad-chem factory";
        price = 8;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 2);
        super.playWithMetadata(player, data);
    }
}
