package com.example.terraformingmarscompanionapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.webSocket.WebSocketHandler;

import java.util.ArrayList;

public class GameCreationActivity extends AppCompatActivity
{
    ArrayList<String> player_names = new ArrayList<>();
    boolean hellas_elysium = false;
    boolean corporate_era = false;
    boolean prelude = false;
    boolean colonies = false;
    boolean venus = false;
    boolean turmoil = false;
    boolean extra_corporations = false;
    Integer map = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creation);

        //WebSocketin implementaation testit on tässä. Pitäisi olla poistettu mergeen mennessä, mutta tiedä häntä.
        WebSocketHandler.createWebSocketClient();

        //textview
        final TextView textview_names = findViewById(R.id.textView);

        //EditText
        final EditText edittext_name = findViewById(R.id.editText);

        //Switchit
        Switch switch_hellas_elysiym = findViewById(R.id.switch_elysium);
        Switch switch_corporate_era = findViewById(R.id.switch_corporate_era);
        Switch switch_prelude = findViewById(R.id.switch_prelude);
        Switch switch_colonies = findViewById(R.id.switch_colonies);
        Switch switch_venus = findViewById(R.id.switch_venus);
        Switch switch_turmoil = findViewById(R.id.switch_turmoil);
        Switch switch_extra_corporations = findViewById(R.id.switch_extra_corporations);

        //buttonit
        ImageButton button_add = findViewById(R.id.imageButton);
        Button button_start = findViewById(R.id.button_start);

        //EditTextin listener
        //toistaiseksi ei mitään

        //switchien listenerit, vaihtaa booleaneja
        switch_hellas_elysiym.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { hellas_elysium = isChecked; }
        });
        switch_corporate_era.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { corporate_era = isChecked; }
        });
        switch_prelude.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { prelude = isChecked; }
        });
        switch_colonies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { colonies = isChecked; }
        });
        switch_venus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { venus = isChecked; }
        });
        switch_turmoil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { turmoil = isChecked; }
        });
        switch_extra_corporations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { extra_corporations = isChecked; }
        });

        //nappien listenerit
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                String name = edittext_name.getText().toString();
                player_names.add(name.trim());
                textview_names.append(name+"\n");
                edittext_name.setText("");
            }
        });
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startInGameUI();
            }
        });


    }


    public void startInGameUI()
    {
        Game game = new Game(player_names,
                hellas_elysium, corporate_era, prelude, colonies, venus, turmoil, extra_corporations,
                map);

        GameController gameController = GameController.makeInstance(game);

        Intent intent = new Intent(this, InGameUI.class);
        startActivity(intent);
    }


}
