package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

/**
 * Events are used to queue up different UI's when playing a card
 */

public abstract class GameEvent {
    public abstract void playEvent(Context context);
}
