package com.example.terraformingmarscompanionapp.webSocket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

/**
 * A handler for incoming and outgoing websocket data during a game
 */
@SuppressLint("DefaultLocale")
public class GameActions {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();
    private static String game_code;
    private static Integer action_number = 0;

    private static Context context;
    public static void setContext(Context screen) {context = screen;}
    private static void resetContext() {context = null;}

    public static String getGameCode() {return game_code;}

    static void handleGameEvent(String event_data) {
        String event_type = event_data.split(Pattern.quote(";"))[1];
        String event = event_data.split(Pattern.quote(";"))[2];
        new Thread(() -> ((InGameUI)GameController.getInstance().getContext()).runOnUiThread(() -> {
            switch (event_type) {
                case "card_event":
                    gson.fromJson(event, CardEventPacket.class).playPacket();
                    action_number++;
                    break;
                case "card_cost":
                    gson.fromJson(event, CardCostPacket.class).playPacket();
                    break;
                case "resource_event":
                    gson.fromJson(event, ResourceEventPacket.class).playPacket();
                    break;
                case "tile_event":
                    gson.fromJson(event, TileEventPacket.class).playPacket();
                    break;
                case "fold":
                    GameController.getInstance().endTurn();
                    break;
                case "use_action":
                    GameController.getInstance().useActionServer();
                    break;
                case "start_generation":
                    GameController.getInstance().atGenerationStart();
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
        for (int i = 2; i < data_points.length-10 ; i++) {
            String player_name = data_points[i];
            ((ServerSetupScreen) context).playerJoined(player_name);
        }
        int first_setting = (data_points.length - 10);
        ((ServerSetupScreen) context).settingChanged(GameSetting.CORPORATE_ERA, data_points[first_setting].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.PRELUDE, data_points[first_setting+1].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.VENUS, data_points[first_setting+2].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.COLONIES, data_points[first_setting+3].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.TURMOIL, data_points[first_setting+4].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.EXTRA_CORPORATIONS, data_points[first_setting+5].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.WORLD_GOVERNMENT_TERRAFORMING, data_points[first_setting+6].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.MUST_MAX_VENUS, data_points[first_setting+7].equals("1"));
        ((ServerSetupScreen) context).settingChanged(GameSetting.TURMOIL_TERRAFORMING_REVISION, data_points[first_setting+8].equals("1"));
        ((ServerSetupScreen) context).mapChanged(Integer.valueOf(data_points[first_setting+9]));
    }

    //When somebody else joins the game add the name to the player name list
    static void handlePlayerJoined(String join_data) {
        String joined_user = join_data.split(Pattern.quote(";"))[1];
        ((ServerSetupScreen) context).playerJoined(joined_user);
    }

    //Changing global settings
    static void handleSettingChanged(String web_socket_message) {
        GameSetting setting = GameSetting.valueOf(web_socket_message.split(Pattern.quote(";"))[1]);
        Boolean value = Boolean.valueOf(web_socket_message.split(Pattern.quote(";"))[2]);
        ((ServerSetupScreen) context).settingChanged(setting, value);
    }

    //Starting the game
    static void handleGameStart() {
        ((ServerSetupScreen) context).startGame();
        resetContext();
    }

    public static void sendSettingChange(GameSetting setting, Boolean value) {
        String message = String.format("game_setting;%s;%s;%s;%s;%b", UserActions.getSessionUser(), UserActions.getSessionId(), game_code, setting.toString(), value);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendGameStart() {
        WebSocketHandler.sendMessage(String.format("start_game;%s;%s;%s", UserActions.getSessionUser(), UserActions.getSessionId(), game_code));
    }

    //Sending the different game events
    public static void sendCardEvent(CardEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;card_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        message += String.format(";%d;%d", action_number, GameController.getInstance().getGeneration());
        action_number++;
        WebSocketHandler.sendMessage(message);
    }

    public static void sendCardCost(CardCostPacket cost_packet) {
        String message = String.format("game_action;%s;%s;%s;card_cost;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(cost_packet);
        message += String.format(";%d;%d", action_number, GameController.getInstance().getGeneration());
        WebSocketHandler.sendMessage(message);
    }

    public static void sendResourceEvent(ResourceEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;resource_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        message += String.format(";%d;%d", action_number, GameController.getInstance().getGeneration());
        WebSocketHandler.sendMessage(message);
    }

    public static void sendTileEvent(TileEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;tile_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        message += String.format(";%d;%d", action_number, GameController.getInstance().getGeneration());
        WebSocketHandler.sendMessage(message);
    }

    //Sending turn action data
    public static void sendFold() {
        WebSocketHandler.sendMessage(String.format("game_action;%s;%s;%s;fold;empty;%d;%d", UserActions.getSessionUser(), UserActions.getSessionId(), game_code, action_number, GameController.getInstance().getGeneration()));
    }

    public static void sendUseAction() {
        WebSocketHandler.sendMessage(String.format("game_action;%s;%s;%s;use_action;empty;%d;%d", UserActions.getSessionUser(), UserActions.getSessionId(), game_code, action_number, GameController.getInstance().getGeneration()));
    }

    public static void sendChangeGeneration() {
        WebSocketHandler.sendMessage(String.format("game_action;%s;%s;%s;start_generation;empty;%d;%d", UserActions.getSessionUser(), UserActions.getSessionId(), game_code, action_number, GameController.getInstance().getGeneration()));
    }
}
