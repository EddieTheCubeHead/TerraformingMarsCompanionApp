package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.player.Player;

/**
 * An event representing playing a production box of a card with the card {@link com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce}
 * <p></p>
 * Used to schedule the needed actions when switching between card choosing activity and main activity.
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public class RoboticWorkforcePlayEvent implements GameEvent {

    private Card card;
    private Player player;
    private Integer metadata;

    public RoboticWorkforcePlayEvent(Card card, Player player, Integer metadata) {
        this.card = card;
        this.player = player;
        this.metadata = metadata;
    }

    @Override
    public void playEvent(Context context) {
        card.getProductionBox().playProductionBox(player, metadata);
        EventScheduler.playNextEvent(context);
    }
}
