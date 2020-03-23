package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class LakeMarineris extends Card {
    public LakeMarineris(Game game) {
        name = "Lake marineris";
        price = 18;
        requirements.put("min_temperature", 0);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addNullTag();
        player.addGreen(this);
        owner_game.placeOcean(player);
        //TODO selvitä placeOcean ajoitus
        owner_game.placeOcean(player);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
