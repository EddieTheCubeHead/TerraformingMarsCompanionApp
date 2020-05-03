package com.example.terraformingmarscompanionapp.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

public class ResourceDialog
{

    //huono ratkaisu resurssien vaihtoon. ongelma geneerisyyden puutteessa.
    public CardCostPacket cost;
    private Player player;

    //käytetään kortin maksun kalibroimiseen suositellusta cardcostista.
    public Integer change;

    //Teräksen ja titaanin arvomuuttujat
    private Integer steel_value = (2 + player.getSteelValueModifier());
    private Integer titanium_value = (3 + player.getTitaniumValueModifier());

    public Integer credit;
    public Integer steel;
    public Integer titanium;
    public Integer heat;
    public Integer plant;
    public Integer floater;

    //context oli joka tapauksessa aikaisemmin "this" ingameui:ssa
    public void DisplayDialog(Context context, Card card)
    {
        GameController controller = GameController.getInstance();
        Game game = controller.getGame();
        player = controller.getCurrentPlayer();

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
        View view = inflater.inflate(R.layout.dialog_resource, null);

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


    /*
    public int getPlayerResource(String type)
    {
        switch (type)
        {
            case "money": return player.getMoney();
            case "steel": return player.getSteel();
            case "titanium": player.getTitanium();
            case "heat": player.getHeat();
            case "plant": player.getPlants();
            case "floater": return 0; //todo floaterilla maksu
            default:
                Log.e("bad ui call", "call: '"+ type +"'"); return 0;
        }
    }
     */


    //maksuvaihtofunktiot. päivittävät myös textviewtä.
    public void creditMinus(Integer amount){
        if (credit-amount <= 0)
        {
            change -= credit;
            credit = 0;
            return;
        }

        credit -= amount;
        change -= amount;

        //update ui
    }

    public void creditPlus(Integer amount) {
        if (credit+amount >= player.getMoney())
        {
            change += player.getMoney() - credit;
            credit = player.getMoney();
            return;
        }

        credit += amount;
        change += amount;

        //update ui
    }

    public void steelMinus(Integer amount) {

        if (steel-amount <= 0)
        {
            change -= steel * steel_value;
            credit = 0;
            return;
        }

        steel -= amount;
        change -= amount * steel_value;

        //update ui
    }

    public void steelPlus(Integer amount) {
        if (steel+amount >= player.getSteel())
        {
            change += (player.getSteel() - steel) * steel_value;
            steel = player.getSteel();
            return;
        }

        steel += amount;
        change += amount * steel_value;

        //update ui
    }

    public void titaniumMinus(Integer amount) {
        if (titanium-amount <= 0)
        {
            change -= titanium * titanium_value;
            credit = 0;
            return;
        }

        titanium -= amount;
        change -= amount;

        //update ui
    }

    public void titaniumPlus(Integer amount) {
        if (titanium+amount >= player.getTitanium())
        {
            change += (player.getTitanium() - titanium) * titanium_value;
            titanium = player.getTitanium();
            return;
        }

        titanium += amount;
        change += amount;

        //update ui
    }

    public void heatMinus(Integer amount) {
        if (heat-amount <= 0)
        {
            change -= heat;
            heat = 0;
            return;
        }

        heat -= amount;
        change -= amount;

        //update ui
    }

    public void heatPlus(Integer amount) {
        if (heat+amount >= player.getHeat())
        {
            change += player.getHeat() - heat;
            heat = player.getHeat();
            return;
        }

        heat += amount;
        change += amount;

        //update ui
    }

    public void plantMinus(Integer amount) {
        if (plant-amount <= 0)
        {
            change -= plant * 2;
            plant = 0;
            return;
        }

        plant -= amount;
        change -= amount * 2;

        //update ui
    }

    public void plantPlus(Integer amount) {
        if (plant+amount >= player.getPlants())
        {
            change += (player.getPlants() - plant) * 2;
            plant = player.getPlants();
            return;
        }

        plant += amount;
        change += amount * 2;

        //update ui
    }




    /*
    Dialog resource_dialog;

    RecyclerView popup_recyclerview;
    private ResourceChangerAdapter popup_adapter;

    //ui luokan konteksti

    public ResourceDialog (Context context, View view)
    {
        //dialogiin ja sen recyclerviewhin liittyvät
        popup_recyclerview = view.findViewById(R.id.popup_recyclerview);
        popup_recyclerview.setHasFixedSize(true);

        RecyclerView.LayoutManager popup_layout_manager = new LinearLayoutManager(context);
        popup_recyclerview.setLayoutManager(popup_layout_manager);

        view.findViewById(R.id.resource_confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_adapter.confirmPurchase();
            }
        });

        resource_dialog = new Dialog(context);
        resource_dialog.setContentView(R.layout.dialog_resource);
        resource_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void show(com.example.terraformingmarscompanionapp.cardSubclasses.Card card)
    {
        //TODO tähän recyclerviewn loaduppaaminen.
        //TODO kortin ostaminen monilla erilaisilla resursseilla
        //resourcepackage tai joku?

        //todo tähän juttujen listaesineiden laittaminen

        //noin vähäkoodisin tapa ladata popupin tiedot uudelleen jos laskee adapterin koodin.
        popup_adapter = new ResourceChangerAdapter(card);
        popup_recyclerview.setAdapter(popup_adapter);

        resource_dialog.show();
    }

     */
}
