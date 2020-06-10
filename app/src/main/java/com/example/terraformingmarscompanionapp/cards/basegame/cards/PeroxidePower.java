package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class PeroxidePower extends Card {
    public PeroxidePower(Game game) {
        super(Type.GREEN, game);
        name = "Peroxide power";
        price = 7;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinMoneyProduction(-1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(-1);
        production_box.setEnergyProduction(2);
        super.playWithMetadata(player, data);
    }
}
