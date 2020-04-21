package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class InGameUI extends AppCompatActivity {

    Game game;
    GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_u_i);

        //default ui-juttuja
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        gameController = GameController.getInstance();

        //KAIKKI LISTENERIT PLACEHOLDEREITA ATM
        findViewById(R.id.item_1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                /* Tarkistetaan onko serveripeli, ja jos on, onko clientin vuoro. Tämän koodinpätkän pitäisi
                 * olla kaikissa vuoroista riippuvaisissa toiminnoissa. */
                if (!gameController.checkTurnEligibility()) {
                    Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Player current_player = gameController.getCurrentPlayer();

                current_player.changeMoney(10);

                Toast.makeText(getApplicationContext(),
                        current_player.getName() + ": " + current_player.getMoney() + "c",
                        Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.item_2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "lisää toiminnallisuus ingameui-luokassa", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.item_3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                startSearchActivity();
            }
        });

        findViewById(R.id.item_4).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                gameController.endTurn();
            }
        });
    }

    private void startSearchActivity()
    {
        /* Tarkistetaan onko serveripeli, ja jos on, onko clientin vuoro. Tämän koodinpätkän pitäisi
         * olla kaikissa vuoroista riippuvaisissa toiminnoissa. */
        if (!gameController.checkTurnEligibility()) {
            Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    //TODO foldaus

    //TODO back nappiin toiminnallisuus, vaikkapa ylös scrollaus
    @Override public void onBackPressed() {} //back -nappi ei tee nyt mitään.
}