package com.example.terraformingmarscompanionapp.webSocket.packets;

/**
 * The server implementation works by senting ServerPacket -interface implementing objects with gson
 */
interface ServerPacket {
    void playPacket();
}
