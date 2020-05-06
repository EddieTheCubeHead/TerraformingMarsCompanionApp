package com.example.terraformingmarscompanionapp.ui.main;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ActivityDialogSearch extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
{
    public static final String OWNER_ONLY = "owner_required";
    public static final String SPECIAL_CASE = "special";
    public static final String AMOUNT = "amount";
    public static final String PLAYER_NAME = "owner_player";
    public static final String RESOURCE_TYPE = "resource_type";

    AlertDialog dialog;

    private SearchView searchview;

    private Integer amount;
    private String player;
    private String special_case;
    private Boolean owner_required;
    private ResourceCard.ResourceType type;

    private ArrayList<Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;
    private ArrayList<Card.Type> valid_types = new ArrayList<>(Arrays.asList(Card.Type.GREEN, Card.Type.RED, Card.Type.BLUE, Card.Type.CORPORATION));
    //todo tarkat valid types

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Intent intent = getIntent();

        special_case = intent.getStringExtra(SPECIAL_CASE);
        owner_required = intent.getBooleanExtra(OWNER_ONLY, false);
        player =intent.getStringExtra(PLAYER_NAME);
        amount = intent.getIntExtra(AMOUNT, 0);
        type = ResourceCard.ResourceType.valueOf(intent.getStringExtra(RESOURCE_TYPE));

        GameController controller = GameController.getInstance();
        Game game = controller.getGame();
        HashMap<String, Card> deck = game.getAllCards();

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.dialog_search, null);

        //findviewbyid't
        searchview = view.findViewById(R.id.searchview);
        RecyclerView recyclerview = view.findViewById(R.id.result_recyclerview);

        if (special_case.equals("Robotic workforce")) {
            //korttien haku
            for (Map.Entry<String, Card> entry : deck.entrySet()) {
                Card card = entry.getValue();

                if (card.getOwner() == null)
                    continue;

                if (!card.getOwner().getName().equals(player)) {
                    continue;
                }

                if (!card.getTags().contains(Tag.BUILDING))
                    continue;

                if (!valid_types.contains(card.getType()))
                    continue;

                card_list.add(card);
            }
            owner_required = true;
        } else {
            //korttien haku
            System.out.println("Searching for valid cards, deck size: " + deck.size() + " Required resource: " + type);
            for (Map.Entry<String, Card> entry : deck.entrySet()) {
                Card card = entry.getValue();

                if (card.getOwner() == null) {
                    continue;
                }

                if (owner_required && !card.getOwner().getName().equals(player)) {
                    continue;
                }

                if (card instanceof ResourceCard) {
                    if (((ResourceCard) card).getResourceType().equals(type)) {
                        card_list.add(card);
                    } else {
                        System.out.println(((ResourceCard) card).getResourceType() + " : " + type);
                    }
                }
            }
            System.out.println("Found " + card_list.size() + " valid cards");
        }

        //recyclerviewn setup
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(card_list, this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        //searchviewn toiminnallisuus
            //filtteröinti tekstin vaihtuessa
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {

                adapter.getFilter().filter(search_string);

                return false;
            }
        });

            //aloittaa kirjoittamisen aina kun klikataan eikä vain suurennuslasista.
        searchview.setOnClickListener(v -> searchview.setIconified(false));

        //dialogin rakentaminen ja näyttäminen

        dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

        //dialogin koon muuttaminen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(3 * width / 4, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    @Override public void onCardClick(int position) {
        Card card = card_list.get(position);
        if (special_case.equals("Robotic workforce")) {
            card.playProductionBox();
        } else {
            if (GameController.getInstance().getGame().getServerMultiplayer()) {
                GameActions.sendResourceEvent(new ResourceEventPacket(card.getName(), amount));
            }
            ((ResourceCard)card).changeResourceAmount(amount);
        }
        dialog.dismiss();
        Intent inGameUi = new Intent(this, InGameUI.class);
        inGameUi.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        startActivity(inGameUi);
    }

    //tässä vaiheessa tyhjä. kun tehdään toiminnallisuus niin palauta true.
    @Override public boolean onCardLongClick(int position) { return false; }
}

