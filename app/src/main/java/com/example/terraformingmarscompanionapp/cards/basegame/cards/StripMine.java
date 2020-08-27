package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class StripMine extends Card {
    public StripMine() {
        super(Type.GREEN);
        name = "Strip mine";
        price = 25;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setEnergyProduction(-2);
        production_box.setSteelProduction(2);
        production_box.setTitaniumProduction(1);
        game.raiseOxygen(player);
        game.raiseOxygen(player);
        super.playWithMetadata(player, data);
    }
}
