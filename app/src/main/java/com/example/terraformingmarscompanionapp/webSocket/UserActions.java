package com.example.terraformingmarscompanionapp.webSocket;

import android.util.Log;

import java.util.Locale;
import java.util.regex.Pattern;

public class UserActions {

    //Session tunnistamiseen. Vakavammassa toteutetussa tarvittaisiin syvällisempi järjestelmä.
    private static String session_id = null;
    private static String session_user = null;
    static String getSessionId() {return session_id;}
    static String getSessionUser() {return session_user;}

    //Login, ottaa käyttäjänimen ja salasanan.
    public static void loginUser(String username, String password) {
        WebSocketHandler.sendMessage(String.format("login;%s;%s", username, password));
        session_user = username;
    }

    //Uuden käyttäjän luonti, ottaa käyttäjänimen ja salasanan.
    public static void createUser(String username, String password) {
        WebSocketHandler.sendMessage(String.format("new_user;%s;%s", username, password));
        session_user = username;
    }

    public static void createGame(
            boolean corporate_era,
            boolean prelude,
            boolean colonies,
            boolean venus,
            boolean turmoil,
            boolean extra_corporations,
            Integer map) {
        WebSocketHandler.sendMessage(String.format(Locale.ENGLISH, "create_game;%s;%s;%b;%b;%b;%b;%b;%b;%d",
                session_user,
                session_id,
                corporate_era,
                prelude,
                colonies,
                venus,
                turmoil,
                extra_corporations,
                map));
    }

    public static void joinGame(String game_code) {
        WebSocketHandler.sendMessage(String.format("join_game;%s;%s;%s", session_user, session_id, game_code));
    }

    static void handleLogin(String web_socket_message) {
        getSessionId(web_socket_message);
    }

    static void handleCreation(String web_socket_message) {
        getSessionId(web_socket_message);
    }

    private static void getSessionId(String web_socket_message) {
        session_id = web_socket_message.split(Pattern.quote(";"))[1];
        Log.i("WebSocket", "Session id recieved: " + session_id);
    }
}
