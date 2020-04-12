package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

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
