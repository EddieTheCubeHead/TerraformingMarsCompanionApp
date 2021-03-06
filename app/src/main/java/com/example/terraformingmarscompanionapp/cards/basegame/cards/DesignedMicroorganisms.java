package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class DesignedMicroorganisms extends Card {
    public DesignedMicroorganisms(Game game) {
        super(Type.GREEN, game);
        name = "Designed microorganisms";
        price = 16;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        requirements.setMaxTemperature(-14);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(2);
        super.playWithMetadata(player, data);
    }
}
