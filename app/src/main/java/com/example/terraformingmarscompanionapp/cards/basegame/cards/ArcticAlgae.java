package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ArcticAlgae extends Card implements EffectCard {
    public ArcticAlgae(Game game) {
        super("blue");
        name = "Arctic algae";
        price = 12;
        tags.add("plant");
        requirements.setMaxTemperature(-12);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(1);
        super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.changePlants(2);
    }
}
