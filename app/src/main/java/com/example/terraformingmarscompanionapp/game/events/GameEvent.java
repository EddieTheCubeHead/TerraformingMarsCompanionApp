package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.GameController;

import org.jetbrains.annotations.NotNull;

/**
 * An interface that represents all different events that can be called during the process of
 * playing a card. Instances of this interfaced are queued into {@link com.example.terraformingmarscompanionapp.game.EventScheduler}
 * and played from there using {@link #playEvent(Context)}.
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.2
 */
public interface GameEvent {

    /**
     * The most important function of all implementations. Gets called from {@link com.example.terraformingmarscompanionapp.game.EventScheduler#playNextEvent(Context)}.
     *
     * @param context {@link Context} the android UI context that the action gets called from. Usually {@link com.example.terraformingmarscompanionapp.InGameUI}
     */
    void playEvent(Context context);

    /**
     * An overload to run {@link #playEvent(Context context)} with a Context recieved from {@link GameController}
     * Should be avoided and mainly delegated to testing, instead preferring to get Context from
     * a parameter
     */
    default void playEvent() {
        playEvent(GameController.getContext());
    }
}
