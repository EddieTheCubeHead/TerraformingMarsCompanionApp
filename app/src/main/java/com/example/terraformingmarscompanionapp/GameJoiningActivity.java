package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.main.TitleScreen;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;

import java.util.ArrayList;


public class GameJoiningActivity extends AppCompatActivity {
    ArrayList<String> player_names = new ArrayList<>();
    boolean corporate_era = false;
    boolean prelude = false;
    boolean colonies = false;
    boolean venus = false;
    boolean turmoil = false;
    boolean extra_corporations = false;
    Integer map = 0;
    String game_code_string = null;

    TextView game_code;
    TextView textview_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_joining);

        //textview
        textview_names = findViewById(R.id.name_textview);

        //Pelin koodi
        game_code = findViewById(R.id.name_edittext);
        game_code_string = getIntent().getStringExtra(TitleScreen.GAME_CODE_INTENT);

        UserActions.joinGame(game_code_string);

        game_code.setText(game_code_string);

        game_code.setText(String.format("Game code: %s", game_code_string));
    }

    public void startInGameUI() {
        if (player_names.size() == 0) {
            Toast.makeText(getApplicationContext(), "Enter names first!", Toast.LENGTH_SHORT).show();
            return;
        }

        Game game = new Game(player_names,
                corporate_era,
                prelude,
                colonies,
                venus,
                turmoil,
                extra_corporations,
                false,
                false,
                true,
                true,
                map);

        GameController.makeInstance(game);

        Intent intent = new Intent(this, InGameUI.class);
        startActivity(intent);
    }
}
