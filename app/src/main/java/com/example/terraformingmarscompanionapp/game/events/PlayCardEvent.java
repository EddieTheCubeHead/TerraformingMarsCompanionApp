package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

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
        card.onPlayServerHook(player, metadata);
    }
}
