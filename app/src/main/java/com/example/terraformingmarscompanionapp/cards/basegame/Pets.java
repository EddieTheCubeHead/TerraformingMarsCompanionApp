package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Pets extends Card {
    public Pets(Game game) {
        name = "Pets";
        price = 10;
        tags.put("earth", 1);
        tags.put("animal", 1);
        resource_type = 6;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEarthTag();
        player.addAnimalTag();
        resource_amount++;
        player.addPassive(this);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
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
