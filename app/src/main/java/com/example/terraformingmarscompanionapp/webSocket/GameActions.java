package com.example.terraformingmarscompanionapp.webSocket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

@SuppressLint("DefaultLocale")
public class GameActions {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();
    private static String game_code;
    private static Integer action_number = 0;
    private static Integer generation = 0;

    private static Context setup_screen;
    public static void setSetupScreen(Context screen) {setup_screen = screen;}

    public static String getGameCode() {return game_code;}

    static void handleGameEvent(String event_data) {
        String event_type = event_data.split(Pattern.quote(";"))[1];
        String event = event_data.split(Pattern.quote(";"))[2];
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
                GameController.getInstance().setPlayerIsFolding(true);
                GameController.getInstance().endTurn();
                break;
            case "end_generation":
                generation++;
                GameController.getInstance().endGeneration();
                break;
            default:
                Log.i("GameActions", "Unrecognized game action: " + event_data);
        }
    }

    //Pelin luominen, tallentaa pelikoodin
    static void handleGameCreated(String creation_data) {
        //TODO tarvittavat UI-hookit
        game_code = creation_data.split(Pattern.quote(";"))[1];
        Log.i("WebSocket", "Game created with code: " + game_code);
    }

    //Peliin liittyminen, tallentaa pelikoodin
    static void handleGameJoined(String join_data) {
        game_code = join_data.split(Pattern.quote(";"))[1];
        Log.i("WebSocket", "Game '" + game_code + "' joined.");
        for (int i = 2; i < join_data.split(Pattern.quote(";")).length ; i++) {
            //Jos pelaajia on vähemmän kuin viisi, tämä heittää errorin. Otetaan kiinni hiljaa.
            String player_name = join_data.split(Pattern.quote(";"))[i];
            Log.i("WebSocketJoin", player_name);
            ((ServerSetupScreen)setup_screen).playerJoined(player_name);
        }
    }

    //Toisen liittyminen peliin, käytännössä käyttäjänimen kirjaaminen
    static void handlePlayerJoined(String join_data) {
        String joined_user = join_data.split(Pattern.quote(";"))[1];
        Log.i("WebSocket", "User '" + joined_user + "' joined the game.");
        ((ServerSetupScreen)setup_screen).playerJoined(joined_user);
    }

    //Asetuksen muuttuminen
    static void handleSettingChanged(String web_socket_message) {
        GameSetting setting = GameSetting.valueOf(web_socket_message.split(Pattern.quote(";"))[1]);
        Boolean value = Boolean.valueOf(web_socket_message.split(Pattern.quote(";"))[2]);
        ((ServerSetupScreen)setup_screen).settingChanged(setting, value);
    }

    //Pelin aloittaminen
    static void handleGameStart() {
        Log.i("WebSocketGame", "Starting game");
        ((ServerSetupScreen)setup_screen).startGame();
    }

    public static void sendSettingChange(GameSetting setting, Boolean value) {
        String message = String.format("game_setting;%s;%s;%s;%s;%b", UserActions.getSessionUser(), UserActions.getSessionId(), game_code, setting.toString(), value.toString());
        WebSocketHandler.sendMessage(message);
    }

    public static void sendGameStart() {
        WebSocketHandler.sendMessage(String.format("start_game;%s;%s;%s", UserActions.getSessionUser(), UserActions.getSessionId(), game_code));
    }

    //Eri event pakettien lähettäminen:
    public static void sendCardEvent(CardEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;card_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        message += String.format(";%d;%d", action_number, generation);
        action_number++;
        WebSocketHandler.sendMessage(message);
    }

    public static void sendCardCost(CardCostPacket cost_packet) {
        String message = String.format("game_action;%s;%s;%s;card_cost;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(cost_packet);
        message += String.format(";%d;%d", action_number, generation);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendResourceEvent(ResourceEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;resource_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        message += String.format(";%d;%d", action_number, generation);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendTileEvent(TileEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;tile_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        message += String.format(";%d;%d", action_number, generation);
        WebSocketHandler.sendMessage(message);
    }

    //Foldaamisen lähettäminen
    public static void sendFold() {
        WebSocketHandler.sendMessage(String.format("game_action;%s;%s;%s;fold;%d;%d", UserActions.getSessionUser(), UserActions.getSessionId(), game_code, action_number, generation));
    }

    //Pelin luonut pelaaja määrittää pelin alussa vuorojärjestyksen ja se lähetetään tällä
    public static void sendTurnOrder(String[] order_list) {

    }

    /*Gson:
      CardEventPacket test = new CardEventPacket("Adaptation technology", "Eddie", 0);
      GsonBuilder test_builder = new GsonBuilder();
      Gson gson = test_builder.create();
      Log.i("GSON", gson.toJson(test));
     */
}
