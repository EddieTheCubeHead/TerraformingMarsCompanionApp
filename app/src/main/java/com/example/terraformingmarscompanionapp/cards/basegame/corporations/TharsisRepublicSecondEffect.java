package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class TharsisRepublicSecondEffect extends Card implements EffectCard {
    public TharsisRepublicSecondEffect(Game game) {
        super("ghost");
        name = "Tharsis republic ghost";
        owner_game = game;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.changeMoneyProduction(1);
    }
}
