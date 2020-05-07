package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class FuelFactory extends Card {
    public FuelFactory(Game game) {
        super(Type.GREEN, game);
        name = "Fuel factory";
        price = 6;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(1);
        player.changeEnergyProduction(-1);
        player.changeTitaniumProduction(1);
    }
}
