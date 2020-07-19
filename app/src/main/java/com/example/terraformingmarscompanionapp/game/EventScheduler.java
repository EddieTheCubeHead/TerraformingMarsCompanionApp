package com.example.terraformingmarscompanionapp.game;

import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.events.GameEvent;

import java.util.Stack;

/**
 * A class for scheduling instances of {@link GameEvent} into a stack representing the whole process
 * of playing a subclass of {@link Card} and
 * playing events from the stack.
 * <p></p>
 * The idea behind using a stack is if an action is evoked due to another action (for example a placement
 * bonus on the map) then the evoked action gets activated right away. The bottom event should always
 * be some form of {@link com.example.terraformingmarscompanionapp.game.events.ActionUseEvent}. This
 * way syncinc actions with the server is straighforward and easy.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */

public class EventScheduler {
    private static Stack<GameEvent> eventStack = new Stack<>();

    /**
     * A method to add an event into the event stack. If the stack is not empty the bottom event
     * should often be an {@link com.example.terraformingmarscompanionapp.game.events.ActionUseEvent}
     *
     * @param event {@link GameEvent} to add to the top of the stack
     */
    public static void addEvent(GameEvent event) {
        Log.i("Event Scheduler", "Queued event: " + event.toString());
        eventStack.push(event);
    }

    /**
     * A method to play the next event from the event stack
     *
     * @param context {@link Context} the UI context the method is called from
     */
    public static void playNextEvent(Context context) {
        Log.i("Event Scheduler", "Playing event, stack size: " + eventStack.size());
        GameController.gameUpdate();
        if (eventStack.size() > 0) {
            eventStack.pop().playEvent(context);
        }
    }

    /**
     * A simple method to check whether the stack has any actions left
     *
     * @return {@link Boolean} true if the stack has actions left, otherwise false
     */
    public static Boolean getStackHasEvents() {
        return eventStack.size() > 0;
    }
}