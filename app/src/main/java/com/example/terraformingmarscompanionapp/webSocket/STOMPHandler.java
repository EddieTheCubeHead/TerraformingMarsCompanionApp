package com.example.terraformingmarscompanionapp.webSocket;

import android.annotation.SuppressLint;
import android.util.Log;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

//https://github.com/NaikSoftware/StompProtocolAndroid
public class STOMPHandler {
    private static final String STOMP_URI = "ws://10.0.2.2:8080/tfmca/websocket";
    private static final String TAG = "STOMP";
    private static StompClient mStompClient;

    @SuppressLint("CheckResult")
    public static void initStomp() {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, STOMP_URI);
        mStompClient.connect();
        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.i(TAG, "Stomp connection opened");
                    break;

                case ERROR:
                    Log.i(TAG, "Error", lifecycleEvent.getException());
                    break;

                case CLOSED:
                    Log.i(TAG, "Stomp connection closed");
                    break;

                case FAILED_SERVER_HEARTBEAT:
                    Log.i(TAG, "Server hearbeat failed");
                    break;
            }
        });
    }

    @SuppressLint("CheckResult")
    public static void testStomp() {
        mStompClient.topic("/topic/greetings").subscribe(topicMessage -> {
            Log.i(TAG, topicMessage.getPayload());
        }, throwable -> {
            Log.i(TAG, "Error on test subscription");
        });

        mStompClient.send("/topic/hello-msg-mapping", "My first STOMP message!").subscribe();
    }

    /* ...

    mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://10.0.2.2:8080/example-endpoint/websocket");
    mStompClient.connect();

    mStompClient.topic("/topic/greetings").subscribe(topicMessage -> {
        Log.d(TAG, topicMessage.getPayload());
        });

    mStompClient.send("/topic/hello-msg-mapping", "My first STOMP message!").subscribe();

    // ...

    mStompClient.disconnect();

     */
}
