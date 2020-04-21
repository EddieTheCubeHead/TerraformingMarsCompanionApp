package com.example.terraformingmarscompanionapp.webSocket;

import android.util.Log;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TurnActionInfoPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

public class GameActions {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();
    private static String game_code;

    //Kaikki pelitapahtumat routataan tästä läpi ServerGameControlleriin
    public static void handleGameEvent(String event_data) {
        String event_type = event_data.split(Pattern.quote(";"))[1];
        String event = event_data.split(Pattern.quote(";"))[2];
        switch (event_data.split(Pattern.quote(";"))[1]) {
            case "turn_info":
                break;
            case "card_event":
                break;
            case "card_cost":
                break;
            case "resource_event":
                break;
            case "tile_event":
                break;
            case "fold":
                GameController.getInstance().setPlayerIsFolding(true);
                GameController.getInstance().endTurn();
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
        //TODO Tarvittavat UI-hookit

    }

    //Toisen liittyminen peliin, käytännössä käyttäjänimen kirjaaminen
    static void handlePlayerJoined(String join_data) {
        String joined_user = join_data.split(Pattern.quote(";"))[1];
        Log.i("WebSocket", "User '" + joined_user + "' joined the game.");
        //TODO liittymislogiikka
        //TODO tarvittavat UI-hookit
    }

    //Pelin aloittaminen
    static void handleGameStart(String start_data) {
        //TODO tarvittavat UI-hookit
    }

    /* Seuraavat viisi funktiota lähettävät kaikki vuoron pelaamisen aikana mahdollisesti tapahtuvat
     * tapahtumat erityyppisinä datapaketteina */
    public static void sendTurnInfo(TurnActionInfoPacket action_info) {
        String message = String.format("game_action;%s;%s;%s;turn_info;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(action_info);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendCardEvent(CardEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;card_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendCardCost(CardCostPacket cost_packet) {
        String message = String.format("game_action;%s;%s;%s;card_cost;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(cost_packet);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendResourceEvent(ResourceEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;resource_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        WebSocketHandler.sendMessage(message);
    }

    public static void sendTileEvent(TileEventPacket event) {
        String message = String.format("game_action;%s;%s;%s;tile_event;", UserActions.getSessionUser(), UserActions.getSessionId(), game_code);
        message += gson.toJson(event);
        WebSocketHandler.sendMessage(message);
    }

    //Foldaamisen lähettäminen
    public static void sendFold() {
        WebSocketHandler.sendMessage("game_action;%s;%s;%s;fold");
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
