package com.example.terraformingmarscompanionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.SearchView;

import com.example.terraformingmarscompanionapp.ui.main.CardView;
import com.example.terraformingmarscompanionapp.ui.main.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SearchActivity extends AppCompatActivity
{
    SearchView searchview;
    HashMap<String, Card> deck;
    ArrayList<CardView> card_list;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //korttilistan luominen
        card_list = new ArrayList<>();
        for (Map.Entry<String, Card> card : deck.entrySet())
            card_list.add(new CardView((Card) card));

        RecyclerView recyclerview = findViewById(R.id.result_recyclerview);
        //TODO testausta recyclerview sethasfixedsize kanssa(ilmeisesti parempi performanssi)
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(card_list);
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);


        //recyclerviewn täyttö

        searchview = (SearchView) findViewById(R.id.searchview);

        recyclerview.setAdapter(adapter);


        /*
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("SEARCH TEXT SUBMIT DETECTED");
                return false;
            }
        });
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test, menu);

        MenuItem search_item = menu.findItem(R.id.action_search);
        SearchView searchview = (SearchView) search_item.getActionView();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String search_string)
            {
                adapter.getFilter().filter(search_string);
                return false;
            }
        });
        return true;
    }
}

