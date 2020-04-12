package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class OptimalAerobraking extends Card implements EffectCard {
    public OptimalAerobraking(Game game) {
        super("blue");
        name = "Optimal aerobraking";
        price = 7;
        tags.add("space");
        owner_game = game;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player != player){
            return;
        }
        owner_player.changeMoney(3);
        owner_player.changeHeat(3);
    }
}
