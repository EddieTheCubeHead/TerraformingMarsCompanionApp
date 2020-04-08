package com.example.terraformingmarscompanionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;
import android.widget.SearchView;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.ui.main.CardView;
import com.example.terraformingmarscompanionapp.ui.main.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    SearchView searchview;
    HashMap<String, Card> deck;
    ArrayList<CardView> card_list;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //korttilistan luominen
        card_list = new ArrayList<>();
        for (Map.Entry<String, Card> card : deck.entrySet())
            card_list.add(new CardView((Card) card));

        RecyclerView recyclerview = findViewById(R.id.result_recyclerview);
        //TODO testausta recyclerview fixedsize päällä (ilmeisesti parempi performanssi)
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerViewAdapter(card_list);
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);


        /*
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        */





        //recyclerviewn täyttö

        searchview = findViewById(R.id.searchview);

        recyclerview.setAdapter(adapter);

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
    }



    /*
    private void searchCards(String search_string)
    {
        Pattern.compile(search_string, Pattern.CASE_INSENSITIVE);
        String[] key_words = search_string.split(".*");
    }
     */

}

