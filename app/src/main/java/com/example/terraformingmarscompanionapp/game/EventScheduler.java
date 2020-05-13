package com.example.terraformingmarscompanionapp.game;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.events.GameEvent;

import java.util.Stack;

/**
 * A class for scheduling events, ui or otherwise.
 */

public class EventScheduler {
    private static Stack<GameEvent> eventStack = new Stack<>();

    public static void addEvent(GameEvent event) {eventStack.push(event);}

    public static void playNextEvent(Context context) {
        GameController.gameUpdate();
        if (eventStack.size() > 0) {
            eventStack.pop().playEvent(context);
        }
    }

    public static void clearEventStack() {eventStack.clear();}
}