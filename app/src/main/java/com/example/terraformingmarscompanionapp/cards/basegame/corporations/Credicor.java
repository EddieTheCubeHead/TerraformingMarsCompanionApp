package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Credicor extends Card implements EffectCard {
    public Credicor(Game game) {
        super("corporation");
        name = "Credicor";
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoney(57);
        super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null && owner_player == player) {
            player.changeMoney(4);
        }
    }
}
