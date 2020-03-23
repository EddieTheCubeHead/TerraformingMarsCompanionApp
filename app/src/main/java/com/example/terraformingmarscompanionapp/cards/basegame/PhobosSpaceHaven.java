package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class PhobosSpaceHaven extends Card {
    public PhobosSpaceHaven(Game game) {
        name = "PhobosSpaceHaven";
        price = 25;
        tags.put("space", 1);
        tags.put("city", 1);
        victory_points = 3;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeTitaniumProduction(1);
        owner_game.placeCity(player, 6);
        player.addSpaceTag();
        player.addCityTag();
        player.addGreen(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
