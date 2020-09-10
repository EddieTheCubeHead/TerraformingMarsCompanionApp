package com.example.terraformingmarscompanionapp;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.ui.cardRecyclerAdapterClasses.CardBuyRecyclerAdapter;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchview;
    private CardBuyRecyclerAdapter adapter;

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

        adapter = new CardBuyRecyclerAdapter();
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

        //starts text input on click of searchview
        searchview.setOnClickListener(v -> searchview.setIconified(false));
    }


    /*
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
     */
}

