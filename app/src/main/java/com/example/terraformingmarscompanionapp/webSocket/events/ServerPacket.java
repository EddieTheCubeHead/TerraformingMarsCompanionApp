package com.example.terraformingmarscompanionapp.webSocket.events;

/**
 * The server implementation works by senting ServerPacket -interface implementing objects with gson
 */
interface ServerPacket {
    void playPacket();
}
