package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class NuclearPower extends Card {
    public NuclearPower(Game game) {
        super(Type.GREEN, game);
        name = "Nuclear power";
        price = 10;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinMoneyProduction(-2);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(-2);
        production_box.setEnergyProduction(3);
        super.playWithMetadata(player, data);
    }
}
