package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
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
    GameController controller;

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_u_i);

        //default ui-juttuja
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        controller = GameController.getInstance();

        //KAIKKI LISTENERIT PLACEHOLDEREITA ATM
        findViewById(R.id.item_1).setOnClickListener(view -> {

            /* Tarkistetaan onko serveripeli, ja jos on, onko clientin vuoro. Tämän koodinpätkän pitäisi
             * olla kaikissa vuoroista riippuvaisissa toiminnoissa. */
            if (!controller.checkTurnEligibility()) {
                Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
                return;
            }
            Player current_player = controller.getCurrentPlayer();

            current_player.changeMoney(10);

            Toast.makeText(getApplicationContext(),
                    current_player.getName() + ": " + current_player.getMoney() + "c",
                    Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.item_2).setOnClickListener(view ->
                System.out.println("ei toimintoa")
        );

        findViewById(R.id.item_3).setOnClickListener(view -> startSearchActivity());

        findViewById(R.id.item_4).setOnClickListener(view -> controller.endTurn(getApplicationContext()));
        
        findViewById(R.id.item_4).setOnLongClickListener(v -> {
            controller.endGeneration(getApplicationContext());
            return true;
        });
    }

    private void startSearchActivity()
    {
        /* Tarkistetaan onko serveripeli, ja jos on, onko clientin vuoro. Tämän koodinpätkän pitäisi
         * olla kaikissa vuoroista riippuvaisissa toiminnoissa. */
        if (!controller.checkTurnEligibility()) {
            Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}