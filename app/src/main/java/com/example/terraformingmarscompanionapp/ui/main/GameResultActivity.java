package com.example.terraformingmarscompanionapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An activity to host game results. Keeping this barebones until the UI revision.
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public class GameResultActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        TextView results = findViewById(R.id.resultTextView);

        StringBuilder result_string_builder = new StringBuilder();

        List<Player> players = GameController.getPlayers();

        Collections.sort(players, Player.pointComparator);

        for (int i = 0; i < players.size(); i++) {
            result_string_builder.append(String.format("%d: %s, %d points\n", i + 1, players.get(i).getName(), players.get(i).getVictoryPoints()));
        }

        results.setText(result_string_builder.toString());
    }
}
