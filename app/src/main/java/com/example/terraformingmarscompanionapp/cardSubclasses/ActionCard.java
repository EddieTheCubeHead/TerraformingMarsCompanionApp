package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;

/**
 * Interface for cards that have playable actions if owned. Action usage flow mimics that of playing
 * a card.
 */
public interface ActionCard {

    void cardAction();

    default void actionServerHook(Player player, Integer data) {
        if (GameController.getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), data));
        }
        setActionToUsed();
        actionWithMetadata(data);
    }

    default void actionServerHook(Player player) {
        if (GameController.getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), 0));
        }
        setActionToUsed();
        actionWithMetadata(0);
    }

    void actionWithMetadata(Integer data);

    String getActionName();

    // Note: returns an "inverse" value based on wording. This is due to a decision made too early in
    // development to realise the use of the function would change drastically.
    Boolean getActionValidity();

    void setActionToUsed();

    default Boolean getActionRequiresWait() {return false;}
}
