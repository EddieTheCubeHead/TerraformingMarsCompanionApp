package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Shuttles extends Card {
    public Shuttles(Game game) {
        super(Type.BLUE);
        name = "Shuttles";
        price = 10;
        tags.add(Tag.SPACE);
        requirements.setMinOxygen(5);
        requirements.setMinEnergyProduction(1);
        victory_points = 0;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeSpaceTagDiscount(2);
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
