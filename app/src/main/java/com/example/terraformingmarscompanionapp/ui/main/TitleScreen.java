package com.example.terraformingmarscompanionapp.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.GameCreationActivity;
import com.example.terraformingmarscompanionapp.GameCreationServerActivity;
import com.example.terraformingmarscompanionapp.GameJoiningActivity;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;
import com.example.terraformingmarscompanionapp.webSocket.WebSocketHandler;

public class TitleScreen extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "msg";
    public static final String GAME_CODE_INTENT = "code";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

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

    public void hostServerGame(View view) {
        if (UserActions.getUser() == null) {
            Toast.makeText(getApplicationContext(), "Please log in or sign up first!", Toast.LENGTH_SHORT).show();
        } else {
            intent = new Intent(this, GameCreationServerActivity.class);
            startActivity(intent);
        }
    }

    //http://www.androidsnippets.com/prompt-user-input-with-an-alertdialog.html
    public void joinServerGame(View view) {
        if (UserActions.getUser() == null) {
            Toast.makeText(getApplicationContext(), "Please log in or sign up first!", Toast.LENGTH_SHORT).show();
            return;
        }

        intent = new Intent(this, GameJoiningActivity.class);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Join a game");
        alert.setMessage("Enter the game code");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Join", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                UserActions.checkCode(value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (UserActions.game_code_valid) {
                    intent.putExtra(GAME_CODE_INTENT, value);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid game code", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }

    public void logIn(View view) {
        initializeWebSocket();
        intent = new Intent(this, LoginActivity.class);
        if (UserActions.getUser() == null) {
            intent.putExtra(EXTRA_MESSAGE, "Log in");
        } else {
            intent.putExtra(EXTRA_MESSAGE, "Change user");
        }
        startActivity(intent);
    }

    public void signUp(View view) {
        initializeWebSocket();
        if (UserActions.getUser() == null) {
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "Sign up");
            startActivity(intent);
        } else {
            UserActions.logoutUser();
            TextView user_name = findViewById(R.id.textView);
            user_name.setText("Not logged in!");

            Button sign_up = findViewById(R.id.button4);
            Button log_in = findViewById(R.id.button5);

            sign_up.setText("Sign up");
            log_in.setText("Log in");
        }
    }

    private void initializeWebSocket() {
        WebSocketHandler.createWebSocketClient();
    }
}
