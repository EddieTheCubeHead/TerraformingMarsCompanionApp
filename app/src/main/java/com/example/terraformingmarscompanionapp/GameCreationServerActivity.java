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
import com.example.terraformingmarscompanionapp.game.GameModifiers;
import com.example.terraformingmarscompanionapp.game.tileSystem.GameMap;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.GameSetting;
import com.example.terraformingmarscompanionapp.webSocket.ServerSetupScreen;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;

import java.util.ArrayList;


public class GameCreationServerActivity extends AppCompatActivity implements ServerSetupScreen {
    ArrayList<String> player_names = new ArrayList<>();
    GameMap map = GameMap.THARSIS;
    GameModifiers modifiers;
    String game_code_string = null;

    TextView game_code;
    TextView textview_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creation_server);

        textview_names = findViewById(R.id.name_textview);

        game_code = findViewById(R.id.name_edittext);

        game_code.setText("Waiting for server");

        modifiers = new GameModifiers();

        GameActions.setContext(this);

        Switch switch_corporate_era = findViewById(R.id.switch_corporate_era);
        Switch switch_prelude = findViewById(R.id.switch_prelude);
        Switch switch_colonies = findViewById(R.id.switch_colonies);
        Switch switch_venus = findViewById(R.id.switch_venus);
        Switch switch_turmoil = findViewById(R.id.switch_turmoil);
        Switch switch_extra_corporations = findViewById(R.id.switch_extra_corporations);


        switch_corporate_era.setOnCheckedChangeListener((buttonView, isChecked) -> {
            modifiers.setCorporateEra(isChecked);
            GameActions.sendSettingChange(GameSetting.CORPORATE_ERA, isChecked);
        });

        switch_prelude.setOnCheckedChangeListener((buttonView, isChecked) -> {
            modifiers.setPrelude(isChecked);
            GameActions.sendSettingChange(GameSetting.PRELUDE, isChecked);
        });

        switch_colonies.setOnCheckedChangeListener((buttonView, isChecked) -> {
            modifiers.setColonies(isChecked);
            GameActions.sendSettingChange(GameSetting.COLONIES, isChecked);
        });

        switch_venus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            modifiers.setVenus(isChecked);
            GameActions.sendSettingChange(GameSetting.VENUS, isChecked);
        });

        switch_turmoil.setOnCheckedChangeListener((buttonView, isChecked) -> {
            modifiers.setTurmoil(isChecked);
            GameActions.sendSettingChange(GameSetting.TURMOIL, isChecked);
        });

        switch_extra_corporations.setOnCheckedChangeListener((buttonView, isChecked) -> modifiers.setExtraCorporations(isChecked));

        Button button_start = findViewById(R.id.button_start);

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
        playerJoined(UserActions.getUser());
    }

    public void startInGameUI() {
        if (player_names.size() == 1) {
            Toast.makeText(getApplicationContext(), "Wait for others to join!", Toast.LENGTH_SHORT).show();
            return;
        }

        GameActions.sendGameStart();

        Game game = new Game(modifiers, true, map);

        GameController.initGameController(game, true, player_names);
        GameController.setSelfPlayer(GameController.getPlayer(UserActions.getUser()));

        Intent intent = new Intent(this, InGameUi.class);
        startActivity(intent);
    }

    @Override
    public void playerJoined(String player_name) {
        player_names.add(player_name);

        // Lambda to get the code to run on the UI thread when calling from WebSocket
        new Thread(() -> runOnUiThread(() -> textview_names.append("\n" + player_name))).start();
    }

    @Override
    public void startGame() {
    }

    @Override
    public void settingChanged(GameSetting setting, Boolean value) {
        switch (setting) {
            case VENUS:
                modifiers.setVenus(value);
                break;
            case PRELUDE:
                modifiers.setPrelude(value);
                break;
            case TURMOIL:
                modifiers.setTurmoil(value);
                break;
            case COLONIES:
                modifiers.setColonies(value);
                break;
            case CORPORATE_ERA:
                modifiers.setCorporateEra(value);
                break;
            case MUST_MAX_VENUS:
                modifiers.setMustMaxVenus(value);
                break;
            case EXTRA_CORPORATIONS:
                modifiers.setExtraCorporations(value);
                break;
            case TURMOIL_TERRAFORMING_REVISION:
                modifiers.setTurmoilTerraformingRevision(value);
                break;
            case WORLD_GOVERNMENT_TERRAFORMING:
                modifiers.setWorldGovernmentTerraformin(value);
                break;
            default:
                break;
        }
    }

    @Override
    public void mapChanged(GameMap value) {
        map = value;
    }
}