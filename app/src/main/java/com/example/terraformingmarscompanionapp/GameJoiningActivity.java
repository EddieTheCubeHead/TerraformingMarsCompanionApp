package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.GameModifiers;
import com.example.terraformingmarscompanionapp.game.tileSystem.GameMap;
import com.example.terraformingmarscompanionapp.ui.main.TitleScreen;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.GameSetting;
import com.example.terraformingmarscompanionapp.webSocket.ServerSetupScreen;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;

import java.util.ArrayList;


public class GameJoiningActivity extends AppCompatActivity implements ServerSetupScreen {
    ArrayList<String> player_names = new ArrayList<>();
    GameMap map = GameMap.THARSIS;
    GameModifiers modifiers;
    String game_code_string = null;

    TextView game_code;
    TextView textview_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_joining);

        GameActions.setContext(this);
        modifiers = new GameModifiers();

        //textview
        textview_names = findViewById(R.id.name_textview);

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

        Game game = new Game(modifiers, true, map);

        GameController.initGameController(game, false, player_names);
        GameController.setSelfPlayer(GameController.getPlayer(UserActions.getUser()));

        Intent intent = new Intent(this, InGameUI.class);
        startActivity(intent);
    }

    @Override
    public void playerJoined(String player_name) {
        player_names.add(player_name);

        // Lambda-expression to run the code on UI thread to prevent crashing when calling from
        // WebSocket thread
        new Thread(() -> runOnUiThread(() -> textview_names.append("\n" + player_name))).start();
    }

    @Override
    public void startGame() {
        //Lambda-esitys koodin ajamiseen UI-threadilla, jotta ohjelma ei kaatuisi kun toinen thread kutsuu tätä
        //Tämä melko yleistä WebSocketin kanssa
        new Thread(() -> runOnUiThread(this::startInGameUI)).start();
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
