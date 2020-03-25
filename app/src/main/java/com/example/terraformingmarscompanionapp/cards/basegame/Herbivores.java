package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Herbivores extends Card {
    public Herbivores(Game game) {
        name = "Herbivores";
        price = 12;
        tags.put("animal", 1);
        requirements.put("min_oxygen", 8);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addAnimalTag();
        player.addAction(this);
        //TODO poista toiselta pelaajalta yksi kasvintuotanto
        resource_amount++;
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player != player) {
            return;
        }
        resource_amount++;
    }

    @Override
    public boolean cardAction() {
        return false;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }
}
