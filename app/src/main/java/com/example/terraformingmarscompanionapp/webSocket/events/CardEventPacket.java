package com.example.terraformingmarscompanionapp.webSocket.events;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

/**
 * A class representing a played card or a used action
 */
public class CardEventPacket implements PlayablePacket {
    private String card_name;
    private String player_name;
    private Integer metadata;
    private String extra_card;

    public CardEventPacket(String card_name, String player_name, Integer metadata) {
        this.card_name = card_name;
        this.player_name = player_name;
        this.metadata = metadata;
        extra_card = "";
    }

    //Just for robotic workforce
    public CardEventPacket(String card_name, String player_name, Integer metadata, String extra_card) {
        this.card_name = card_name;
        this.player_name = player_name;
        this.metadata = metadata;
        this.extra_card = extra_card;
    }

    //The game can deduce whether a card was played or an action was used based on the card's owner
    @Override
    public void playPacket() {
        Game game = GameController.getInstance().getGame();
        Card card = game.getAllCards().get(card_name);
        if (card.getOwner() == null) {
            card.playWithMetadata(game.getPlayer(player_name), metadata);
        } else if (card instanceof ActionCard) {
            ((ActionCard) card).actionWithMetadata(metadata);
            GameController.getInstance().useAction();
        } else {
            Log.i("WebSocket", "CardEffectPacket lähetetty omistetusta kortista, jolla ei ole toimintaa. Huomauta Eetua. Kortin nimi: " + card_name);
        }
        if (!extra_card.equals("")) {
            game.getAllCards().get(extra_card).playProductionBox();
        }
    }
}
