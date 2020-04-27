package com.example.terraformingmarscompanionapp.webSocket;

import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import tech.gusavila92.websocketclient.WebSocketClient;

//https://www.pubnub.com/blog/java-websocket-programming-with-android-and-spring-boot/
public final class WebSocketHandler {
    private static WebSocketClient webSocketClient = null;

    //Aseta serverin ip tänne testauksessa
    private static final String WEBSOCKET_URI = null;

    //WebSocketin sydän. Vastaanottaa serverin viestit. Käsittelyyn oltava funktio muualla logia lukuunottamatta.
    public static void createWebSocketClient() {
        URI uri;
        try {
            uri = new URI(WEBSOCKET_URI == null ? "ws://10.0.2.2:8080/tfmca" : WEBSOCKET_URI);
            System.out.println("WebSocket: URI set.");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                Log.i("WebSocket", "Session is starting");
            }

            @Override
            public void onTextReceived(String s) {
                Log.i("WebSocket", "Message received");
                final String[] contents = s.split(Pattern.quote(";"));
                final String identifier = contents[0];
                    switch (identifier) {
                        //Kirjautumisen ja uuden käyttäjän luomisen onnistumiset
                        case "user_creation_successful":
                            Log.i("WebSocket", "User creation successful.");
                            UserActions.handleCreation(s);
                            break;
                        case "login_successful":
                            Log.i("WebSocket", "Login successful!");
                            UserActions.successful_login = true;
                            UserActions.handleLogin(s);
                            break;

                        //Pelin luominen ja liittyminen
                        case "game_created":
                            GameActions.handleGameCreated(s);
                            break;

                        case "game_joined":
                            GameActions.handleGameJoined(s);
                            break;

                        case "player_joined":
                            GameActions.handlePlayerJoined(s);
                            break;

                        //Pelin toiminnot
                        case "game_action":
                            GameActions.handleGameEvent(s);
                            break;

                        //Exceptioneiden käsittely
                        case "username_exception":
                            Log.i("WebSocketException", "Invalid username: " + contents[1]);
                            UserActions.successful_login = false;
                            UserActions.message = "Invalid username.";
                            break;
                        case "password_exception":
                            Log.i("WebSocketException", "Invalid password: " + contents[1]);
                            UserActions.successful_login = false;
                            UserActions.message = "Invalid password.";
                            break;

                        case "test":
                            Log.i("WebSocket", "Successful test");

                        //Tunnistamattomien viestien loggaaminen
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
                Log.i("WebSocket",  "exception on message " + e.getMessage());
            }

            @Override
            public void onCloseReceived() {
                Log.i("WebSocket", "Closed");
            }
        };

        webSocketClient.setConnectTimeout(10000);
        webSocketClient.setReadTimeout(60000);
        webSocketClient.enableAutomaticReconnection(5000);
        webSocketClient.connect();
    }

    //Kutsutaan GameActions- ja UserActions -luokista.
    static void sendMessage(String message) {
        Log.i("WebSocket", "Sending message " + message);
        webSocketClient.send(message);
    }
}
