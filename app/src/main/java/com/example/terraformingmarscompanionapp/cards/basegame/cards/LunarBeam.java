package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class LunarBeam extends Card {
    public LunarBeam(Game game) {
        super(Type.GREEN, game);
        name = "Lunar beam";
        price = 13;
        tags.add(Tag.EARTH);
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(-2);
        production_box.setHeatProduction(2);
        production_box.setEnergyProduction(2);
        super.playWithMetadata(player, data);
    }
}
