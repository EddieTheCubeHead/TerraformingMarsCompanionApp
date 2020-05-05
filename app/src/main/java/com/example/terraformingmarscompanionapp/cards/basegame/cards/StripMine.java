package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StripMine extends Card {
    public StripMine(Game game) {
        super(Type.GREEN, game);
        name = "Strip mine";
        price = 25;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(2);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeEnergyProduction(-2);
        player.changeSteelProduction(2);
        player.changeTitaniumProduction(1);
        owner_game.raiseOxygen(player);
        owner_game.raiseOxygen(player);
        super.playWithMetadata(player, data);
    }
}
