package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ColonizerTrainingCamp extends Card {
    public ColonizerTrainingCamp(Game game) {
        name = "Colonizer training camp";
        price = 8;
        tags.put("jovian", 1);
        tags.put("building", 1);
        requirements.put("max_oxygen", 5);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addJovianTag();
        player.addBuildingTag();
        player.addGreen(this);
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
