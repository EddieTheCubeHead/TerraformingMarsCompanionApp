package com.example.terraformingmarscompanionapp.webSocket;

public class UserActions {

    //Session tunnistamiseen. Vakavammassa toteutetussa tarvittaisiin syvällisempi järjestelmä.
    private static String session_id = null;
    private static String session_user = null;

    //Login, ottaa käyttäjänimen ja salasanan.
    public static void loginUser(String username, String password) {
        WebSocketHandler.sendMessage(String.format("login;%s;%s", username, password));
    }

    //Uuden käyttäjän luonti, ottaa käyttäjänimen ja salasanan.
    public static void createUser(String username, String password) {
        WebSocketHandler.sendMessage(String.format("new_user;%s;%s", username, password));
    }

    static void handleLogin(String web_socket_message) {

    }

    static void handleCreation(String web_socket_message) {

    }
}
