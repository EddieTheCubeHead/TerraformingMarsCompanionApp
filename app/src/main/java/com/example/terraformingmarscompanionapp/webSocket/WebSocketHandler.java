package com.example.terraformingmarscompanionapp.webSocket;

import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import tech.gusavila92.websocketclient.WebSocketClient;

/**
 * Handler for the websocket data
 */
//https://www.pubnub.com/blog/java-websocket-programming-with-android-and-spring-boot/
public final class WebSocketHandler {
    private static WebSocketClient webSocketClient = null;
    private static Boolean is_initialized = false;

    //For rapid changing of server ip in development. Leave to null for localhost6
    private static final String WEBSOCKET_URI = "ws://168.61.98.65:8080/tfmca";

    //Azure server ip
    //"ws://168.61.98.65:8080/tfmca"

    //The heart of the WebSocket implementation
    public static void createWebSocketClient() {
        if (is_initialized) {
            return;
        }
        is_initialized = true;
        URI uri;
        try {
            //Setting the ip to localhost if public ip not set
            uri = new URI(WEBSOCKET_URI == null ? "ws://10.0.2.2:8080/tfmca" : WEBSOCKET_URI);
            System.out.println("WebSocket: URI set.");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
            }

            @Override
            public void onTextReceived(String s) {
                final String[] contents = s.split(Pattern.quote(";"));
                final String identifier = contents[0];
                    switch (identifier) {
                        //Kirjautumisen ja uuden käyttäjän luomisen onnistumiset
                        case "user_creation_successful":
                            UserActions.handleCreation(s);
                            break;
                        case "login_successful":
                            UserActions.handleLogin(s);
                            break;

                        case "logout_successful":
                            //Just catching this to prevent logging it as unrecognized
                            break;

                        //Creating and joining the game
                        case "game_created":
                            GameActions.handleGameCreated(s);
                            break;

                        case "check_code":
                            UserActions.handleCodeCheck(s);
                            break;

                        case "game_joined":
                            GameActions.handleGameJoined(s);
                            break;

                        case "player_joined":
                            GameActions.handlePlayerJoined(s);
                            break;

                        case "game_setting":
                            GameActions.handleSettingChanged(s);
                            break;

                        case "game_start":
                            GameActions.handleGameStart();
                            break;

                        //During game
                        case "game_action":
                            GameActions.handleGameEvent(s);
                            break;

                        //Exceptions
                        case "username_exception":
                            UserActions.successful_login = false;
                            UserActions.message = "Invalid username.";
                            break;
                        case "password_exception":
                            UserActions.successful_login = false;
                            UserActions.message = "Invalid password.";

                        //Logging unrecognized messages
                        default:
                            Log.i("WebSocket" ,"Unrecognized message: " + contents[1]);
                    }
                }
            @Override
            public void onBinaryReceived(byte[] data) {

            }

            @Override
            public void onPingReceived(byte[] data) {
                webSocketClient.sendPong(new byte[] {1});
            }

            @Override
            public void onPongReceived(byte[] data) {

            }

            @Override
            public void onException(Exception e) {
                Log.i("WebSocket",  "exception on message: " + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onCloseReceived() {
            }
        };

        webSocketClient.setConnectTimeout(10000);
        webSocketClient.setReadTimeout(60000);
        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();
    }

    //Called from GameActions and UserActions -classes
    static void sendMessage(String message) {
        webSocketClient.send(message);
    }
}
