package com.example.terraformingmarscompanionapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.SectionsPagerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InGameUI extends AppCompatActivity {

    Game game;
    GameController controller;

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_u_i);

        controller = GameController.getInstance();
        game = controller.getGame();

        //default ui-juttuja
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

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
                Toast.makeText(getApplicationContext(), "No functionality.", Toast.LENGTH_SHORT).show()
        );

        findViewById(R.id.item_3).setOnClickListener(view -> startSearchActivity());

        findViewById(R.id.item_4).setOnClickListener(view -> controller.endTurn());

        findViewById(R.id.item_4).setOnLongClickListener(v -> {
            controller.endGeneration();
            return true;
        });


        boolean serverGame = false;

        if (serverGame) {
        } else
            corporationRound();

    }


    //avaa dialogin ja laittaa pelaajien korporaatiot valinnan mukaisikasi
    private void corporationRound() {
        List<Player> players = controller.getPlayers();

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_startchoices, null);

        //visuaalinen muokkaus
        view.setBackgroundColor(Color.TRANSPARENT);

        //findviewbyid't
        TextView title = view.findViewById(R.id.first_round_title);
        Spinner spinner = view.findViewById(R.id.first_round_spinner);

        //korporaatioiden hankkiminen
        HashMap<String, Card> corps_hashmap = game.getCorporations();

        ArrayList<Card> corporations = new ArrayList<>();

        for (Map.Entry<String, Card> corp_entry : corps_hashmap.entrySet())
            corporations.add(corp_entry.getValue());

        //spinnerin valmistaminen
        //arrayadapter kutsuu toString -metodia kortissa.
        ArrayAdapter<Card> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, corporations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        title.setText("Choose " + players.get(0).getName() + "'s corporation.");

        //eri pelaajien läpi menemisen logiikka onclicklistenerissä
        //voi uudelleenkirjottaa
        view.findViewById(R.id.first_round_confirm).setOnClickListener(new View.OnClickListener() {
            private int player_index = 0;

            @Override
            public void onClick(View v) {
                Player player = players.get(player_index);

                title.setText("Choose " + player.getName() + "'s corporation.");


                //seuraavaan pelaajaan siirtyminen
                player.setCorporation((Card) spinner.getSelectedItem());
                spinner.setSelection(0);

                player_index++;

                //viimeisen valinnan ohessa suljetaan dialogi
                if (player_index == players.size())
                    dialog.dismiss();
            }
        });
    }

    private void startSearchActivity() {
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