package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.main.TitleScreen;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.GameSetting;
import com.example.terraformingmarscompanionapp.webSocket.ServerSetupScreen;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;

import java.util.ArrayList;


public class GameJoiningActivity extends AppCompatActivity implements ServerSetupScreen {
    ArrayList<String> player_names = new ArrayList<>();
    boolean corporate_era = false;
    boolean prelude = false;
    boolean colonies = false;
    boolean venus = false;
    boolean turmoil = false;
    boolean extra_corporations = false;
    boolean must_max_venus = false;
    boolean world_government_terraforming = false;
    boolean turmoi_terraforming_revision = true;
    Integer map = 0;
    String game_code_string = null;

    TextView game_code;
    TextView textview_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_joining);

        GameActions.setSetupScreen(this);

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

    @Override
    public void playerJoined(String player_name) {
        player_names.add(player_name);

        //Lambda-esitys koodin ajamiseen UI-threadilla, jotta ohjelma ei kaatuisi kun toinen thread kutsuu tätä
        //Tämä melko yleistä WebSocketin kanssa
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
                venus = value;
                break;
            case PRELUDE:
                prelude = value;
                break;
            case TURMOIL:
                turmoil = value;
                break;
            case COLONIES:
                colonies = value;
                break;
            case CORPORATE_ERA:
                corporate_era = value;
                break;
            case MUST_MAX_VENUS:
                must_max_venus = value;
                break;
            case EXTRA_CORPORATIONS:
                extra_corporations = value;
                break;
            case TURMOIL_TERRAFORMING_REVISION:
                turmoi_terraforming_revision = value;
                break;
            case WORLD_GOVERNMENT_TERRAFORMING:
                world_government_terraforming = value;
                break;
            default:
                break;
        }
    }

    @Override
    public void mapChanged(Integer value) {
        map = value;
    }
}
