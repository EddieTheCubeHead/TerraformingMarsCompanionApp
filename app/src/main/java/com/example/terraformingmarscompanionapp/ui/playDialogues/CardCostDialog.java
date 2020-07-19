package com.example.terraformingmarscompanionapp.ui.playDialogues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardCostPacket;

public class CardCostDialog {

    private static Player player;

    //used for verifying the validity of payment dialogs by comparing values with recommendedcardcost
    private static Integer change;

    //megacredits per unit values for steel and titanium
    private static Integer steel_value;
    private static Integer titanium_value;

    //units payed
    private static Integer credit;
    private static Integer steel;
    private static Integer titanium;
    private static Integer heat;
    private static Integer plant;
    private static Integer floater;

    @SuppressLint("StaticFieldLeak")
    private static View view;

    public static void displayDialog(Context context, Card card) {displayDialog(context, card, false);}

    public static void displayDialog(Context context, Card card, Boolean from_action)
    {
        Game game = GameController.getGame();
        player = GameController.getCurrentPlayer();

        change = 0;

        steel_value = (2 + player.getModifiers().getSteelValueModifier());
        titanium_value = (3 + player.getModifiers().getTitaniumValueModifier());

        CardCostPacket cost = game.prepareCardPlayAction(card);

        if (!cost.isEligible()) {
            Toast.makeText(context, String.format("Can not play card '%s'", card.getName()), Toast.LENGTH_SHORT).show();
            return;
        }

        credit = cost.getMoney();
        steel = cost.getSteel();
        titanium = cost.getTitanium();
        heat = cost.getHeat();
        plant = cost.getPlantResources();
        floater = cost.getFloaterResources();

        //inflating layout
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dialog_resource, null);

        //visual editing
            //for clear corners, probably not the best way to do this
            view.setBackgroundColor(Color.TRANSPARENT);

            //opens to recommended costs
        ((TextView) view.findViewById(R.id.credit_chosen)).setText(credit.toString());
        ((TextView) view.findViewById(R.id.steel_chosen)).setText(steel.toString());
        ((TextView) view.findViewById(R.id.titanium_chosen)).setText(titanium.toString());
        ((TextView) view.findViewById(R.id.heat_chosen)).setText(heat.toString());
        ((TextView) view.findViewById(R.id.plant_chosen)).setText(plant.toString());

        //onclicklisteners for changing payment resources
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

        //removing resources that can't be used.
        LinearLayout root = view.findViewById(R.id.dialog_root_layout);

        if(!card.getTags().contains(Tag.BUILDING))
            root.removeView(root.findViewById(R.id.dialog_steel_layout));

        if(!card.getTags().contains(Tag.SPACE))
            root.removeView(root.findViewById(R.id.dialog_titanium_layout));

        if(!player.getModifiers().getHeatIsMoney())
            root.removeView(root.findViewById(R.id.dialog_heat_layout));

        root.removeView(root.findViewById(R.id.dialog_plant_layout));

        root.removeView(root.findViewById(R.id.dialog_floater_layout));

        AlertDialog dialog = new AlertDialog.Builder(context).setView(view).create();

