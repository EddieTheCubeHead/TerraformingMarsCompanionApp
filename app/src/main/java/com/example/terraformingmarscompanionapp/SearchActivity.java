package com.example.terraformingmarscompanionapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.RecyclerAdapter;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements RecyclerAdapter.OnCardListener, RecyclerAdapter.OnCardLongListener
{
    private SearchView searchview;
    private GameController controller;
    private Game game;
    private Player player;
    private HashMap<String, Card> deck;
    private ArrayList<Card> card_list = new ArrayList<>();
    private RecyclerAdapter adapter;
    private ArrayList<Card.Type> valid_cards = new ArrayList<>(Arrays.asList(Card.Type.BLUE, Card.Type.RED, Card.Type.BLUE));

    //dialogimuuttujat
        //note: huono ratkaisu resurssien vaihtoon. ongelma geneerisyyden puutteessa.

    //käytetään kortin maksun kalibroimiseen suositellusta cardcostista.
    public Integer change = 0;

    public CardCostPacket cost;

    //Teräksen ja titaanin arvomuuttujat
    private Integer steel_value;
    private Integer titanium_value;

    public Integer credit;
    public Integer steel;
    public Integer titanium;
    public Integer heat;
    public Integer plant;
    public Integer floater;

    Dialog resource_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchview = findViewById(R.id.searchview);

        controller = GameController.getInstance();
        game = controller.getGame();
        deck = game.getDeck();

        //korttien haku
        for (Map.Entry<String, Card> entry : deck.entrySet())
        {
            Card card = entry.getValue();
            Card.Type type = card.getType();

            if (valid_cards.contains(card.getType()))
                card_list.add(card);
        }
        RecyclerView recyclerview = findViewById(R.id.result_recyclerview);
        recyclerview.setHasFixedSize(true);

        adapter = new RecyclerAdapter(card_list, this, this); //this koska tämä luokka implementoi metodit
        recyclerview.setAdapter(adapter);

        RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layout_manager);

        recyclerview.setAdapter(adapter);

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

    @Override public void onCardClick(int position) {
        Card card = card_list.get(position);

        DisplayDialog(this, card);
    }

    //tässä vaiheessa tyhjä. kun tehdään toiminnallisuus niin palauta true.
    @Override public boolean onCardLongClick(int position) { return false; }



    //DIALOGILOGIIKKa (alunperin resourcedialog -luokassa)
    View view;
    public void DisplayDialog(Context context, Card card)
    {
        GameController controller = GameController.getInstance();
        Game game = controller.getGame();
        player = controller.getCurrentPlayer();

        steel_value = (2 + player.getSteelValueModifier());
        titanium_value = (3 + player.getTitaniumValueModifier());

        //maksujen alustaminen suosituksiin
        cost = game.getRecommendedCardCost(card);

        //todo näytä viesti
        if (!cost.isEligible())
            return;

        credit = cost.getMoney();
        steel = cost.getSteel();
        titanium = cost.getTitanium();
        heat = cost.getHeat();
        plant = cost.getPlantResources();
        floater = cost.getFloaterResources();

        //layoutin rakentaminen
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dialog_resource, null);

        //visuaalinen muokkaus
        //view.setBackgroundColor(Color.TRANSPARENT);

        //onclicklistenerit resurssien vaihdoille, bracketit minimoimisen takia
        {
            view.findViewById(R.id.credit_minus5).setOnClickListener(v -> creditMinus(5));
            view.findViewById(R.id.credit_minus1).setOnClickListener(v -> creditMinus(1));
            view.findViewById(R.id.credit_plus1).setOnClickListener(v -> creditPlus(1));
            view.findViewById(R.id.credit_plus5).setOnClickListener(v -> creditPlus(5));

            view.findViewById(R.id.steel_minus5).setOnClickListener(v -> steelMinus(5));
            view.findViewById(R.id.steel_minus1).setOnClickListener(v -> steelMinus(1));
            view.findViewById(R.id.steel_plus1).setOnClickListener(v -> steelPlus(1));
            view.findViewById(R.id.steel_plus5).setOnClickListener(v -> steelPlus(5));

            view.findViewById(R.id.titanium_minus5).setOnClickListener(v -> titaniumMinus(5));
            view.findViewById(R.id.titanium_minus1).setOnClickListener(v -> titaniumMinus(1));
            view.findViewById(R.id.titanium_plus1).setOnClickListener(v -> titaniumPlus(1));
            view.findViewById(R.id.titanium_plus5).setOnClickListener(v -> titaniumPlus(5));

            view.findViewById(R.id.heat_minus5).setOnClickListener(v -> heatMinus(5));
            view.findViewById(R.id.heat_minus1).setOnClickListener(v -> heatMinus(1));
            view.findViewById(R.id.heat_plus1).setOnClickListener(v -> heatPlus(1));
            view.findViewById(R.id.heat_plus5).setOnClickListener(v -> heatPlus(5));

            view.findViewById(R.id.plant_minus5).setOnClickListener(v -> plantMinus(5));
            view.findViewById(R.id.plant_minus1).setOnClickListener(v -> plantMinus(1));
            view.findViewById(R.id.plant_plus1).setOnClickListener(v -> plantPlus(1));
            view.findViewById(R.id.plant_plus5).setOnClickListener(v -> plantPlus(5));
            /*
            view.findViewById(R.id.floater_minus5).setOnClickListener(v -> floaterMinus(5));
            view.findViewById(R.id.floater_minus1).setOnClickListener(v -> floaterMinus(1));
            view.findViewById(R.id.floater_plus1).setOnClickListener(v -> floaterPlus(1));
            view.findViewById(R.id.floater_plus5).setOnClickListener(v -> floaterPlus(5));
            */
        }

        //linearlayoutin juttujen poistaminen
        LinearLayout root = view.findViewById(R.id.dialog_root_layout);

        //TODO, eetu: ehdot poistoille

        //root.removeView(root.findViewById(R.id.dialog_credit_layout));

        if(!card.getTags().contains(Tag.BUILDING))
            root.removeView(root.findViewById(R.id.dialog_steel_layout));

        if(!card.getTags().contains(Tag.SPACE))
            root.removeView(root.findViewById(R.id.dialog_titanium_layout));

        if(!player.getHeatIsMoney())
            root.removeView(root.findViewById(R.id.dialog_heat_layout));

        //root.removeView(root.findViewById(R.id.dialog_plant_layout));

        //root.removeView(root.findViewById(R.id.dialog_floater_layout));

        AlertDialog dialog = new AlertDialog.Builder(context).setView(view).create();

        dialog.show();

        view.findViewById(R.id.resource_decline_button).setOnClickListener(v -> {
            dialog.dismiss();
        });

        view.findViewById(R.id.resource_confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //TODO oston tarkistus
                //TODO oston vahvistus
            }
        });

    }



    //maksuvaihtofunktiot. päivittävät myös textviewtä.
    public void creditMinus(Integer amount){
        if (credit-amount <= 0)
        {
            change -= credit;
            credit = 0;
        } else {
            credit -= amount;
            change -= amount;
        }

        ((TextView) view.findViewById(R.id.credit_chosen)).setText(credit);
    }

    public void creditPlus(Integer amount) {
        if (credit+amount >= player.getMoney())
        {
            change += player.getMoney() - credit;
            credit = player.getMoney();
        } else {
            credit += amount;
            change += amount;
        }

        ((TextView) view.findViewById(R.id.credit_chosen)).setText(credit);
    }

    public void steelMinus(Integer amount) {

        if (steel-amount <= 0)
        {
            change -= steel * steel_value;
            steel = 0;
        } else {
            steel -= amount;
            change -= amount * steel_value;
        }

        ((TextView) view.findViewById(R.id.steel_chosen)).setText(steel);
    }

    public void steelPlus(Integer amount) {
        if (steel+amount >= player.getSteel())
        {
            change += (player.getSteel() - steel) * steel_value;
            steel = player.getSteel();
        } else {
            steel += amount;
            change += amount * steel_value;
        }

        ((TextView) view.findViewById(R.id.steel_chosen)).setText(steel);
    }

    public void titaniumMinus(Integer amount) {
        if (titanium-amount <= 0)
        {
            change -= titanium * titanium_value;
            titanium = 0;
        } else {
            titanium -= amount;
            change -= amount;
        }

        ((TextView) view.findViewById(R.id.titanium_chosen)).setText(titanium);
    }

    public void titaniumPlus(Integer amount) {
        if (titanium+amount >= player.getTitanium())
        {
            change += (player.getTitanium() - titanium) * titanium_value;
            titanium = player.getTitanium();
        } else {
            titanium += amount;
            change += amount;
        }

        ((TextView) view.findViewById(R.id.titanium_chosen)).setText(titanium);
    }

    public void heatMinus(Integer amount) {
        if (heat-amount <= 0)
        {
            change -= heat;
            heat = 0;
        } else {
            heat -= amount;
            change -= amount;
        }

        ((TextView) view.findViewById(R.id.heat_chosen)).setText(heat);
    }

    public void heatPlus(Integer amount) {
        if (heat+amount >= player.getHeat())
        {
            change += player.getHeat() - heat;
            heat = player.getHeat();
        } else {
            heat += amount;
            change += amount;
        }

        ((TextView) view.findViewById(R.id.heat_chosen)).setText(heat);
    }

    public void plantMinus(Integer amount) {
        if (plant-amount <= 0)
        {
            change -= plant * 2;
            plant = 0;
        } else {
            plant -= amount;
            change -= amount * 2;
        }

        ((TextView) view.findViewById(R.id.plant_chosen)).setText(plant);
    }

    public void plantPlus(Integer amount) {
        if (plant+amount >= player.getPlants())
        {
            change += (player.getPlants() - plant) * 2;
            plant = player.getPlants();
        } else {
            plant += amount;
            change += amount * 2;
        }

        ((TextView) view.findViewById(R.id.plant_chosen)).setText(plant);
    }
}

