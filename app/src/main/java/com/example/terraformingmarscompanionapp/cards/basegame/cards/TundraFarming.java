package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TundraFarming extends Card {
    public TundraFarming(Game game) {
        super(Type.GREEN, game);
        name = "Tundra farming";
        price = 16;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-6);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(1);
        player.changePlantsProduction(1);
        player.changeMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
