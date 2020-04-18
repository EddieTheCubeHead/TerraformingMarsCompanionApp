package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class InterplanetaryCinematics extends Card implements EffectCard {
    public InterplanetaryCinematics(Game game) {
        super(Type.CORPORATION);
        name = "Interplanetary cinematics";
        tags.add(Tag.BUILDING);
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
