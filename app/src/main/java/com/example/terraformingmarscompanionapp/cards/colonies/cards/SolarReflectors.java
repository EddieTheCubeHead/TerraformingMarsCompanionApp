package com.example.terraformingmarscompanionapp.cards.colonies.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class SolarReflectors extends Card {
    public SolarReflectors() {
        super(Type.GREEN);
        name = "Solar reflectors";
        price = 23;
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(5);
        super.playWithMetadata(player, data);
    }
}
