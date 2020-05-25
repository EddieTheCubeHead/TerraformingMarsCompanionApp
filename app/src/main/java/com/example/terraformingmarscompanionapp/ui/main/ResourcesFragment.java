package com.example.terraformingmarscompanionapp.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public class ResourcesFragment extends Fragment implements GameController.GameUpdateListener {

    private Player player = GameController.getDisplayPlayer();
    private Game game = GameController.getGame();

    //Paikalliset muuttujat muutosmoodiin
    private Integer multiplier;
    private Boolean editmode;

    private Integer money;
    private Integer moneyProduction;
    private Integer steel;
    private Integer steelProduction;
    private Integer titanium;
    private Integer titaniumProduction;
    private Integer plants;
    private Integer plantsProduction;
    private Integer energy;
    private Integer energyProduction;
    private Integer heat;
    private Integer heatProduction;
    private Integer temperature;
    private Integer terraformingRating;
    private Integer oxygen;

    private ImageButton button_temperature_minus;
    private ImageButton button_temperature_plus;
    private ImageButton button_tfr_minus;
    private ImageButton button_tfr_plus;
    private ImageButton button_oxygen_minus;
    private ImageButton button_oxygen_plus;
    private ImageButton button_money_minus;
    private ImageButton button_money_plus;
    private ImageButton button_money_production_minus;
    private ImageButton button_money_production_plus;
    private ImageButton button_steel_minus;
    private ImageButton button_steel_plus;
    private ImageButton button_steel_production_minus;
    private ImageButton button_steel_production_plus;
    private ImageButton button_titanium_minus;
    private ImageButton button_titanium_plus;
    private ImageButton button_titanium_production_minus;
    private ImageButton button_titanium_production_plus;
    private ImageButton button_plants_minus;
    private ImageButton button_plants_plus;
    private ImageButton button_plants_production_minus;
    private ImageButton button_plants_production_plus;
    private ImageButton button_energy_minus;
    private ImageButton button_energy_plus;
    private ImageButton button_energy_production_minus;
    private ImageButton button_energy_production_plus;
    private ImageButton button_heat_minus;
    private ImageButton button_heat_plus;
    private ImageButton button_heat_production_minus;
    private ImageButton button_heat_production_plus;

    private Button button_multiplier_1;
    private Button button_multiplier_5;
    private Button button_multiplier_10;

    private Button button_save_resources;
    private Button button_editcancel_resources;

    private TextView textview_current_player_name;
    private TextView textview_current_player_actions_remaining;

    private TextView textview_temperature;
    private TextView textview_tfr;
    private TextView textview_oxygen;
    private TextView textview_money;
    private TextView textview_steel;
    private TextView textview_titanium;
    private TextView textview_plants;
    private TextView textview_energy;
    private TextView textview_heat;
    private TextView textview_money_production;
    private TextView textview_steel_production;
    private TextView textview_titanium_production;
    private TextView textview_plants_production;
    private TextView textview_energy_production;
    private TextView textview_heat_production;


    @Override public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    )
    {
        editmode = false;
        multiplier = 1;

        refreshEditVariables();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_resources, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button_temperature_minus = getView().findViewById(R.id.button_temperature_minus);
        button_temperature_plus = getView().findViewById(R.id.button_temperature_plus);
        button_tfr_minus = getView().findViewById(R.id.button_tfr_minus);
        button_tfr_plus = getView().findViewById(R.id.button_tfr_plus);
        button_oxygen_minus = getView().findViewById(R.id.button_oxygen_minus);
        button_oxygen_plus = getView().findViewById(R.id.button_oxygen_plus);
        button_money_minus = getView().findViewById(R.id.button_money_minus);
        button_money_plus = getView().findViewById(R.id.button_money_plus);
        button_money_production_minus = getView().findViewById(R.id.button_money_production_minus);
        button_money_production_plus = getView().findViewById(R.id.button_money_production_plus);
        button_steel_minus = getView().findViewById(R.id.button_steel_minus);
        button_steel_plus = getView().findViewById(R.id.button_steel_plus);
        button_steel_production_minus = getView().findViewById(R.id.button_steel_production_minus);
        button_steel_production_plus = getView().findViewById(R.id.button_steel_production_plus);
        button_titanium_minus = getView().findViewById(R.id.button_titanium_minus);
        button_titanium_plus = getView().findViewById(R.id.button_titanium_plus);
        button_titanium_production_minus = getView().findViewById(R.id.button_titanium_production_minus);
        button_titanium_production_plus = getView().findViewById(R.id.button_titanium_production_plus);
        button_plants_minus = getView().findViewById(R.id.button_plants_minus);
        button_plants_plus = getView().findViewById(R.id.button_plants_plus);
        button_plants_production_minus = getView().findViewById(R.id.button_plants_production_minus);
        button_plants_production_plus = getView().findViewById(R.id.button_plants_production_plus);
        button_energy_minus = getView().findViewById(R.id.button_energy_minus);
        button_energy_plus = getView().findViewById(R.id.button_energy_plus);
        button_energy_production_minus = getView().findViewById(R.id.button_energy_production_minus);
        button_energy_production_plus = getView().findViewById(R.id.button_energy_production_plus);
        button_heat_minus = getView().findViewById(R.id.button_heat_minus);
        button_heat_plus = getView().findViewById(R.id.button_heat_plus);
        button_heat_production_minus = getView().findViewById(R.id.button_heat_production_minus);
        button_heat_production_plus = getView().findViewById(R.id.button_heat_production_plus);

        button_multiplier_1 = getView().findViewById(R.id.button_multiplier_1);
        button_multiplier_5 = getView().findViewById(R.id.button_multiplier_5);
        button_multiplier_10 = getView().findViewById(R.id.button_multiplier_10);

        button_save_resources = getView().findViewById(R.id.button_save_resources);
        button_editcancel_resources = getView().findViewById(R.id.button_editcancel_resources);

        textview_current_player_name = getView().findViewById(R.id.textview_current_player_name);
        textview_current_player_actions_remaining = getView().findViewById(R.id.textview_current_player_actions_remaining);

        textview_temperature = getView().findViewById(R.id.textview_temperature);
        textview_tfr = getView().findViewById(R.id.textview_tfr);
        textview_oxygen = getView().findViewById(R.id.textview_oxygen);
        textview_money = getView().findViewById(R.id.textview_money);
        textview_steel = getView().findViewById(R.id.textview_steel);
        textview_titanium = getView().findViewById(R.id.textview_titanium);
        textview_plants = getView().findViewById(R.id.textview_plants);
        textview_energy = getView().findViewById(R.id.textview_energy);
        textview_heat = getView().findViewById(R.id.textview_heat);
        textview_money_production = getView().findViewById(R.id.textview_money_production);
        textview_steel_production = getView().findViewById(R.id.textview_steel_production);
        textview_titanium_production = getView().findViewById(R.id.textview_titanium_production);
        textview_plants_production = getView().findViewById(R.id.textview_plants_production);
        textview_energy_production = getView().findViewById(R.id.textview_energy_production);
        textview_heat_production = getView().findViewById(R.id.textview_heat_production);

        setResourceAmounts();

        View.OnClickListener listener = v ->
        {

            if (v == button_temperature_minus) {
                temperature = changeResourceAmount(temperature, textview_temperature, true);
                textview_temperature.setText(temperature + "°C");

            }

            if (v == button_temperature_plus) {
                temperature = changeResourceAmount(temperature, textview_temperature, false);
                textview_temperature.setText(temperature + "°C");
            }

            if (v == button_tfr_minus) {
                terraformingRating = changeResourceAmount(terraformingRating, textview_tfr, true);

                if (terraformingRating == 0) {
                    setMinusClickable(button_tfr_minus, false);
                }
            }

            if (v == button_tfr_plus) {
                terraformingRating = changeResourceAmount(terraformingRating, textview_tfr, false);

                if(!button_tfr_minus.isClickable()) {
                    setMinusClickable(button_tfr_minus, true);
                }


            }

            if (v == button_oxygen_minus) {
                oxygen = changeResourceAmount(oxygen, textview_oxygen, true);
                textview_oxygen.setText(oxygen + "%");

                if (oxygen == 0) {
                    setMinusClickable(button_oxygen_minus, false);
                }
            }

            if (v == button_oxygen_plus) {
                oxygen = changeResourceAmount(oxygen, textview_oxygen, false);
                textview_oxygen.setText(oxygen + "%");

                if(!button_oxygen_minus.isClickable()) {
                    setMinusClickable(button_oxygen_minus, true);
                }
            }

            if (v == button_money_minus) {
                money = changeResourceAmount(money, textview_money, true);

                if (money == 0) {
                    setMinusClickable(button_money_minus, false);
                }
            }


            if (v == button_money_plus) {
                money = changeResourceAmount(money, textview_money, false);

                if(!button_money_minus.isClickable()) {
                    setMinusClickable(button_money_minus, true);
                }
            }

            if (v == button_steel_minus) {
                steel = changeResourceAmount(steel, textview_steel, true);

                if (steel == 0) {
                    setMinusClickable(button_steel_minus, false);
                }
            }

            if (v == button_steel_plus) {
                steel = changeResourceAmount(steel, textview_steel, false);

                if(!button_steel_minus.isClickable()) {
                    setMinusClickable(button_steel_minus, true);
                }
            }

            if (v == button_titanium_minus) {
                titanium = changeResourceAmount(titanium, textview_titanium, true);

                if (titanium == 0) {
                    setMinusClickable(button_titanium_minus, false);
                }
            }

            if (v == button_titanium_plus) {
                titanium = changeResourceAmount(titanium, textview_titanium, false);

                if(!button_titanium_minus.isClickable()) {
                    setMinusClickable(button_titanium_minus, true);
                }
            }

            if (v == button_plants_minus) {
                plants = changeResourceAmount(plants, textview_plants, true);

                if (plants == 0) {
                    setMinusClickable(button_plants_minus, false);
                }
            }

            if (v == button_plants_plus) {
                plants = changeResourceAmount(plants, textview_plants, false);

                if(!button_plants_minus.isClickable()) {
                    setMinusClickable(button_plants_minus, true);
                }
            }

            if (v == button_energy_minus) {
                energy = changeResourceAmount(energy, textview_energy, true);

                if (energy == 0) {
                    setMinusClickable(button_energy_minus, false);
                }
            }

            if (v == button_energy_plus) {
                energy = changeResourceAmount(energy, textview_energy, false);

                if(!button_energy_minus.isClickable()) {
                    setMinusClickable(button_energy_minus, true);
                }
            }

            if (v == button_heat_minus) {
                heat = changeResourceAmount(heat, textview_heat, true);

                if (heat == 0) {
                    setMinusClickable(button_heat_minus, false);
                }
            }

            if (v == button_heat_plus) {
                heat = changeResourceAmount(heat, textview_heat, false);

                if(!button_heat_minus.isClickable()) {
                    setMinusClickable(button_heat_minus, true);
                }
            }

            if (v == button_money_production_minus) {
                moneyProduction = changeResourceAmount(moneyProduction, textview_money_production, true);
            }


            if (v == button_money_production_plus) {
                moneyProduction = changeResourceAmount(moneyProduction, textview_money_production, false);
            }

            if (v == button_steel_production_minus) {
                steelProduction = changeResourceAmount(steelProduction, textview_steel_production, true);

                if (steelProduction == 0) {
                    setMinusClickable(button_steel_production_minus, false);
                }
            }

            if (v == button_steel_production_plus) {
                steelProduction = changeResourceAmount(steelProduction, textview_steel_production, false);

                if(!button_steel_production_minus.isClickable()) {
                    setMinusClickable(button_steel_production_minus, true);
                }
            }

            if (v == button_titanium_production_minus) {
                titaniumProduction = changeResourceAmount(titaniumProduction, textview_titanium_production, true);

                if (titaniumProduction == 0) {
                    setMinusClickable(button_titanium_production_minus, false);
                }
            }

            if (v == button_titanium_production_plus) {
                titaniumProduction = changeResourceAmount(titaniumProduction, textview_titanium_production, false);

                if(!button_titanium_production_minus.isClickable()) {
                    setMinusClickable(button_titanium_production_minus, true);
                }
            }

            if (v == button_plants_production_minus) {
                plantsProduction = changeResourceAmount(plantsProduction, textview_plants_production, true);

                if (plantsProduction == 0) {
                    setMinusClickable(button_plants_production_minus, false);
                }
            }

            if (v == button_plants_production_plus) {
                plantsProduction = changeResourceAmount(plantsProduction, textview_plants_production, false);

                if(!button_plants_production_minus.isClickable()) {
                    setMinusClickable(button_plants_production_minus, true);
                }
            }

            if (v == button_energy_production_minus) {
                energyProduction = changeResourceAmount(energyProduction, textview_energy_production, true);

                if (energyProduction == 0) {
                    setMinusClickable(button_energy_production_minus, false);
                }
            }

            if (v == button_energy_production_plus) {
                energyProduction = changeResourceAmount(energyProduction, textview_energy_production, false);

                if(!button_energy_production_minus.isClickable()) {
                    setMinusClickable(button_energy_production_minus, true);
                }
            }

            if (v == button_heat_production_minus) {
                heatProduction = changeResourceAmount(heatProduction, textview_heat_production, true);

                if (heatProduction == 0) {
                    setMinusClickable(button_heat_production_minus, false);
                }
            }

            if (v == button_heat_production_plus) {
                heatProduction = changeResourceAmount(heatProduction, textview_heat_production, false);

                if(!button_heat_production_minus.isClickable()) {
                    setMinusClickable(button_heat_production_minus, true);
                }
            }




            if (v == button_multiplier_1)
            {
                multiplier = 1;

                button_multiplier_1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                button_multiplier_5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                button_multiplier_10.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
            }

            if (v == button_multiplier_5)
            {
                multiplier = 5;

                button_multiplier_1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                button_multiplier_5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                button_multiplier_10.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
            }

            if (v == button_multiplier_10)
            {
                multiplier = 10;

                button_multiplier_1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                button_multiplier_5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                button_multiplier_10.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
            }


            if (v == button_save_resources)
            {
                multiplier = 1;

                button_multiplier_1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                button_multiplier_5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                button_multiplier_10.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));

                editmode = false;

                player.changeMoney(money - player.getMoney());
                player.changeMoneyProduction(moneyProduction - player.getMoneyProduction());
                player.changeSteel(money - player.getMoney());
                player.changeSteelProduction(steelProduction - player.getSteelProduction());
                player.changeTitanium(titanium - player.getTitanium());
                player.changeTitaniumProduction(titaniumProduction - player.getTitaniumProduction());
                player.changePlants(plants - player.getPlants());
                player.changePlantsProduction(plantsProduction - player.getPlantsProduction());
                player.changeEnergy(energy - player.getEnergy());
                player.changeEnergyProduction(energyProduction - player.getEnergyProduction());
                player.changeHeat(heat - player.getHeatProduction());
                player.changeHeatProduction(heatProduction - player.getHeatProduction());
                player.changeTerraformingRating(terraformingRating - player.getTerraformingRating());
                game.rawChangeTemperature(temperature - game.getGlobalTemperature());
                game.rawChangeOxygen(oxygen - game.getGlobalOxygen());

                changeMode(false, 0);
                button_editcancel_resources.setText("Edit");
            }

            if (v == button_editcancel_resources)
            {

                //Pressing edit button
                if (!editmode)
                {
                    editmode = true;
                    refreshEditVariables();

                    changeMode(true, 1);

                    button_editcancel_resources.setText("Cancel");

                    if (terraformingRating == 0) {
                            setMinusClickable(button_tfr_minus, false);
                    }
                    if (oxygen == 0) {
                            setMinusClickable(button_oxygen_minus, false);
                    }
                    if (money == 0) {
                            setMinusClickable(button_money_minus, false);
                    }
                    if (steel == 0) {
                            setMinusClickable(button_steel_minus, false);
                    }
                    if (titanium == 0) {
                            setMinusClickable(button_titanium_minus, false);
                    }
                    if (plants == 0) {
                            setMinusClickable(button_plants_minus, false);
                    }
                    if (energy == 0) {
                            setMinusClickable(button_energy_minus, false);
                    }
                    if (heat == 0) {
                            setMinusClickable(button_heat_minus, false);
                    }
                    if (steelProduction == 0) {
                            setMinusClickable(button_steel_production_minus, false);
                    }
                    if (titaniumProduction == 0) {
                            setMinusClickable(button_titanium_production_minus, false);
                    }
                    if (plantsProduction == 0) {
                            setMinusClickable(button_plants_production_minus, false);
                    }
                    if (energyProduction == 0) {
                            setMinusClickable(button_energy_production_minus, false);
                    }
                    if (heatProduction == 0) {
                            setMinusClickable(button_heat_production_minus, false);
                    }
                }

                //Pressing cancel button
                else
                {
                    editmode = false;

                    changeMode(false, 0);

                    button_editcancel_resources.setText("Edit");

                    setResourceAmounts();

                }
            }
        };

        button_temperature_minus.setOnClickListener(listener);
        button_temperature_plus.setOnClickListener(listener);
        button_tfr_minus.setOnClickListener(listener);
        button_tfr_plus.setOnClickListener(listener);
        button_oxygen_minus.setOnClickListener(listener);
        button_oxygen_plus.setOnClickListener(listener);
        button_money_minus.setOnClickListener(listener);
        button_money_plus.setOnClickListener(listener);
        button_money_production_minus.setOnClickListener(listener);
        button_money_production_plus.setOnClickListener(listener);
        button_steel_minus.setOnClickListener(listener);
        button_steel_plus.setOnClickListener(listener);
        button_steel_production_minus.setOnClickListener(listener);
        button_steel_production_plus.setOnClickListener(listener);
        button_titanium_minus.setOnClickListener(listener);
        button_titanium_plus.setOnClickListener(listener);
        button_titanium_production_minus.setOnClickListener(listener);
        button_titanium_production_plus.setOnClickListener(listener);
        button_plants_minus.setOnClickListener(listener);
        button_plants_plus.setOnClickListener(listener);
        button_plants_production_minus.setOnClickListener(listener);
        button_plants_production_plus.setOnClickListener(listener);
        button_energy_minus.setOnClickListener(listener);
        button_energy_plus.setOnClickListener(listener);
        button_energy_production_minus.setOnClickListener(listener);
        button_energy_production_plus.setOnClickListener(listener);
        button_heat_minus.setOnClickListener(listener);
        button_heat_plus.setOnClickListener(listener);
        button_heat_production_minus.setOnClickListener(listener);
        button_heat_production_plus.setOnClickListener(listener);

        button_multiplier_1.setOnClickListener(listener);
        button_multiplier_5.setOnClickListener(listener);
        button_multiplier_10.setOnClickListener(listener);

        button_save_resources.setOnClickListener(listener);

        button_editcancel_resources.setOnClickListener(listener);

        changeMode(false, 0);

    }


    // Changing the buttons when exiting or entering editmode
    private void changeMode(boolean mode, float alpha)
    {
        button_temperature_minus.setClickable(mode);
        button_temperature_plus.setClickable(mode);
        button_tfr_minus.setClickable(mode);
        button_tfr_plus.setClickable(mode);
        button_oxygen_minus.setClickable(mode);
        button_oxygen_plus.setClickable(mode);
        button_money_minus.setClickable(mode);
        button_money_plus.setClickable(mode);
        button_money_production_minus.setClickable(mode);
        button_money_production_plus.setClickable(mode);
        button_steel_minus.setClickable(mode);
        button_steel_plus.setClickable(mode);
        button_steel_production_minus.setClickable(mode);
        button_steel_production_plus.setClickable(mode);
        button_titanium_minus.setClickable(mode);
        button_titanium_plus.setClickable(mode);
        button_titanium_production_minus.setClickable(mode);
        button_titanium_production_plus.setClickable(mode);
        button_plants_minus.setClickable(mode);
        button_plants_plus.setClickable(mode);
        button_plants_production_minus.setClickable(mode);
        button_plants_production_plus.setClickable(mode);
        button_energy_minus.setClickable(mode);
        button_energy_plus.setClickable(mode);
        button_energy_production_minus.setClickable(mode);
        button_energy_production_plus.setClickable(mode);
        button_heat_minus.setClickable(mode);
        button_heat_plus.setClickable(mode);
        button_heat_production_minus.setClickable(mode);
        button_heat_production_plus.setClickable(mode);

        button_multiplier_1.setClickable(mode);
        button_multiplier_5.setClickable(mode);
        button_multiplier_10.setClickable(mode);

        button_save_resources.setClickable(mode);

        button_temperature_minus.setAlpha(alpha);
        button_temperature_plus.setAlpha(alpha);
        button_tfr_minus.setAlpha(alpha);
        button_tfr_plus.setAlpha(alpha);
        button_oxygen_minus.setAlpha(alpha);
        button_oxygen_plus.setAlpha(alpha);
        button_money_minus.setAlpha(alpha);
        button_money_plus.setAlpha(alpha);
        button_money_production_minus.setAlpha(alpha);
        button_money_production_plus.setAlpha(alpha);
        button_steel_minus.setAlpha(alpha);
        button_steel_plus.setAlpha(alpha);
        button_steel_production_minus.setAlpha(alpha);
        button_steel_production_plus.setAlpha(alpha);
        button_titanium_minus.setAlpha(alpha);
        button_titanium_plus.setAlpha(alpha);
        button_titanium_production_minus.setAlpha(alpha);
        button_titanium_production_plus.setAlpha(alpha);
        button_plants_minus.setAlpha(alpha);
        button_plants_plus.setAlpha(alpha);
        button_plants_production_minus.setAlpha(alpha);
        button_plants_production_plus.setAlpha(alpha);
        button_energy_minus.setAlpha(alpha);
        button_energy_plus.setAlpha(alpha);
        button_energy_production_minus.setAlpha(alpha);
        button_energy_production_plus.setAlpha(alpha);
        button_heat_minus.setAlpha(alpha);
        button_heat_plus.setAlpha(alpha);
        button_heat_production_minus.setAlpha(alpha);
        button_heat_production_plus.setAlpha(alpha);

        button_multiplier_1.setAlpha(alpha);
        button_multiplier_5.setAlpha(alpha);
        button_multiplier_10.setAlpha(alpha);

        button_save_resources.setAlpha(alpha);
    }

    private Integer changeResourceAmount(Integer resourceType, TextView textView, boolean isMinus)
    {
        if (isMinus) {resourceType -= multiplier;}
        if (!isMinus) {resourceType += multiplier;}

        if (resourceType < 0 && textView != textview_money_production && textView != textview_temperature) { resourceType = 0; }

        textView.setText(String.valueOf(resourceType));

        return resourceType;
    }

    private void setMinusClickable(ImageButton minusButton, boolean clickable) {

        if (!clickable) {
            minusButton.setAlpha(0.5f);
            minusButton.setClickable(false);
        }

        if (clickable) {
            minusButton.setAlpha(1f);
            minusButton.setClickable(true);
        }
    }

    //Listening for changes in the game
    @Override
    public void update() {
        setResourceAmounts();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        GameController.registerGameUpdateListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GameController.unregisterGameUpdateListener(this);
    }

    //Setting the numbers to the current player's resources, used for updating the view
    private void setResourceAmounts()
    {
        player = GameController.getDisplayPlayer();

        try {
            textview_current_player_name.setText(GameController.getCurrentPlayer().getName());
            textview_current_player_actions_remaining.setText(String.valueOf(GameController.getDisplayActions()));
            textview_temperature.setText(game.getGlobalTemperature() + "°C");
            textview_oxygen.setText(game.getGlobalOxygen() + "%");
            textview_tfr.setText(String.valueOf(player.getTerraformingRating()));
            textview_money.setText(String.valueOf(player.getMoney()));
            textview_money_production.setText(String.valueOf(player.getMoneyProduction()));
            textview_steel.setText(String.valueOf(player.getSteel()));
            textview_steel_production.setText(String.valueOf(player.getSteelProduction()));
            textview_titanium.setText(String.valueOf(player.getTitanium()));
            textview_titanium_production.setText(String.valueOf(player.getTitaniumProduction()));
            textview_plants.setText(String.valueOf(player.getPlants()));
            textview_plants_production.setText(String.valueOf(player.getPlantsProduction()));
            textview_energy.setText(String.valueOf(player.getEnergy()));
            textview_energy_production.setText(String.valueOf(player.getEnergyProduction()));
            textview_heat.setText(String.valueOf(player.getHeat()));
            textview_heat_production.setText(String.valueOf(player.getHeatProduction()));
        } catch (NullPointerException ignored) {
        }
    }

    //refreshing the local variables for editmode
    private void refreshEditVariables() {

        money = player.getMoney();
        moneyProduction = player.getMoneyProduction();
        steel = player.getSteel();
        steelProduction = player.getSteelProduction();
        titanium = player.getTitanium();
        titaniumProduction = player.getTitaniumProduction();
        plants = player.getPlants();
        plantsProduction = player.getPlantsProduction();
        energy = player.getEnergy();
        energyProduction = player.getEnergyProduction();
        heat = player.getHeat();
        heatProduction = player.getHeatProduction();
        temperature = game.getGlobalTemperature();
        terraformingRating = player.getTerraformingRating();
        oxygen = game.getGlobalOxygen();
    }

    @Override
    public void onResume() {
        super.onResume();
        setResourceAmounts();
    }
}