package com.example.terraformingmarscompanionapp.game;

import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.game.events.GameEvent;

import java.util.Stack;

/**
 * A class for scheduling events, ui or otherwise.
 */

public class EventScheduler {
    private static Stack<GameEvent> eventStack = new Stack<>();

    public static void addEvent(GameEvent event) {
        Log.i("Event Scheduler", "Queued event: " + event.toString());
        eventStack.push(event);
    }

    public static void playNextEvent(Context context) {
        Log.i("Event Scheduler", "Playing event, stack size: " + eventStack.size());
        GameController.gameUpdate();
        if (eventStack.size() > 0) {
            eventStack.pop().playEvent(context);
        }
    }

    public static Boolean getStackHasEvents() {
        return eventStack.size() > 0;
    }

    public static void clearEventStack() {eventStack.clear();}
}