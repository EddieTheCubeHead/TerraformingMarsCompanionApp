package com.example.terraformingmarscompanionapp;

import androidx.appcompat.app.AppCompatActivity;

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
    ArrayAdapter adapter;
    ArrayList<Card> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ListView search_listview = findViewById(R.id.search_listview);


        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
        search_listview.setAdapter(adapter);

        searchview = findViewById(R.id.searchview);

        /*
        textbox.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String search_string = "placeholder";
                searchCards(search_string);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });
         */

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
