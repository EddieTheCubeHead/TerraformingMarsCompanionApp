package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class AerobrakedAmmoniaAsteroid extends Card {
    public AerobrakedAmmoniaAsteroid(Game game) {
        super("red");
        name = "Aerobraked ammonia asteroid";
        price = 26;
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onSpaceEvent(player);
        player.changeHeatProduction(3);
        player.changePlantsProduction(1);
        //TODO lisää 2 mikrobia toiselle kortille
        super.onPlay(player);
    }
}
