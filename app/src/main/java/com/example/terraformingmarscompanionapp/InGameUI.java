package com.example.terraformingmarscompanionapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.ui.main.GameUiElement;
import com.example.terraformingmarscompanionapp.ui.main.SectionsPagerAdapter;
import com.example.terraformingmarscompanionapp.ui.main.TileMapFragment;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InGameUI extends AppCompatActivity implements GameUiElement {

    public static final String UI_QUEUE_CHECK = "ui";

    Game game;

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;

    // Will probably be used for managing state. Might get removed
    private enum State {
        MAIN_VIEW,
        MAP,
        SEARCH
    }

    private State state = State.MAIN_VIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game_u_i);

        //fixes the appearance of the keyboard on return to the activity
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        game = GameController.getGame();

        //default ui-things
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //Placeholder for map fragment testing. Doesn really function but at least gets the fragment open
        findViewById(R.id.item_1).setOnClickListener(view -> {

            // Temporary undo button

            try {
                GameController.loadGame();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Hex-ui testing code commented out

            /*2TileMapFragment map_fragment = new TileMapFragment(/*add data here/);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction map_transaction = manager.beginTransaction();
            map_transaction.replace(R.id.main_layout, map_fragment, "map");
            state = State.MAP;
            map_transaction.commit();*/
        });

        findViewById(R.id.item_2).setOnClickListener(v -> {
            if (!GameController.checkTurnEligibility()) {
                Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
                return;
            } else if (GameController.getGreeneryRound()) {
                Toast.makeText(getApplicationContext(), "Can only build greeneries!", Toast.LENGTH_SHORT).show();
            }
            startTestingActivity();
        });

        findViewById(R.id.item_3).setOnClickListener(view ->  {
            if (!GameController.checkTurnEligibility()) {
                Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
                return;
            } else if (GameController.getGreeneryRound()) {
                Toast.makeText(getApplicationContext(), "Can only build greeneries!", Toast.LENGTH_SHORT).show();
            }
            startSearchActivity();
        });

        findViewById(R.id.item_4).setOnClickListener(view -> {
            if (!GameController.checkTurnEligibility()) {
                Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
                return;
            }
            GameController.endTurn(this);
            if (game.getServerMultiplayer()) {
                GameActions.sendActionUse(new ActionUsePacket(true, false));
            }
        });

        if (GameController.getGeneration() == 0) {
            GameController.atTurnStart(this);
        }
    }

    @Override
    protected void onResume() {
        Log.i("InGameUI", "Calling on resume");
        GameController.setContextReference(this);
        if (EventScheduler.getStackHasEvents()) {
            EventScheduler.playNextEvent(this);
        }
        super.onResume();
    }

    public void playCorporation()
    {
        Player self = GameController.getCurrentPlayer();

        //inflating layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        //visual editing
        view.setBackgroundColor(Color.TRANSPARENT);

            //removing layout elements not used in the corporation round.
        LinearLayout linearLayout = view.findViewById(R.id.root_linearlayout);

        linearLayout.removeView(view.findViewById(R.id.title2));
        linearLayout.removeView(view.findViewById(R.id.spinner2));

        //finding views
        TextView title = view.findViewById(R.id.title1);
        Spinner spinner = view.findViewById(R.id.spinner1);

        //corporations to arraylist
        HashMap<String, Card> corps_hashmap = game.getCorporations();

        ArrayList<Card> corporations = new ArrayList<>();

        for (Map.Entry<String, Card> corp_entry : corps_hashmap.entrySet()) {
            if (corp_entry.getValue().getOwner() != null) {
                continue;
            }
            corporations.add(corp_entry.getValue());
        }

        //setting up spinner
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

        String player_string = (self.getName() + "'s");

        if (game.getServerMultiplayer()) {
            player_string = "your";
        }

        title.setText("Choose " + player_string + " corporation.");

        //Quickly store context to use inside spinner
        Context self_context = this;

        view.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setting corporation
                EventScheduler.addEvent(new ActionUseEvent(new ActionUsePacket(true, game.modifiers.getPrelude())));
                ((Card) spinner.getSelectedItem()).initializePlayEvents(self);
                EventScheduler.playNextEvent(self_context);
                dialog.dismiss();
            }
        });
    }

    public void playPreludes()
    {
        Player self = GameController.getCurrentPlayer();

        //inflating layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_spinners, null);

        //visual editing
        view.setBackgroundColor(Color.TRANSPARENT);

        //finding views
        TextView title = view.findViewById(R.id.title1);

        Spinner spinner1 = view.findViewById(R.id.spinner1);
        Spinner spinner2 = view.findViewById(R.id.spinner2);

        //getting preludes
        HashMap<String, Card> preludes_hashmap = game.getPreludes();

        ArrayList<Card> preludes = new ArrayList<>();

        for (Map.Entry<String, Card> corp_entry : preludes_hashmap.entrySet())
            preludes.add(corp_entry.getValue());

        //setting up spinners
        //arrayadapter calls the toString method in Card
        ArrayAdapter<Card> adapter = new ArrayAdapter<> (
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

        String player_string = (self.getName() + "'s");

        if (game.getServerMultiplayer()) {
            player_string = "your";
        }

        title.setText("Choose " + player_string + " preludes.");

        //Quickly store context to use inside spinner
        Context self_context = this;

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

                EventScheduler.addEvent(new ActionUseEvent(new ActionUsePacket(true, false)));
                prelude1.initializePlayEvents(GameController.getCurrentPlayer());
                prelude2.initializePlayEvents(GameController.getCurrentPlayer());
                EventScheduler.playNextEvent(self_context);

                GameController.getCurrentPlayer().setPlayedPreludes(true);

                //to the next player
                spinner1.setSelection(0);
                spinner2.setSelection(1);

                dialog.dismiss();
            }
        });
    }

    public void startSearchActivity() {
        if (!GameController.checkTurnEligibility()) {
            Toast.makeText(getApplicationContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    private void startTestingActivity() {

        Toast.makeText(getApplicationContext(), "This should be removed but isn't. Tough luck.", Toast.LENGTH_SHORT).show();
        /*
        Intent intent = new Intent(this, MyActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("player", controller.getCurrentPlayer().getName());
        startActivity(intent);
         */
    }

    public void onBackPressed() {
        switch (state) {
            case MAIN_VIEW:
                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
                break;
            case MAP:
                //TODO this
                Log.i("InGameUI", "Back pressed from map.");
        }
    }

    public void generationEndPrompt() {
        Toast.makeText(getApplicationContext(), "Generation ended.", Toast.LENGTH_SHORT).show();
    }

    //https://stackoverflow.com/questions/5810084/android-alertdialog-single-button
    public void displayPrompt(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> EventScheduler.playNextEvent(this));
        AlertDialog alert = builder.create();
        alert.show();
    }
}