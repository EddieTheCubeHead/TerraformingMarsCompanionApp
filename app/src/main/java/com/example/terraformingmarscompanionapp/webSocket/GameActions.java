package com.example.terraformingmarscompanionapp.webSocket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.packets.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.packets.TileEventPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

/**
 * A handler for incoming and outgoing websocket data during a game
 */
@SuppressLint("DefaultLocale")
public class GameActions {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();
    private static String game_code;

    //Constants for different strings to prevent typos and make future edits easier
    private static final String GAME_ACTION = "game_action";
    private static final String GAME_SETTING = "game_setting";
    private static final String GAME_START = "game_start";

    private static final String CARD_EVENT = "card_event";
    private static final String CARD_COST = "card_cost";
    private static final String RESOURCE_EVENT = "resource_event";
    private static final String TILE_EVENT = "tile_event";
    private static final String ACTION_USE = "action_use";
    private static final String START_GENERATION = "start_generation";

    private static WeakReference<Context> creation_context;
    public static void setContext(Context context) {creation_context = new WeakReference<>(context);}
    private static void resetContext() {creation_context = null;}

    public static String getGameCode() {return game_code;}

    static void handleGameEvent(String event_data) {
        String event_type = event_data.split(Pattern.quote(";"))[1];
        String event = event_data.split(Pattern.quote(";"))[2];
        new Thread(() -> ((InGameUI)GameController.getContext()).runOnUiThread(() -> {
            switch (event_type) {
                case CARD_EVENT:
                    gson.fromJson(event, CardEventPacket.class).playPacket();
                    break;
                case CARD_COST:
                    gson.fromJson(event, CardCostPacket.class).playPacket();
                    break;
                case RESOURCE_EVENT:
                    gson.fromJson(event, ResourceEventPacket.class).playPacket();
                    break;
                case TILE_EVENT:
                    gson.fromJson(event, TileEventPacket.class).playPacket();
                    break;
                case ACTION_USE:
                    gson.fromJson(event, ActionUsePacket.class).playPacket();
                    break;
                case START_GENERATION:
                    GameController.atGenerationStart();
                    break;
                default:
                    Log.i("GameActions", "Unrecognized game action: " + event_data);
            }
        })).start();
    }

    //Creating a game saves the game code
    static void handleGameCreated(String creation_data) {
        game_code = creation_data.split(Pattern.quote(";"))[1];
    }

    //Joining a game extracts the settings and saves the game code
    static void handleGameJoined(String join_data) {
        String[] data_points = join_data.split(Pattern.quote(";"));
        game_code = data_points[1];
        ServerSetupScreen context = ((ServerSetupScreen)creation_context.get());
        for (int i = 2; i < data_points.length-10 ; i++) {
            String player_name = data_points[i];
            context.playerJoined(player_name);
        }
        int first_setting = (data_points.length - 10);
        context.settingChanged(GameSetting.CORPORATE_ERA, data_points[first_setting].equals("1"));
        context.settingChanged(GameSetting.PRELUDE, data_points[first_setting+1].equals("1"));
        context.settingChanged(GameSetting.VENUS, data_points[first_setting+2].equals("1"));
        context.settingChanged(GameSetting.COLONIES, data_points[first_setting+3].equals("1"));
        context.settingChanged(GameSetting.TURMOIL, data_points[first_setting+4].equals("1"));
        context.settingChanged(GameSetting.EXTRA_CORPORATIONS, data_points[first_setting+5].equals("1"));
        context.settingChanged(GameSetting.WORLD_GOVERNMENT_TERRAFORMING, data_points[first_setting+6].equals("1"));
        context.settingChanged(GameSetting.MUST_MAX_VENUS, data_points[first_setting+7].equals("1"));
        context.settingChanged(GameSetting.TURMOIL_TERRAFORMING_REVISION, data_points[first_setting+8].equals("1"));
        context.mapChanged(Integer.valueOf(data_points[first_setting+9]));
    }

    //When somebody else joins the game add the name to the player name list
    static void handlePlayerJoined(String join_data) {
        String joined_user = join_data.split(Pattern.quote(";"))[1];
        ((ServerSetupScreen) creation_context.get()).playerJoined(joined_user);
    }

    //Changing global settings
    static void handleSettingChanged(String web_socket_message) {
        GameSetting setting = GameSetting.valueOf(web_socket_message.split(Pattern.quote(";"))[1]);
        Boolean value = Boolean.valueOf(web_socket_message.split(Pattern.quote(";"))[2]);
        ((ServerSetupScreen) creation_context.get()).settingChanged(setting, value);
    }

    //Starting the game
    static void handleGameStart() {
        ((ServerSetupScreen) creation_context.get()).startGame();
        resetContext();
    }

    public static void sendSettingChange(GameSetting setting, Boolean value) {
        String message = String.format("%s;%s;%s;%b", GAME_SETTING, generateEventId(), setting.toString(), value);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendGameStart() {
        WebSocketHandler.sendMessage(String.format("%s;%s", GAME_START, generateEventId()));
    }

    //Sending the different game events
    public static void sendCardEvent(CardEventPacket event) {
        String message = String.format("%s;%s;%s;%s;%s", GAME_ACTION, generateEventId(), CARD_EVENT, generateActionInfo(), gson.toJson(event));
        WebSocketHandler.sendMessage(message);
    }

    public static void sendCardCost(CardCostPacket event) {
        String message = String.format("%s;%s;%s;%s;%s", GAME_ACTION, generateEventId(), CARD_COST, generateActionInfo(), gson.toJson(event));
        WebSocketHandler.sendMessage(message);
    }

    public static void sendResourceEvent(ResourceEventPacket event) {
        String message = String.format("%s;%s;%s;%s;%s", GAME_ACTION, generateEventId(), RESOURCE_EVENT, generateActionInfo(), gson.toJson(event));
        WebSocketHandler.sendMessage(message);
    }

    public static void sendTileEvent(TileEventPacket event) {
        String message = String.format("%s;%s;%s;%s;%s", GAME_ACTION, generateEventId(), TILE_EVENT, generateActionInfo(), gson.toJson(event));
        WebSocketHandler.sendMessage(message);
    }

    public static void sendActionUse(ActionUsePacket event) {
        String message = String.format("%s;%s;%s;%s;%s", GAME_ACTION, generateEventId(), ACTION_USE, generateActionInfo(), gson.toJson(event));
        WebSocketHandler.sendMessage(message);
    }

    public static void sendChangeGeneration() {
        WebSocketHandler.sendMessage(String.format("%s;%s;%s;empty", GAME_ACTION, generateEventId(), START_GENERATION));
    }

    //Two functions to generate the constant parts of sent Strings
    private static String generateActionInfo() {
        return String.format("%d;%d", GameController.getActionNumber(), GameController.getGeneration());
    }

    private static String generateEventId() {
        return String.format("%s;%s;%s", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
    }
}
