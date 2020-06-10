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
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.packets.ResourceEventPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A UI class representing the choice on which card to place resources on
 *
 * @author Aleksanteri Reijo, Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class ActivityResourceAddition extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
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
    private ArrayList<Type> valid_types = new ArrayList<>(Arrays.asList(Type.GREEN, Type.RED, Type.BLUE, Type.CORPORATION));
    private ArrayList<ResourceCard.ResourceType> organics = new ArrayList<>(Arrays.asList(ResourceCard.ResourceType.ANIMAL, ResourceCard.ResourceType.MICROBE));

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

        Game game = GameController.getGame();
        HashMap<String, Card> deck = game.getAllCards();

        //inflating layout
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.dialog_search, null);

        //findviews
        searchview = view.findViewById(R.id.searchview);
        RecyclerView recyclerview = view.findViewById(R.id.result_recyclerview);

        if (special_case.equals("Robotic workforce")) {
            //filtering cards
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
            //filtering cards
            for (Map.Entry<String, Card> entry : deck.entrySet()) {
                Card card = entry.getValue();

                if (card.getOwner() == null) {
                    continue;
                }

                if (owner_required && !card.getOwner().getName().equals(player)) {
                    continue;
                }

                if (card instanceof ResourceCard) {
                    if (card.getOwner().getModifiers().getOrganicsProtected() && organics.contains(type)) {
                        if (amount < 0 && card.getOwner().getName() != player) {
                            continue;
                        }
                    }

                    if (((ResourceCard) card).getResourceType().equals(type)) {
                        card_list.add(card);
                    } else if (((ResourceCard) card).getResourceType() == ResourceCard.ResourceType.PET && type == ResourceCard.ResourceType.ANIMAL) {
                        card_list.add(card);
                    } else if (type.equals(ResourceCard.ResourceType.EXISTING) && ((ResourceCard) card).getResourceAmount() > 0) {
                        card_list.add(card);
                    } else {
                        System.out.println(((ResourceCard) card).getResourceType() + " : " + type);
                    }
                }
            }
            System.out.println("Found " + card_list.size() + " valid cards");
        }

        //recyclerview setup
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(card_list, this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        // SearchView functionality
        // Filter on text change
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) { return false; }
            @Override public boolean onQueryTextChange(String search_string) {

                adapter.getFilter().filter(search_string);

                return false;
            }
        });

            //starts text input on click
        searchview.setOnClickListener(v -> searchview.setIconified(false));

        //setting up dialog

        dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();

            //changing dialog size
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(3 * width / 4, WindowManager.LayoutParams.WRAP_CONTENT);

        view.findViewById(R.id.exit_button).setOnClickListener(v -> onBackPressed());
    }

    @Override public void onCardClick(int position) {
        Card card = card_list.get(position);
        if (special_case.equals("Robotic workforce")) {
            card.playProductionBox();
            //TODO Robotic workforce implementation
            GameActions.sendCardEvent(new CardEventPacket("Robotic workforce", GameController.getCurrentPlayer().getName(), 0));
        } else {
            if (GameController.getGame().getServerMultiplayer()) {
                GameActions.sendResourceEvent(new ResourceEventPacket(card.getName(), amount));
            }
            ((ResourceCard)card).changeResourceAmount(amount);
        }
        dialog.dismiss();
        Intent inGameUi = new Intent(this, InGameUI.class);
        inGameUi.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        startActivity(inGameUi);
    }

    // TODO have a look at possibly removing this
    @Override public boolean onCardLongClick(int position) { return false; }

    @Override
    public void onBackPressed() {
        dialog.dismiss();
        Intent inGameUi = new Intent(this, InGameUI.class);
        inGameUi.putExtra(InGameUI.UI_QUEUE_CHECK, true);
        startActivity(inGameUi);
    }
}

