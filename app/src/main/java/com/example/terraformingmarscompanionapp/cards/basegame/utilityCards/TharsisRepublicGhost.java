package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class TharsisRepublicGhost extends Card implements EffectCard {
    public TharsisRepublicGhost() {
        super(Type.GHOST);
        name = "Tharsis republic ghost";
    }

    @Override
    public void cardEffect(Player player) throws InvalidResourcesException {
        if (owner_player == null) {
            return;
        }

        owner_player.getResources().setMoneyProduction(owner_player.getResources().getMoneyProduction() + 1);
    }
}
