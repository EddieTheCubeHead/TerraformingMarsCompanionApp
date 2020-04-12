package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class InterplanetaryCinematics extends Card implements EffectCard {
    public InterplanetaryCinematics(Game game) {
        super("corporation");
        name = "Interplanetary cinematics";
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeSteel(20);
        player.changeMoney(30);
        super.onPlay(player);
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null | owner_player != player) {
            return;
        }
        owner_player.changeMoney(2);
    }
}
