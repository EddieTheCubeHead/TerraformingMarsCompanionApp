package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EcologicalZone extends ResourceCard implements EffectCard {
    public EcologicalZone(Game game) {
        super("green");
        name = "Ecological zone";
        price = 12;
        tags.add("animal");
        tags.add("plant");
        requirements.put("min_personal_forests", 1);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeEcologicalZone(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }

        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player == player) {
            resource_amount++;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }
}
