package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class QuantumExtractor extends Card {
    public QuantumExtractor(Game game) {
        super(Type.BLUE, game);
        name = "Quantum extractor";
        price = 13;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.ENERGY);
        requirements.setMinScienceTags(4);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(4);
        player.changeSpaceTagDiscount(2);
        super.playWithMetadata(player, data);
    }
}

