package com.example.terraformingmarscompanionapp;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.playDialogues.CardCostDialog;
import com.example.terraformingmarscompanionapp.ui.main.RecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
{
    private SearchView searchview;
    private ArrayList<Card> card_list;
    private RecyclerAdapter adapter;

    public Integer titanium;
    public Integer heat;
    public Integer plant;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.searchview);

        RecyclerView recyclerview = findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(this, this);
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        adapter.getFilter().filter("");
        card_list = adapter.getCardList();

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {
                adapter.getFilter().filter(search_string);
                card_list = adapter.getCardList();
                return false;
            }
        });

        //starts text input on click of searchview
        searchview.setOnClickListener(v -> searchview.setIconified(false));
    }

    @Override public void onCardClick(int position) {

        Card card = card_list.get(position);

        try {
            GameController.saveGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CardCostDialog.displayDialog(this, card);
    }

    @Override public boolean onCardLongClick(int position) { return false; }
}

