package com.example.terraformingmarscompanionapp.ui.main;

import android.app.AlertDialog;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActivityDialogSearch extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
{
    public static final String CARD_INTENT = "filter_by_player";
    public static final String FILTER_NAME = "card_owner";

    AlertDialog dialog;

    private SearchView searchview;

    private HashMap<String, Card> deck;
    private ArrayList<Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;
    private ArrayList<Card.Type> valid_types = new ArrayList<>(Arrays.asList(Card.Type.GREEN, Card.Type.RED, Card.Type.BLUE));
    //todo tarkat valid types

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);



        GameController controller = GameController.getInstance();
        Game game = controller.getGame();
        deck = game.getDeck();

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.dialog_search, null);

        //findviewbyid't
        searchview = view.findViewById(R.id.searchview);
        RecyclerView recyclerview = view.findViewById(R.id.result_recyclerview);

        //korttien haku
        for (Map.Entry<String, Card> entry : deck.entrySet())
        {
            Card card = entry.getValue();

            if (card.getOwner() == null)
                continue;

            if (!valid_types.contains(card.getType()))
                continue;

            card_list.add(card);
        }

        //recyclerviewn setup
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(card_list, this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        //searchviewn toiminnallisuus
            //filtteröinti tekstin vaihtuessa
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {

                adapter.getFilter().filter(search_string);

                return false;
            }
        });

            //aloittaa kirjoittamisen aina kun klikataan eikä vain suurennuslasista.
        searchview.setOnClickListener(v -> searchview.setIconified(false));

        //dialogin rakentaminen ja näyttäminen

        dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        //dialogin koon muuttaminen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(3 * width / 4, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override public void onCardClick(int position) {
        Card card = card_list.get(position);
        //todo se mitä pitää tehdä.
        finish();
    }

    //tässä vaiheessa tyhjä. kun tehdään toiminnallisuus niin palauta true.
    @Override public boolean onCardLongClick(int position) { return false; }
}

