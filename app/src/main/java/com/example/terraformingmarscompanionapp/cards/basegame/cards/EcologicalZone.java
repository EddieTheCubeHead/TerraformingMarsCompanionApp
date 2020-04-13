package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EcologicalZone extends ResourceCard implements EffectCard {
    public EcologicalZone(Game game) {
        super("green");
        name = "Ecological zone";
        price = 12;
        tags.add("animal");
        tags.add("plant");
        requirements.setMinPersonalGreeneries(1);
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

    @Override
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
