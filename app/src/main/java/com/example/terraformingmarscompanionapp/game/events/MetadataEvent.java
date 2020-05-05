package com.example.terraformingmarscompanionapp.game.events;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

import java.util.ArrayList;
import java.util.List;

public final class MetadataEvent extends GameEvent {
    private MetadataType type;
    private Card card;
    private List<Player> eligible_players = null;

    public MetadataEvent(MetadataType type, Card card) {
        this.type = type;
        this.card = card;
        eligible_players = GameController.getInstance().getPlayers();
    }

    public MetadataEvent(ArrayList<Player> players, Card card) {
        this.type = MetadataType.PLAYER;
        this.card = card;
        eligible_players = players;
    }

    @Override
    public void playEvent() {

    }

    public enum MetadataType {
        PLAYER,
        NUMBER,
        CHOICE
    }
}
