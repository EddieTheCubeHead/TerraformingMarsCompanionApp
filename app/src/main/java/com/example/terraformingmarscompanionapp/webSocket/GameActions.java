package com.example.terraformingmarscompanionapp.webSocket;

import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TurnActionInfoPacket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GameActions {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    private static String game_code;

    public static void handleGameEvent(String event_data) {

    }

    public static void handleGameCreated(String creation_data) {

    }

    public static void sendTurnInfo(TurnActionInfoPacket action_info) {

    }

    public static void sendCardEvent(CardEventPacket event) {

    }

    public static void sendCardCost(CardCostPacket cost_packet) {

    }

    public static void sendResourceEvent(ResourceEventPacket event) {

    }

    public static void sendTileEvent(TileEventPacket event) {

    }

    /*Gson:
      CardEventPacket test = new CardEventPacket("Adaptation technology", "Eddie", 0);
      GsonBuilder test_builder = new GsonBuilder();
      Gson gson = test_builder.create();
      Log.i("GSON", gson.toJson(test));
     */
}
