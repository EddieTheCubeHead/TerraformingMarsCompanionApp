package com.example.terraformingmarscompanionapp.ui.gameMainElements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.ui.cardRecyclerAdapterClasses.CardParentRecyclerAdapter;
import com.example.terraformingmarscompanionapp.ui.cardRecyclerAdapterClasses.RecyclerAdapter;

/**
 * A {@link Fragment} subclass responsible for housing the
 */
public class CardListFragment extends Fragment {


    private RecyclerAdapter adapter;

    public CardListFragment() {
        // Required empty public constructor
    }

    public CardListFragment(RecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_search, container, false);
    }
}