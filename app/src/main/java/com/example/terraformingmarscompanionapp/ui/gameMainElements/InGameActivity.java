package com.example.terraformingmarscompanionapp.ui.gameMainElements;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InGameActivity extends AppCompatActivity {

    private FrameLayout main_frame;
    private TabMenuFragment main_menu;
    private CardListFragment card_search;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        main_menu = new TabMenuFragment();
        card_search = new CardListFragment();

        main_frame = findViewById(R.id.main_frame);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(main_frame.getId(), main_menu);
        transaction.commit();

        GameController.setContextReference(this);
        game = GameController.getGame();

        if (GameController.getGeneration() == 0) {
            GameController.atTurnStart(this);
        }
    }

    void startSearchFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(main_frame.getId(), card_search);
        transaction.commit();
    }

    public void displayPrompt(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, id) -> EventScheduler.playNextEvent(this));
        AlertDialog alert = builder.create();
        alert.show();
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
}