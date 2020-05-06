package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TharsisRepublicSecondEffect extends Card implements EffectCard {
    public TharsisRepublicSecondEffect(Game game) {
        super(Type.GHOST, game);
        name = "Tharsis republic ghost";
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            System.out.println("Tried to call tharsis republic second effect with no owner");
            return;
        }
        owner_player.changeMoneyProduction(1);
    }
}
