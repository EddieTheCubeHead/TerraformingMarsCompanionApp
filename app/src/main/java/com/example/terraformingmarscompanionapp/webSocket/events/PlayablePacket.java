package com.example.terraformingmarscompanionapp.webSocket.events;

/**
 * The server implementation works by senting PlayablePacket -interface implementing objects with gson
 */
interface PlayablePacket {
    void playPacket();
}
