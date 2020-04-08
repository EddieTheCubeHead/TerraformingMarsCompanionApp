package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Pets extends ResourceCard implements EffectCard {
    public Pets(Game game) {
        super("blue");
        name = "Pets";
        price = 10;
        tags.add("earth");
        tags.add("animal");
        resource_type = 6;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        resource_amount++;
        super.onPlay(player);
    }

    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        resource_amount++;
    }
    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }

}
