package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.GameSetting;
import com.example.terraformingmarscompanionapp.webSocket.ServerSetupScreen;
import com.example.terraformingmarscompanionapp.webSocket.UserActions;

import java.util.ArrayList;


public class GameCreationServerActivity extends AppCompatActivity implements ServerSetupScreen {
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
        setContentView(R.layout.activity_game_creation_server);

        //textview
        textview_names = findViewById(R.id.name_textview);

        //Pelin koodi
        game_code = findViewById(R.id.name_edittext);

        game_code.setText("Waiting for server");

        GameActions.setContext(this);

        //Switchit
        Switch switch_corporate_era = findViewById(R.id.switch_corporate_era);
        Switch switch_prelude = findViewById(R.id.switch_prelude);
        Switch switch_colonies = findViewById(R.id.switch_colonies);
        Switch switch_venus = findViewById(R.id.switch_venus);
        Switch switch_turmoil = findViewById(R.id.switch_turmoil);
        Switch switch_extra_corporations = findViewById(R.id.switch_extra_corporations);

        //switchien listenerit, vaihtaa booleaneja

        //(buttonView, isChecked) -> (corporate_era = isChecked; GameActions.sendSettingChange(GameSetting.CORPORATE_ERA, corporate_era))

        switch_corporate_era.setOnCheckedChangeListener((buttonView, isChecked) -> {
            corporate_era = isChecked;
            System.out.println("Corporate era value: " + corporate_era + ", " + corporate_era);
            GameActions.sendSettingChange(GameSetting.CORPORATE_ERA, corporate_era);
        });

        switch_prelude.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prelude = isChecked;
            GameActions.sendSettingChange(GameSetting.PRELUDE, prelude);
        });
        switch_colonies.setOnCheckedChangeListener((buttonView, isChecked) -> {
            colonies = isChecked;
            GameActions.sendSettingChange(GameSetting.COLONIES, colonies);
        });
        switch_venus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            venus = isChecked;
            GameActions.sendSettingChange(GameSetting.VENUS, venus);
        });
        switch_turmoil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                turmoil = isChecked;
                GameActions.sendSettingChange(GameSetting.TURMOIL, turmoil);
            }
        });
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
        playerJoined(UserActions.getUser());
    }

    public void startInGameUI() {
        if (player_names.size() == 1) {
            Toast.makeText(getApplicationContext(), "Wait for others to join!", Toast.LENGTH_SHORT).show();
            return;
        }

        GameActions.sendGameStart();

        Game game = new Game(player_names,
                corporate_era,
                prelude,
                colonies,
                venus,
                turmoil,
                extra_corporations,
                world_government_terraforming,
                must_max_venus,
                turmoi_terraforming_revision,
                true,
                map);

        GameController.makeInstance(game).setSelfPlayer(game.getPlayer(UserActions.getUser()));

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