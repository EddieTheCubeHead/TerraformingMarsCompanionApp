package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.cards.basegame.utilityCards.BuildGreenery;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.cardRecyclerAdapterClasses.RecyclerAdapter;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardCostPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A veiw for the "extra" cards and actions, special projects, awards, milestones.
 */
public class SpecialCardFragment extends Fragment implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener, GameController.GameUpdateListener {
    private SearchView searchview;
    private GameController controller;
    private Game game;
    private ArrayList<Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;
    RecyclerView recyclerview;
    RecyclerView.LayoutManager layout_manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        GameController.registerGameUpdateListener(this);

        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_search, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchview = view.findViewById(R.id.searchview);

        game = GameController.getGame();
        HashMap<String, Card> deck = game.getDeck();

        // cards into arraylist
        for (Map.Entry<String, Card> entry : deck.entrySet())
        {
            Card card = entry.getValue();
            card_list.add(card);
        }

        for (Map.Entry entry : game.getAwards().entrySet()) {
            card_list.add((Card)entry.getValue());
        }

        for (Map.Entry entry : game.getMilestones().entrySet()) {
            card_list.add((Card)entry.getValue());
        }

        recyclerview = view.findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(this, this);
        recyclerview.setAdapter(adapter);

        layout_manager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layout_manager);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {
                adapter.getPlayedFilter().filter(search_string);
                return false;
            }
        });

        //starts text input on click
        searchview.setOnClickListener(v -> searchview.setIconified(false));
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }


    @Override public void onCardClick(int position)
    {
        if (!GameController.checkTurnEligibility()) {
            Toast.makeText(getContext(), "Not your turn!", Toast.LENGTH_SHORT).show();
            return;
        }

        Card card = card_list.get(position);

        if (GameController.getGreeneryRound() && !(card instanceof BuildGreenery)) {
            Toast.makeText(getContext(), "Can only build greeneries!", Toast.LENGTH_SHORT).show();
        }

        try {
             CardCostPacket packet = game.prepareCardPlayAction(card);
             if (!packet.isEligible()) {
                 Toast.makeText(getContext(), "Invalid requirements!", Toast.LENGTH_SHORT).show();
                 return;
             }

             GameController.saveGame();

             Toast.makeText(getContext(), String.format("Action %s performed", card.getName()), Toast.LENGTH_SHORT).show();
             game.playCard(card, packet, this.getContext());
        } catch (Exception ignored) {}
    }

    @Override public boolean onCardLongClick(int position) { return false; }

    @Override
    public void update() {
        adapter.getSpecialFilter().filter("");
    }
}
