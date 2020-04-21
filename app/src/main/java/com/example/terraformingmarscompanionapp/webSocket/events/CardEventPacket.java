package com.example.terraformingmarscompanionapp.webSocket.events;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public class CardEventPacket implements PlayablePacket {
    private String card_name;
    private String player_name;
    private Integer metadata;

    public String getCardName() {return card_name;}
    public String getPlayerName() {return player_name;}
    public Integer getMetadata() {return metadata;}

    public CardEventPacket(String card_name, String player_name, Integer metadata) {
        this.card_name = card_name;
        this.player_name = player_name;
        this.metadata = metadata;
    }

    /* Saadut paketit pelataan. Onko kyseessä kortin pelaaminen vai toiminnan käyttö selviää kortin omistajasta:
     * pelatulla kortilla on aina omistaja => omistajallinen kortti on pelattu toimintona.
     */
    @Override
    public void playPacket() {
        Game game = GameController.getInstance().getGame();
        Card card = game.getDeck().get(card_name);
        if (card.getOwmer() == null) {
            card.playWithMetadata(game.getPlayer(player_name), metadata);
        } else if (card instanceof MetadataAction) {
            ((MetadataAction) card).actionWithMetadata(metadata);
        } else if (card instanceof ActionCard) {
            ((ActionCard) card).cardAction();
        } else {
            Log.i("WebSocket", "CardEffectPacket lähetetty omistetusta kortista, jolla ei ole toimintaa. Huomauta Eetua. Kortin nimi: " + card_name);
        }
    }
}
