package com.example.terraformingmarscompanionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    SearchView searchview;
    //ArrayAdapter adapter;
    RecyclerView result_recyclerview;
    ArrayList<Card> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview = (SearchView) findViewById(R.id.searchview);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                System.out.println("SEARCH TEXT SUBMIT DETECTED");
                return false;
            }
        });

        result_recyclerview = (RecyclerView) findViewById(R.id.result_recyclerview);

    }

    //TODO korttien etsimisfunktio valmiiksi
    private void searchCards(String search_string)
    {
        //hakutoiminnot tähän

        setSearchResults(results);
    }

    private void setSearchResults(ArrayList<Card> search_results)
    {
        System.out.println("Alex: Search made.");
    }
}
