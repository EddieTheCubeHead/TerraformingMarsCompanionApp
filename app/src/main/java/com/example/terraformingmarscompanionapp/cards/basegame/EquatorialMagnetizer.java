package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EquatorialMagnetizer extends Card {
    public EquatorialMagnetizer(Game game) {
        name = "Equatorial magnetizer";
        price = 11;
        tags.put("building", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (action_used | owner_player.getEnergyProduction() < 1) {
            return false;
        } else {
            owner_player.changeEnergyProduction(-1);
            owner_player.changeTerraformingRating(1);
            action_used = true;
            return true;
        }
    }
}
