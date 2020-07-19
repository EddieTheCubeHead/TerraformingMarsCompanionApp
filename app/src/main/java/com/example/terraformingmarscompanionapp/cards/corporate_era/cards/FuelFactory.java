package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

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
        production_box.setMoneyProduction(1);
        production_box.setEnergyProduction(-1);
        production_box.setTitaniumProduction(1);
        super.playWithMetadata(player, data);
    }
}
