package com.example.terraformingmarscompanionapp;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.ui.main.RecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
{
    SearchView searchview;
    HashMap<String, Card> deck;
    ArrayList<Card> card_list;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.searchview);

        Game game = (Game) getIntent().getExtras().get("game");

        deck = game.getDeck();

        //korttilistan luominen
        card_list = new ArrayList<>();

        RecyclerView recyclerview = findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true); //TODO testausta recyclerview sethasfixedsize kanssa(ilmeisesti parempi performanssi)

        adapter = new RecyclerAdapter(card_list, this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        recyclerview.setAdapter(adapter);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {
                adapter.getFilter().filter(search_string);
                return false;
            }
        });

    }

    @Override public void onCardClick(int position) { }
    @Override public boolean onCardLongClick(int position) { return false; }
}

