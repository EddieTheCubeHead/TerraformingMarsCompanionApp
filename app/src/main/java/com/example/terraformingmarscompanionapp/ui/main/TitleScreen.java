package com.example.terraformingmarscompanionapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.GameCreationActivity;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;
import com.example.terraformingmarscompanionapp.webSocket.WebSocketHandler;

public class TitleScreen extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "msg";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        WebSocketHandler.createWebSocketClient();

        TextView user_name = findViewById(R.id.textView);
        user_name.setText("Not logged in!");

        if (UserActions.getUser() != null) {
            user_name.setText(UserActions.getUser());
            Button sign_up = findViewById(R.id.button4);
            Button log_in = findViewById(R.id.button5);

            sign_up.setText("Log out");
            log_in.setText("Change user");
        }
    }



    public void startSoloGame(View view) {
        //TODO tämä
    }

    public void startHotSeat(View view) {
        intent = new Intent(this, GameCreationActivity.class);
        startActivity(intent);
    }

    public void startServerGame(View view) {
        if (UserActions.getUser() == null) {
            Toast.makeText(getApplicationContext(), "Please log in or sign up first!", Toast.LENGTH_SHORT).show();
        } else {
            //TODO tämä
        }
    }

    public void logIn(View view) {
        if (UserActions.getUser() == null) {
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "Log in");
            startActivity(intent);
        }
    }

    public void signUp(View view) {
        if (UserActions.getUser() == null) {
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "Sign up");
            startActivity(intent);
        }
    }
}
