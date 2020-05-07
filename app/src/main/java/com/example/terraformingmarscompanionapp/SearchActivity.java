package com.example.terraformingmarscompanionapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.CardCostDialog;
import com.example.terraformingmarscompanionapp.ui.main.RecyclerAdapter;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
{
    private SearchView searchview;
    private ArrayList<Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;
    private ArrayList<Card.Type> valid_cards = new ArrayList<>(Arrays.asList(Card.Type.GREEN, Card.Type.RED, Card.Type.BLUE));

    public Integer titanium;
    public Integer heat;
    public Integer plant;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.searchview);

        GameController controller = GameController.getInstance();
        Game game = controller.getGame();
        HashMap<String, Card> deck = game.getDeck();

        //korttien haku
        for (Map.Entry<String, Card> entry : deck.entrySet())
        {
            Card card = entry.getValue();

            if (valid_cards.contains(card.getType()))
                card_list.add(card);
        }
        RecyclerView recyclerview = findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(card_list, this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {
                adapter.getFilter().filter(search_string);
                return false;
            }
        });

        //aloittaa kirjoittamisen aina kun klikataan eikä vain suurennuslasista.
        searchview.setOnClickListener(v -> searchview.setIconified(false));
    }

    @Override public void onCardClick(int position) {
        Card card = card_list.get(position);

        CardCostDialog.displayDialog(this, card);
    }

    //tässä vaiheessa tyhjä. kun tehdään toiminnallisuus niin palauta true.
    @Override public boolean onCardLongClick(int position) { return false; }
}

