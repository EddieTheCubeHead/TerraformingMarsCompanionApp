package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

import java.util.ArrayList;


/*
 * netistä: You should inflate your layout in onCreateView but shouldn't initialize other views using findViewById in onCreateView.
 * */

public class CardsFragment extends Fragment implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener{

    private SearchView searchview;
    private GameController controller;
    private Game game;
    private ArrayList<com.example.terraformingmarscompanionapp.cardSubclasses.Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;

    //otettu
    @Override public View onCreateView
    (
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        //defaultti: return inflater.inflate(R.layout.fragment_second, container, false);
        return inflater.inflate(R.layout.activity_search, container, false);
    }

    //view on oncreateviewn palautusarvo
    //sama juttu kun searchview tässä vaiheessa.
    //TODO tee gamecontrolleriin pelattavat kortit ui-esiintymisjärjestyksessä palauttava funktio.
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        //tästä eteenpäin noin sama koodi kuin searchactivityn oncreate:ssa
        searchview = view.findViewById(R.id.searchview);

        controller = GameController.getInstance();
        game = controller.getGame();
        card_list = controller.getCards();

        RecyclerView recyclerview = view.findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(card_list, this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(getContext()); //en tiedä onko oikea konteksti
        recyclerview.setLayoutManager(layout_manager);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {
                adapter.getFilter().filter(search_string);
                return false;
            }
        });

        //aloittaa kirjoittamisen aina kun klikataan eikä vain suurennuslasista.
        searchview.setOnClickListener(v -> searchview.setIconified(false));
    }

    @Override public void onCardClick(int position)
    {
        com.example.terraformingmarscompanionapp.cardSubclasses.Card card = card_list.get(position);

        //jos kortti implementtaa actioncard-interfacen
        if (card instanceof com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard) {
            Game game = GameController.getInstance().getGame();
            Integer action_metadata = ((ActionCard) card).cardAction();
            if (action_metadata == -1) {
                //TODO error handling: toiminto käytetty jo
            } else if (action_metadata == -2) {
                //TODO error handling: ei riittävästi resursseja
            } else if (game.getServerMultiplayer()) {
                GameActions.sendCardEvent(new CardEventPacket(card.getName(), card.getOwmer().getName(), action_metadata));
            }
        }

        //Periaatteessa kaikki effectit on automatisoitu
        /*else if (card instanceof com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard) {
            //TODO tee korttia vastaava effect.
        }*/

        else Log.i("non-interactable card","Pelaajan korttilistassa kortilla ei ollut CardActionia eikä CardEffectiä");
    }

    //tässä vaiheessa tyhjä. kun tehdään toiminnallisuus niin palauta true.
    @Override public boolean onCardLongClick(int position) { return false; }

    public void goToFragment1() {
        NavHostFragment.findNavController(CardsFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }
}
