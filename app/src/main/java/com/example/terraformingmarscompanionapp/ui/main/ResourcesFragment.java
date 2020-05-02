package com.example.terraformingmarscompanionapp.ui.main;

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
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public class ResourcesFragment extends Fragment {

    GameController controller = GameController.getInstance();
    Player player = controller.getCurrentPlayer();


    //Paikalliset muuttujat muutosmoodiin
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


    private Integer multiplier;

    private Boolean editmode;

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
        temperature = 0;
        terraformingRating = player.getTerraformingRating();
        oxygen = 0;



                ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_resources, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        System.out.println("testijou");

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

        SetResourceAmounts();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == button_temperature_minus) {

                    temperature = ChangeResourceAmount(temperature, textview_temperature, true);

                }

                if (v == button_temperature_plus) {

                    temperature = ChangeResourceAmount(temperature, textview_temperature, false);

                }

                if (v == button_tfr_minus) {

                    terraformingRating = ChangeResourceAmount(terraformingRating, textview_tfr, true);

                }

                if (v == button_tfr_plus) {

                    terraformingRating = ChangeResourceAmount(terraformingRating, textview_tfr, false);

                }

                if (v == button_oxygen_minus) {

                    oxygen = ChangeResourceAmount(oxygen, textview_oxygen, true);


                }

                if (v == button_oxygen_plus) {

                    oxygen = ChangeResourceAmount(oxygen, textview_oxygen, false);

                }

                if (v == button_money_minus) {

                    money = ChangeResourceAmount(money, textview_money, true);

                }

                if (v == button_money_plus) {

                    money = ChangeResourceAmount(money, textview_money, false);

                }

                if (v == button_steel_minus) {

                    steel = ChangeResourceAmount(steel, textview_steel, true);

                }

                if (v == button_steel_plus) {

                    steel = ChangeResourceAmount(steel, textview_steel, false);

                }

                if (v == button_titanium_minus) {

                    titanium = ChangeResourceAmount(titanium, textview_titanium, true);

                }

                if (v == button_titanium_plus) {

                    titanium = ChangeResourceAmount(titanium, textview_titanium, false);

                }

                if (v == button_plants_minus) {

                    plants = ChangeResourceAmount(plants, textview_plants, true);


                }

                if (v == button_plants_plus) {

                    plants = ChangeResourceAmount(plants, textview_plants, false);

                }


                if (v == button_energy_minus) {

                    energy = ChangeResourceAmount(energy, textview_energy, true);

                }

                if (v == button_energy_plus) {

                    energy = ChangeResourceAmount(energy, textview_energy, false);

                }

                if (v == button_heat_minus) {

                    heat = ChangeResourceAmount(heat, textview_heat, true);

                }

                if (v == button_heat_plus) {

                    heat = ChangeResourceAmount(heat, textview_heat, true);

                }

                if (v == button_money_production_minus) {

                    moneyProduction = ChangeResourceAmount(moneyProduction, textview_money_production , true);

                }

                if (v == button_money_production_plus) {

                    moneyProduction = ChangeResourceAmount(moneyProduction, textview_money_production , false);

                }

                if (v == button_steel_production_minus) {

                    steelProduction = ChangeResourceAmount(steelProduction, textview_steel_production , true);

                }

                if (v == button_steel_production_plus) {

                    steelProduction = ChangeResourceAmount(steelProduction, textview_steel_production , false);

                }

                if (v == button_titanium_production_minus) {

                    titaniumProduction = ChangeResourceAmount(titaniumProduction, textview_titanium_production , true);

                }

                if (v == button_titanium_production_plus) {

                    titaniumProduction = ChangeResourceAmount(titaniumProduction, textview_titanium_production , false);

                }

                if (v == button_plants_production_minus) {

                    plantsProduction = ChangeResourceAmount(plantsProduction, textview_plants_production , true);


                }

                if (v == button_plants_production_plus) {

                    plantsProduction = ChangeResourceAmount(plantsProduction, textview_plants_production , false);

                }


                if (v == button_energy_production_minus) {

                    energyProduction = ChangeResourceAmount(energyProduction, textview_energy_production , true);

                }

                if (v == button_energy_production_plus) {

                    energyProduction = ChangeResourceAmount(energyProduction, textview_energy_production , false);

                }

                if (v == button_heat_production_minus) {

                    heatProduction = ChangeResourceAmount(heatProduction, textview_heat_production , true);

                }

                if (v == button_heat_production_plus) {

                    heatProduction = ChangeResourceAmount(heatProduction, textview_heat_production , false);

                }











                if (v == button_multiplier_1) {

                    multiplier = 5;

                    button_multiplier_1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                    button_multiplier_5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    button_multiplier_10.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));


                }

                if (v == button_multiplier_5) {

                    multiplier = 5;

                    button_multiplier_1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    button_multiplier_5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
                    button_multiplier_10.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));


                }

                if (v == button_multiplier_10) {

                    multiplier = 10;

                    button_multiplier_1.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    button_multiplier_5.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    button_multiplier_10.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));


                }






                if (v == button_save_resources) {

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

                    ChangeMode(false, 0);
                    button_editcancel_resources.setText("Edit");


                }

                if (v == button_editcancel_resources) {

                    //Edit painikkeen painaminen
                    if (!editmode) {

                        editmode = true;

                        ChangeMode(true, 1);

                        button_editcancel_resources.setText("Cancel");

                    }

                    //Cancel painikkeen painaminen
                    else if (editmode) {

                        editmode = false;

                        ChangeMode(false, 0);

                        button_editcancel_resources.setText("Edit");

                        SetResourceAmounts();

                    }



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



    }


    private void ChangeMode(boolean mode, float alpha) {

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


    private Integer ChangeResourceAmount(Integer resourceType, TextView textView, boolean isMinus) {

        if (isMinus) {resourceType -= multiplier;}
        if (!isMinus) {resourceType += multiplier;}

        textView.setText(String.valueOf(resourceType));

        return resourceType;
    }


    //Asettaa arvot nykyisen pelaajan arvoista

    //TODO sijoita johonkin, jossa aktivoituu yhtiödien valinnan jälkeen
    public void SetResourceAmounts() {

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
    }

}