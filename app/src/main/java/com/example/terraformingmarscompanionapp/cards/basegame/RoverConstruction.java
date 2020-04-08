package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class RoverConstruction extends Card implements EffectCard {
    public RoverConstruction(Game game) {
        super("blue");
        name = "Rover construction";
        price = 8;
        tags.add("building");
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    public void cardEffect(Player player) {
        if (owner_player != null) {
            owner_player.changeMoney(2);
        }
    }
}
