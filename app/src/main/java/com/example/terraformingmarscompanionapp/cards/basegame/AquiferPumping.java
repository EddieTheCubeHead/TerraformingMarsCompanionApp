package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class AquiferPumping extends Card {
    public AquiferPumping(Game game) {
        name = "Aquifer pumping";
        price = 18;
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
        if (action_used) {
            return false;
        } else if (owner_player != null) {
            //TODO implementoi tähän vie 8 rahana tai teräksenä
            owner_game.placeOcean(owner_player, false);
            action_used = true;
            return true;
        }
        return false;
    }
}
