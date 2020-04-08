package com.example.terraformingmarscompanionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.SearchView;
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

        //TODO tähän passataan deck intentillä.

        //korttilistan luominen
        card_list = new ArrayList<>();
        /*
        for (Map.Entry<String, Card> card : deck.entrySet())
            card_list.add(new CardView((Card) card));
            */

        RecyclerView recyclerview = findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true); //TODO testausta recyclerview sethasfixedsize kanssa(ilmeisesti parempi performanssi)

        adapter = new RecyclerAdapter(card_list, this, this); //this viittaa implementoituun
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        searchview = (SearchView) findViewById(R.id.searchview);

        recyclerview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test, menu);

        MenuItem search_item = menu.findItem(R.id.action_search);
        SearchView searchview = (SearchView) search_item.getActionView();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {
                adapter.getFilter().filter(search_string);
                return false;
            }
        });
        return true;
    }

    @Override public void onCardClick(int position) { }
    @Override public boolean onCardLongClick(int position) { return false; }
}

