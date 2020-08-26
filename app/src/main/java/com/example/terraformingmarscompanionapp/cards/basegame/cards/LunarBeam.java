package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class LunarBeam extends Card {
    public LunarBeam() {
        super(Type.GREEN);
        name = "Lunar beam";
        price = 13;
        tags.add(Tag.EARTH);
        tags.add(Tag.ENERGY);
        requirements.setMinMoneyProduction(-3);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(-2);
        production_box.setHeatProduction(2);
        production_box.setEnergyProduction(2);
        super.playWithMetadata(player, data);
    }
}
