package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

/**
 * An interface that represents all different events that can be called during the process of
 * playing a card. Instances of this interfaced are queued into {@link com.example.terraformingmarscompanionapp.game.EventScheduler}
 * and played from there using {@link #playEvent(Context)}.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public interface GameEvent {

    /**
     * The most important function of all implementations. Gets called from {@link com.example.terraformingmarscompanionapp.game.EventScheduler#playNextEvent(Context)}.
     *
     * @param context {@link Context} the android UI context that the action gets called from. Usually {@link com.example.terraformingmarscompanionapp.InGameUI}
     */
    void playEvent(Context context);
}
