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

    public static String getUser() {return session_user;}

    //ui:n kutsumat sisäänkirjautumisen onnistuneisuuden merkit.
    public static Boolean successful_login = false;
    public static String message = "";

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

    public static void logoutUser() {
        WebSocketHandler.sendMessage(String.format("logout;%s", session_id));
        session_user = null;
        session_id = null;
    }

    public static void createGame() {
        WebSocketHandler.sendMessage(String.format(Locale.ENGLISH, "create_game;%s;%s",
                session_user,
                session_id));
    }

    public static void joinGame(String game_code) {
        if (session_user == null | session_id == null) {
            //TODO error handling
            return;
        }
        WebSocketHandler.sendMessage(String.format("join_game;%s;%s;%s", session_user, session_id, game_code));
    }

    static void handleLogin(String web_socket_message) {
        getSessionId(web_socket_message);
        successful_login = true;
    }

    static void handleCreation(String web_socket_message) {
        getSessionId(web_socket_message);
        successful_login = true;
    }

    private static void getSessionId(String web_socket_message) {
        session_id = web_socket_message.split(Pattern.quote(";"))[1];
        Log.i("WebSocket", "Session id recieved: " + session_id);
    }
}
