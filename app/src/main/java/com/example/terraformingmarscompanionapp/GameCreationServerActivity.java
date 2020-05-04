package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;

import java.util.ArrayList;


public class GameCreationServerActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_game_creation_server);

        //textview
        textview_names = findViewById(R.id.name_textview);

        //Pelin koodi
        game_code = findViewById(R.id.name_edittext);

        game_code.setText("Waiting for server");

        //Switchit
        Switch switch_corporate_era = findViewById(R.id.switch_corporate_era);
        Switch switch_prelude = findViewById(R.id.switch_prelude);
        Switch switch_colonies = findViewById(R.id.switch_colonies);
        Switch switch_venus = findViewById(R.id.switch_venus);
        Switch switch_turmoil = findViewById(R.id.switch_turmoil);
        Switch switch_extra_corporations = findViewById(R.id.switch_extra_corporations);

        //switchien listenerit, vaihtaa booleaneja
        switch_corporate_era.setOnCheckedChangeListener((buttonView, isChecked) -> corporate_era = isChecked);
        switch_prelude.setOnCheckedChangeListener((buttonView, isChecked) -> prelude = isChecked);
        switch_colonies.setOnCheckedChangeListener((buttonView, isChecked) -> colonies = isChecked);
        switch_venus.setOnCheckedChangeListener((buttonView, isChecked) -> venus = isChecked);
        switch_turmoil.setOnCheckedChangeListener((buttonView, isChecked) -> turmoil = isChecked);
        switch_extra_corporations.setOnCheckedChangeListener((buttonView, isChecked) -> extra_corporations = isChecked);

        //buttonit
        Button button_start = findViewById(R.id.button_start);

        //nappien listenerit
        button_start.setOnClickListener(v -> startInGameUI());

        UserActions.createGame();

        while (game_code_string == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game_code_string = GameActions.getGameCode();
        }
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