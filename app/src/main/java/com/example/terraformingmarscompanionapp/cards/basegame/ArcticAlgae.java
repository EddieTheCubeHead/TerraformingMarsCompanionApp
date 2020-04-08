package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ArcticAlgae extends Card implements EffectCard {
    public ArcticAlgae(Game game) {
        super("blue");
        name = "Arctic algae";
        price = 12;
        tags.add("plant");
        requirements.put("max_temperature", -12);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(1);
        super.onPlay(player);
    }

    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.changePlants(2);
    }
}