        dialog.show();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((AppCompatActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window window = dialog.getWindow();

        window.setLayout(4*width/5, WindowManager.LayoutParams.WRAP_CONTENT);

        //window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //window.setGravity(Gravity.CENTER);

        view.findViewById(R.id.resource_decline_button) .setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.popup_exit_image)        .setOnClickListener(v -> dialog.dismiss());

        view.findViewById(R.id.resource_confirm_button).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v)
            {
                confirmPlay(card, dialog, context, from_action);
            }
        });
    }


    private static void confirmPlay(Card card, AlertDialog dialog, Context context, Boolean from_action)
    {
        if (change < 0) {
            Toast.makeText(context, String.format("Please add %d megacredits worth of resources.", -change), Toast.LENGTH_SHORT).show();
        } else if (change > 0 &&
                (!(card.getTags().contains(Tag.BUILDING) && change < steel_value) ||
                        !(card.getTags().contains(Tag.SPACE) && change < titanium_value))) {
            Toast.makeText(context, String.format("Please remove %d megacredits worth of resources", change), Toast.LENGTH_SHORT).show();
        } else {
            GameController.getGame().playCard(card, new CardCostPacket(player.getName(), credit, steel, titanium, heat, plant, floater), context);
            Toast.makeText(context, String.format("Card '%s' played successfully!", card.getName()), Toast.LENGTH_SHORT).show();
            exit(dialog);
        }
    }

    private static void exit(AlertDialog dialog) {
        dialog.dismiss();
    }

    //functions for changing the amount of units payed. they also update the shown amount.
    private static void creditMinus(Integer amount){
        if (credit-amount <= 0)
        {
            change -= credit;
            credit = 0;
        } else {
            credit -= amount;
            change -= amount;
        }

        ((TextView) view.findViewById(R.id.credit_chosen)).setText(credit.toString());
    }

    private static void creditPlus(Integer amount) {
        if (credit+amount >= player.getResources().getMoney())
        {
            change += player.getResources().getMoney() - credit;
            credit = player.getResources().getMoney();
        } else {
            credit += amount;
            change += amount;
        }

        ((TextView) view.findViewById(R.id.credit_chosen)).setText(credit.toString());
    }

    private static void steelMinus(Integer amount) {

        if (steel-amount <= 0)
        {
            change -= steel * steel_value;
            steel = 0;
        } else {
            steel -= amount;
            change -= amount * steel_value;
        }

        ((TextView) view.findViewById(R.id.steel_chosen)).setText(steel.toString());
    }

    private static void steelPlus(Integer amount) {
        if (steel+amount >= player.getResources().getSteel())
        {
            change += (player.getResources().getSteel() - steel) * steel_value;
            steel = player.getResources().getSteel();
        } else {
            steel += amount;
            change += amount * steel_value;
        }

        ((TextView) view.findViewById(R.id.steel_chosen)).setText(steel.toString());
    }

    private static void titaniumMinus(Integer amount) {
        if (titanium-amount <= 0)
        {
            change -= titanium * titanium_value;
            titanium = 0;
        } else {
            titanium -= amount;
            change -= amount * titanium_value;
        }

        ((TextView) view.findViewById(R.id.titanium_chosen)).setText(titanium.toString());
    }

    private static void titaniumPlus(Integer amount) {
        if (titanium+amount >= player.getResources().getTitanium())
        {
            change += (player.getResources().getTitanium() - titanium) * titanium_value;
            titanium = player.getResources().getTitanium();
        } else {
            titanium += amount;
            change += amount * titanium_value;
        }

        ((TextView) view.findViewById(R.id.titanium_chosen)).setText(titanium.toString());
    }

    private static void heatMinus(Integer amount) {
        if (heat-amount <= 0)
        {
            change -= heat;
            heat = 0;
        } else {
            heat -= amount;
            change -= amount;
        }

        ((TextView) view.findViewById(R.id.heat_chosen)).setText(heat.toString());
    }

    private static void heatPlus(Integer amount) {
        if (heat+amount >= player.getResources().getHeat())
        {
            change += player.getResources().getHeat() - heat;
            heat = player.getResources().getHeat();
        } else {
            heat += amount;
            change += amount;
        }

        ((TextView) view.findViewById(R.id.heat_chosen)).setText(heat.toString());
    }

    private static void plantMinus(Integer amount) {
        if (plant-amount <= 0)
        {
            change -= plant * 2;
            plant = 0;
        } else {
            plant -= amount;
            change -= amount * 2;
        }

        ((TextView) view.findViewById(R.id.plant_chosen)).setText(plant.toString());
    }

    private static void plantPlus(Integer amount) {
        if (plant+amount >= player.getResources().getPlants())
        {
            change += (player.getResources().getPlants() - plant) * 2;
            plant = player.getResources().getPlants();
        } else {
            plant += amount;
            change += amount * 2;
        }

        ((TextView) view.findViewById(R.id.plant_chosen)).setText(plant.toString());
    }
}
