package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PlayCardEvent extends GameEvent {
    private Card card;
    private Player player;
    private Integer metadata;

    public PlayCardEvent(Card card, Player player, Integer metadata) {
        this.card = card;
        this.player = player;
        this.metadata = metadata;
    }

    @Override
    public void playEvent(Context context) {
        if (card.getOwner() != null) {
            card.onPlayServerHook(player, metadata);
        } else if (card instanceof ActionCard) {
            ((ActionCard) card).actionServerHook(player, metadata);
        }
    }
}
