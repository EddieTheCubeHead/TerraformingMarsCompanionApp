package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class PhobosSpaceHaven extends Card {
    public PhobosSpaceHaven(Game game) {
        super("green");
        name = "PhobosSpaceHaven";
        price = 25;
        tags.add("space");
        tags.add("city");
        victory_points = 3;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        owner_game.tile_handler.placePhobos(player);
        super.onPlay(player);
    }
}
