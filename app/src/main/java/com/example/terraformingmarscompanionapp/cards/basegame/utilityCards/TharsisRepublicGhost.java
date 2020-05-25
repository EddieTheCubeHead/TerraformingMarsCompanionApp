package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;

public final class TharsisRepublicGhost extends Card implements EffectCard {
    public TharsisRepublicGhost(Game game) {
        super(Type.GHOST, game);
        name = "Tharsis republic ghost";
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.playNextEvent(context);
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
