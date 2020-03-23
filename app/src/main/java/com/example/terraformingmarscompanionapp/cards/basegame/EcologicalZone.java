package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EcologicalZone extends Card {
    public EcologicalZone(Game game) {
        name = "Ecological zone";
        price = 12;
        tags.put("animal", 1);
        tags.put("plant", 1);
        requirements.put("min_forests_personal", 1);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_player = player;
        player.addPassive(this);
        player.addAnimalTag();
        player.addPlantTag();
        owner_game.placeTile(player, 8);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player == player) {
            resource_amount++;
        }
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
