package com.example.terraformingmarscompanionapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.terraformingmarscompanionapp.ui.main.SectionsPagerAdapter;

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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Snackbar.make(view, "Testausaktiviteetti aloitetaan.", Snackbar.LENGTH_LONG).show();
                startSearchActivity();
            }
        });
    }

    //tästä voi vaihtaa aktiviteettia johon fab vie.
    private void startSearchActivity()
    {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

    //TODO back nappiin päänäkymän aloittaminen
    @Override public void onBackPressed() {} //back -nappi ei tee nyt mitään.
}