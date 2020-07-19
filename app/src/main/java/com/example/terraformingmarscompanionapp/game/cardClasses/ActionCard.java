package com.example.terraformingmarscompanionapp.game.cardClasses;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;

/**
 * Interface to be implemented by {@link Card} instances that represent cards with playable actions.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public interface ActionCard {

    /**
     * Method used to start playing the action from card. All functionalities that should only
     * be seen on the client playing the card should go here.
     */
    void cardAction();

    /**
     * Method used in the playing process. Main functionality of the method is sending data to
     * the server if the game is played through one. Can also be overriden for more precise
     * control over the playing process in special cases.
     *
     * @param player {@link Player} using the action
     * @param data {@link Integer} metadata associated with the action
     */
    default void actionServerHook(Player player, Integer data) {
        if (GameController.getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), data));
        }
        setActionToUsed();
        actionWithMetadata(data);
    }

    /**
     * Overloading {@link #actionServerHook(Player, Integer)} in cases where the action doesn't need metadata.
     *
     * @param player {@link Player} using the action
     */
    default void actionServerHook(Player player) {
        if (GameController.getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), 0));
        }
        setActionToUsed();
        actionWithMetadata(0);
    }

    /**
     * Last part of the card playing process. All functionalities shared across all clients in the game
     * should go here.
     *
     * @param data {@link Integer} metadata associated with the action
     */
    void actionWithMetadata(Integer data);

    /**
     * Special getter for the name of the card implementing the interface. Mainly used in the default
     * function {@link #actionServerHook(Player, Integer)}.
     *
     * @return {@link String} name of the card
     */
    String getActionName();

    /**
     * Method for checking whether a player can play the card. Note that the return is inverse to
     * what is implied in the wording of the method name because of the method slightly changing
     * functionality during developemnt.
     *
     * @return {@link Boolean} true if card can not be played, false if card can be played
     */
    Boolean getActionValidity();

    /**
     * Method to set the action as used
     */
    void setActionToUsed();
}
