package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SolarWindPower extends Card {
    public SolarWindPower(Game game) {
        super(Type.GREEN, game);
        name = "Solar wind power";
        price = 11;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SPACE);
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(1);
        player.changeTitanium(2);
        super.playWithMetadata(player, data);
    }
}
