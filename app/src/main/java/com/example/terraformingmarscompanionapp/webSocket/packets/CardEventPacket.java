package com.example.terraformingmarscompanionapp.webSocket.packets;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

/**
 * A class representing a played card or a used action
 */
public class CardEventPacket implements ServerPacket {
    private String card_name;
    private String player_name;
    private Integer metadata;
    private String extra_card;

    //TODO modifications to get robotic workforce to work

    public CardEventPacket(String card_name, String player_name, Integer metadata) {
        this.card_name = card_name;
        this.player_name = player_name;
        this.metadata = metadata;
    }

    //The game can deduce whether a card was played or an action was used based on the card's owner
    @Override
    public void playPacket() {
        Game game = GameController.getGame();
        Card card = game.getAllCards().get(card_name);
        if (card.getOwner() == null) {
            card.playWithMetadata(GameController.getPlayer(player_name), metadata);
        } else if (card instanceof ActionCard) {
            ((ActionCard) card).actionWithMetadata(metadata);
        } else {
            Log.i("WebSocket", "CardEffectPacket send from a card with no action. Notify Eddie, card name: " + card_name);
        }
    }
}
