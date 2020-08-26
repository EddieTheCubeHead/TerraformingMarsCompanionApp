package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardsFragment extends Fragment implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener, GameController.GameUpdateListener{

    private SearchView searchview;
    private GameController controller;
    private Game game;
    private ArrayList<Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;
    RecyclerView recyclerview;
    RecyclerView.LayoutManager layout_manager;

    //otettu
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //default: return inflater.inflate(R.layout.fragment_second, container, false);
        return inflater.inflate(R.layout.activity_search, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //standard code in multiple classes. maybe possible to generize.
        searchview = view.findViewById(R.id.searchview);

        GameController.registerGameUpdateListener(this);

        game = GameController.getGame();
        HashMap<String, Card>deck = game.getDeck();

        for (Map.Entry entry : game.getCorporations().entrySet()) {
            card_list.add((Card)entry.getValue());
        }

        //filtering cards
        for (Map.Entry<String, Card> entry : deck.entrySet())
        {
            Card card = entry.getValue();
            card_list.add(card);
        }

        recyclerview = view.findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        layout_manager = new LinearLayoutManager(getContext()); //en tiedä onko oikea konteksti
        recyclerview.setLayoutManager(layout_manager);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {
                adapter.getPlayedFilter().filter(search_string);
                return false;
            }
        });

        //starts text input on click of searchview
        searchview.setOnClickListener(v -> searchview.setIconified(false));
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }


    //does card-specific action.
    @Override public void onCardClick(int position)
    {
        if (!GameController.checkTurnEligibility()) {
            Toast.makeText(getContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
            return;
        } else if (GameController.getGreeneryRound()) {
            Toast.makeText(getContext(), "Can only build greeneries!", Toast.LENGTH_SHORT).show();
        }

        Card card = card_list.get(position);

        if (card instanceof ActionCard) {
            Boolean validity = ((ActionCard) card).getActionValidity();
            String action_name = ((ActionCard) card).getActionName();

            if (validity) {
                System.out.println(((ActionCard) card).getActionValidity());
                Toast.makeText(getContext(), String.format("Action '%s' not usable.\nInsufficient requirements or already used", action_name), Toast.LENGTH_SHORT).show();
            } else {
                // TODO event to handle UI better?

                try {
                    GameController.saveGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                EventScheduler.addEvent(new ActionUseEvent());
                ((ActionCard) card).cardAction();
                Toast.makeText(getContext(), String.format("Action '%s' used", action_name), Toast.LENGTH_SHORT).show();
            }
            update();
        }

        else Log.i("CardsFragment","Trying to use action on a card with no action attached");
    }

    @Override public boolean onCardLongClick(int position) { return false; }

    //updates by refiltering
    @Override public void update() {
        game = GameController.getGame();
        adapter.getPlayedFilter().filter("");
        Log.i("CardsFragment", "Fragment updated");
    }
}