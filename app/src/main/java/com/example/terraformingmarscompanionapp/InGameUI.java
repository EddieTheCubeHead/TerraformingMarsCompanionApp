package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
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

        //pelin nouto intentistä
        game = (Game) getIntent().getSerializableExtra("game");

        gameController = new GameController(game);

        //aika lailla placeholder
        findViewById(R.id.item_1).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                //tähän toiminnallisuus
                Toast.makeText(getApplicationContext(), "Lisää toiminnallisuus InGameUI -luokassa", Toast.LENGTH_LONG).show();
            }
        });

        //aika lailla placeholder
        findViewById(R.id.item_2).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                //tähän toiminnallisuus
                Toast.makeText(getApplicationContext(), "Lisää toiminnallisuus InGameUI -luokassa", Toast.LENGTH_LONG).show();
            }
        });

        //aika lailla placeholder
        findViewById(R.id.item_3).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Testausaktiviteetti aloitetaan.", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    //TODO back nappiin toiminnallisuus, vaikkapa ylös scrollaus
    @Override public void onBackPressed() {} //back -nappi ei tee nyt mitään.
}