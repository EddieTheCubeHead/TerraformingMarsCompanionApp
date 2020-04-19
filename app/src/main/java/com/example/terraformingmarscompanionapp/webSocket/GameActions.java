package com.example.terraformingmarscompanionapp.webSocket;

import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TurnActionInfoPacket;

public class GameActions {
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
}
