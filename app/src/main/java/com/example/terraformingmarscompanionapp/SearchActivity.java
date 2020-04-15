package com.example.terraformingmarscompanionapp;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.main.RecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
{
    SearchView searchview;
    HashMap<String, Card> deck;
    ArrayList<Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.searchview);

        GameController gameController = GameController.getInstance();
        deck = gameController.getGame().getDeck();

        //korttien haku
        for (Map.Entry<String, Card> entry : deck.entrySet())
            card_list.add(entry.getValue());

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

    //TODO laita tähän kortin pelaaminen, ehkä klikkausääni jotenkin.
    /*pitää jotenkin selvittää että mitä ikkunoita avautuu pelaamisesta tai hinnasta.*/

    @Override public void onCardClick(int position) {
        Toast.makeText(getApplicationContext(),
                card_list.get(position).getName() + " would be played" , Toast.LENGTH_SHORT).show();
    }
    @Override public boolean onCardLongClick(int position) {
        Toast.makeText(getApplicationContext(), "card buy menu for: " +
                card_list.get(position).getName() , Toast.LENGTH_SHORT).show();
        return false;
    }
}

