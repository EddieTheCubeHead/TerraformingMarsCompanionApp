package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

public interface ActionCard {
    void cardAction();
    default void actionServerHook(Player player, Integer data) {
        if (GameController.getInstance().getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), data));
        }
        setActionToUsed();
        actionWithMetadata(data);
    }
    default void actionServerHook(Player player) {
        if (GameController.getInstance().getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), 0));
        }
        setActionToUsed();
        actionWithMetadata(0);
        GameController.getInstance().gameUpdate();
    }
    void actionWithMetadata(Integer data);

    String getActionName();

    //Huom. aikaisessa vaiheessa tehdyn virheen vuoksi tämä palauttaa booleanin "käänteisesti".
    //Koska korjaamiseen menisi suhteellisen paljon aikaa suhteessa hyötyyn, tämä on nyt toiminto, eikä bugi
    Boolean getActionValidity();
    void setActionToUsed();
}
