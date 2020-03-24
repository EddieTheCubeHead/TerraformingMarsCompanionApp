package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Ants extends Card {
    public Ants(Game game) {
        name = "Ants";
        price = 9;
        tags.put("microbe", 1);
        requirements.put("min_oxygen", 4);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addMicrobeTag();
        player.addAction(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            //TODO poista resurssi toiselta kortilta
            resource_amount++;
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        super.onGameEnd();
    }
}
