package com.example.terraformingmarscompanionapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.CardsBoughtActivity;
import com.example.terraformingmarscompanionapp.ui.main.PlayerChoiceActivity;
import com.example.terraformingmarscompanionapp.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InGameUI extends AppCompatActivity {

    static boolean is_first_run = true;

    Game game;
    GameController controller;

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_u_i);

        //korjaa näppäimistön satunnaisen ilmestymisen
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        controller = GameController.getInstance();
        controller.setContext(this);
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

        findViewById(R.id.item_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTestingActivity();
            }
        });

        findViewById(R.id.item_3).setOnClickListener(view -> startSearchActivity());

        findViewById(R.id.item_4).setOnClickListener(view -> controller.endTurn());

        //tehdään vain kerran
        if (is_first_run)
        {
            is_first_run = false;

            corporationRound();
        }
    }

    //avaa dialogin ja laittaa pelaajien korporaatiot valinnan mukaisiksi
    //tässä vaiheessa aika paljon toistoa
    //tehty toistaen alku koska kuitenkin niin paljon eri asioita että on järkevää tehdä erillisiksi
    private void corporationRound()
    {
        List<Player> players = controller.getPlayers();

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        //visuaalinen muokkaus
        view.setBackgroundColor(Color.TRANSPARENT);

            //corporationroundissa käyttämättömien poisto.
        LinearLayout linearLayout = view.findViewById(R.id.root_linearlayout);

        linearLayout.removeView(view.findViewById(R.id.title2));
        linearLayout.removeView(view.findViewById(R.id.spinner2));

        //findviewbyid't
        TextView title = view.findViewById(R.id.title1);
        Spinner spinner = view.findViewById(R.id.spinner1);

        //korporaatioiden hankkiminen arraylistiin
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

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(2*width/3, WindowManager.LayoutParams.WRAP_CONTENT);

        title.setText("Choose " + players.get(0).getName() + "'s corporation.");

        //eri pelaajien läpi menemisen logiikka onclicklistenerissä
        //voi uudelleenkirjottaa
        view.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
            private int player_index = 0;

            @Override
            public void onClick(View v)
            {
                //korporaation asettaminen
                ((Card) spinner.getSelectedItem()).onPlay(players.get(player_index));

                //seuraavaan pelaajaan siirtyminen
                spinner.setSelection(0);
                player_index++;

                //viimeisen valinnan ohessa dialogi suljetaan
                if (player_index == players.size()) {
                    dialog.dismiss();
                    if (game.modifiers.getPrelude()) {
                        preludeRound();
                    }
                    GameController.getInstance().gameUpdate();
                    GameController.getInstance().atGenerationStart();
                    return;
                }

                title.setText("Choose " + players.get(player_index).getName() + "'s corporation.");
            }
        });
    }

    private void preludeRound()
    {
        List<Player> players = controller.getPlayers();

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        //visuaalinen muokkaus
        view.setBackgroundColor(Color.TRANSPARENT);

        //findviewbyid't
        TextView title = view.findViewById(R.id.title1);

        Spinner spinner1 = view.findViewById(R.id.spinner1);
        Spinner spinner2 = view.findViewById(R.id.spinner2);

        //preludien hankkiminen arraylistiin
        HashMap<String, Card> preludes_hashmap = game.getPreludes();

        ArrayList<Card> preludes = new ArrayList<>();

        for (Map.Entry<String, Card> corp_entry : preludes_hashmap.entrySet())
            preludes.add(corp_entry.getValue());

        //spinnerin valmistaminen
        //arrayadapter kutsuu toString -metodia
        ArrayAdapter<Card> adapter = new ArrayAdapter<Card> (
                this, android.R.layout.simple_spinner_item, preludes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(2*width/3, WindowManager.LayoutParams.WRAP_CONTENT);

        title.setText("Choose " + players.get(0).getName() + "'s preludes.");

        //eri pelaajien läpi menemisen logiikka onclicklistenerissä
        //voi uudelleenkirjottaa
        view.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
            private int player_index = 0;

            @Override
            public void onClick(View v)
            {
                Card prelude1 = (Card) spinner1.getSelectedItem();
                Card prelude2 = (Card) spinner2.getSelectedItem();

                if (prelude1 == null || prelude2 == null) {
                    return;
                }

                if (prelude1 == prelude2) {
                    return;
                }

                //preludien asettaminen
                players.get(player_index).addPrelude((Card) spinner1.getSelectedItem());
                players.get(player_index).addPrelude((Card) spinner2.getSelectedItem());

                //seuraavaan pelaajaan siirtyminen
                spinner1.setSelection(0);
                spinner2.setSelection(1);

                player_index++;

                //viimeisen valinnan ohessa dialogi suljetaan
                if (player_index == players.size()) {
                    dialog.dismiss();
                    return;
                }
                title.setText("Choose " + players.get(player_index).getName() + "'s corporation.");
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

    private void startTestingActivity() {
        Intent intent = new Intent(this, CardsBoughtActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("player", controller.getCurrentPlayer().getName());
        startActivity(intent);
    }

    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void onGenerationEnd() {
        Toast.makeText(getApplicationContext(), "Generation ended.", Toast.LENGTH_SHORT).show();
    }

    public void onTurnChange(String player_name) {
        Toast.makeText(getApplicationContext(), String.format("%s's turn", player_name), Toast.LENGTH_SHORT).show();
    }

    //https://stackoverflow.com/questions/5810084/android-alertdialog-single-button
    public void cardDrawPrompt(Integer amount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format("Please draw %d card(s)", amount))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void cardSwapPrompt(Integer amount) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(String.format("Please swap up to %d card(s)", amount))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}