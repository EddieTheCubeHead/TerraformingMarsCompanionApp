package com.example.terraformingmarscompanionapp.webSocket.packets;

import com.example.terraformingmarscompanionapp.exceptions.GameplayException;

/**
 * The server implementation works by senting ServerPacket -interface implementing objects with gson
 */
interface ServerPacket {
    void playPacket() throws GameplayException;
}
