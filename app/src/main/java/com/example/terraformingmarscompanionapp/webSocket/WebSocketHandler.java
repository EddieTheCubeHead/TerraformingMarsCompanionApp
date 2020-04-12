package com.example.terraformingmarscompanionapp.webSocket;

import android.util.Log;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import tech.gusavila92.websocketclient.WebSocketClient;

//https://www.pubnub.com/blog/java-websocket-programming-with-android-and-spring-boot/
public final class WebSocketHandler {

    private static WebSocketClient webSocketClient = null;

    //WebSocketin sydän. Vastaanottaa serverin viestit. Käsittelyyn oltava funktio muualla logia lukuunottamatta.
    //TODO kaikki käsittelevät funktiot oikeaan kohtaan koodissa
    //TODO, Eetu: tutustu gson-tarjoamiin mahdollisuuksiin, suunnittele pelin tapahtumille paras tapa kulkea serverin ja clientin välillä.
    public static void createWebSocketClient() {
        URI uri;
        try {
            uri = new URI("ws://10.0.2.2:8080/tfmca");
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
                            UserActions.handleLogin(s);
                            break;

                        //Exceptioneiden käsittely
                        case "username_exception":
                            Log.i("WebSocketException", "Invalid username: " + contents[1]);
                        case "password_exception":
                            Log.i("WebSocketException", "Invalid password: " + contents[1]);

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

            }

            @Override
            public void onPongReceived(byte[] data) {

            }

            @Override
            public void onException(Exception e) {
                System.out.println(e.getMessage());
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
        webSocketClient.send(message);
    }
}
