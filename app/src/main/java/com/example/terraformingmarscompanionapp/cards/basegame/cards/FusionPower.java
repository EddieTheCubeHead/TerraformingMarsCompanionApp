package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class FusionPower extends Card {
    public FusionPower() {
        super(Type.GREEN);
        name = "Fusion power";
        price = 14;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyTags(2);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(3);
        super.playWithMetadata(player, data);
    }
}
