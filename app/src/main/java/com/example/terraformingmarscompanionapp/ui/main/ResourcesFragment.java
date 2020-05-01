package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.terraformingmarscompanionapp.R;

public class ResourcesFragment extends Fragment {


    //Paikalliset muuttujat muutosmoodiin
    Integer money;
    Integer moneyProduction;
    Integer steel;
    Integer steelProduction;
    Integer titanium;
    Integer titaniumProduction;
    Integer plants;
    Integer plantsProduction;
    Integer energy;
    Integer energyProduction;
    Integer heat;
    Integer heatProduction;
    Integer temperature;
    Integer terraformingRating;
    Integer oxygen;

    Boolean editmode;



    @Override public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    )
    {

        editmode = false;

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_resources, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageButton button_temperature_minus = getView().findViewById(R.id.button_temperature_minus);
        ImageButton button_temperature_plus = getView().findViewById(R.id.button_temperature_plus);
        ImageButton button_tfr_minus = getView().findViewById(R.id.button_tfr_minus);
        ImageButton button_tfr_plus = getView().findViewById(R.id.button_tfr_plus);
        ImageButton button_oxygen_minus = getView().findViewById(R.id.button_oxygen_minus);
        ImageButton button_oxygen_plus = getView().findViewById(R.id.button_oxygen_plus);
        ImageButton button_money_minus = getView().findViewById(R.id.button_money_minus);
        ImageButton button_money_plus = getView().findViewById(R.id.button_money_plus);
        ImageButton button_money_production_minus = getView().findViewById(R.id.button_money_production_minus);
        ImageButton button_money_production_plus = getView().findViewById(R.id.button_money_production_plus);
        ImageButton button_steel_minus = getView().findViewById(R.id.button_steel_minus);
        ImageButton button_steel_plus = getView().findViewById(R.id.button_steel_plus);
        ImageButton button_steel_production_minus = getView().findViewById(R.id.button_steel_production_minus);
        ImageButton button_steel_production_plus = getView().findViewById(R.id.button_steel_production_plus);
        ImageButton button_titanium_minus = getView().findViewById(R.id.button_titanium_minus);
        ImageButton button_titanium_plus = getView().findViewById(R.id.button_titanium_plus);
        ImageButton button_titanium_production_minus = getView().findViewById(R.id.button_titanium_production_minus);
        ImageButton button_titanium_production_plus = getView().findViewById(R.id.button_titanium_production_plus);
        ImageButton button_plants_minus = getView().findViewById(R.id.button_plants_minus);
        ImageButton button_plants_plus = getView().findViewById(R.id.button_plants_plus);
        ImageButton button_plants_production_minus = getView().findViewById(R.id.button_plants_production_minus);
        ImageButton button_plants_production_plus = getView().findViewById(R.id.button_plants_production_plus);
        ImageButton button_energy_minus = getView().findViewById(R.id.button_energy_minus);
        ImageButton button_energy_plus = getView().findViewById(R.id.button_energy_plus);
        ImageButton button_energy_production_minus = getView().findViewById(R.id.button_energy_production_minus);
        ImageButton button_energy_production_plus = getView().findViewById(R.id.button_energy_production_plus);
        ImageButton button_heat_minus = getView().findViewById(R.id.button_heat_minus);
        ImageButton button_heat_plus = getView().findViewById(R.id.button_heat_plus);
        ImageButton button_heat_production_minus = getView().findViewById(R.id.button_heat_production_minus);
        ImageButton button_heat_production_plus = getView().findViewById(R.id.button_heat_production_plus);

        Button button_multiplier_1 = getView().findViewById(R.id.button_multiplier_1);
        Button button_multiplier_5 = getView().findViewById(R.id.button_multiplier_5);
        Button button_multiplier_10 = getView().findViewById(R.id.button_multiplier_10);

        Button button_save_resources = getView().findViewById(R.id.button_save_resources);
        Button button_editcancel_resources = getView().findViewById(R.id.button_editcancel_resources);






        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == button_temperature_minus) {

                }

                if (v == button_temperature_plus) {

                }

                if (v == button_tfr_minus) {

                }

                if (v == button_tfr_plus) {

                }

                if (v == button_oxygen_minus) {

                }

                if (v == button_oxygen_plus) {

                }

                if (v == button_money_minus) {

                }

                if (v == button_money_plus) {

                }

                if (v == button_money_production_minus) {

                }

                if (v == button_money_production_plus) {

                }

                if (v == button_steel_minus) {

                }

                if (v == button_steel_plus) {

                }

                if (v == button_steel_production_minus) {

                }

                if (v == button_steel_production_plus) {

                }

                if (v == button_titanium_minus) {

                }

                if (v == button_titanium_plus) {

                }

                if (v == button_titanium_production_minus) {

                }

                if (v == button_titanium_production_plus) {

                }

                if (v == button_plants_minus) {

                }

                if (v == button_plants_plus) {

                }

                if (v == button_plants_production_minus) {

                }

                if (v == button_plants_production_plus) {

                }

                if (v == button_energy_minus) {

                }

                if (v == button_energy_plus) {

                }

                if (v == button_energy_production_minus) {

                }

                if (v == button_energy_production_plus) {

                }

                if (v == button_heat_minus) {

                }

                if (v == button_heat_plus) {

                }

                if (v == button_heat_production_minus) {

                }

                if (v == button_heat_production_plus) {

                }






                if (v == button_multiplier_1) {

                }

                if (v == button_multiplier_5) {

                }

                if (v == button_multiplier_10) {

                }






                if (v == button_save_resources) {

                }

                if (v == button_editcancel_resources) {

                    if (!editmode) {

                        System.out.println("0");

                        editmode = true;

                        button_temperature_minus.setClickable(true);
                        button_temperature_plus.setClickable(true);
                        button_tfr_minus.setClickable(true);
                        button_tfr_plus.setClickable(true);
                        button_oxygen_minus.setClickable(true);
                        button_oxygen_plus.setClickable(true);
                        button_money_minus.setClickable(true);
                        button_money_plus.setClickable(true);
                        button_money_production_minus.setClickable(true);
                        button_money_production_plus.setClickable(true);
                        button_steel_minus.setClickable(true);
                        button_steel_plus.setClickable(true);
                        button_steel_production_minus.setClickable(true);
                        button_steel_production_plus.setClickable(true);
                        button_titanium_minus.setClickable(true);
                        button_titanium_plus.setClickable(true);
                        button_titanium_production_minus.setClickable(true);
                        button_titanium_production_plus.setClickable(true);
                        button_plants_minus.setClickable(true);
                        button_plants_plus.setClickable(true);
                        button_plants_production_minus.setClickable(true);
                        button_plants_production_plus.setClickable(true);
                        button_energy_minus.setClickable(true);
                        button_energy_plus.setClickable(true);
                        button_energy_production_minus.setClickable(true);
                        button_energy_production_plus.setClickable(true);
                        button_heat_minus.setClickable(true);
                        button_heat_plus.setClickable(true);
                        button_heat_production_minus.setClickable(true);
                        button_heat_production_plus.setClickable(true);

                        button_multiplier_1.setClickable(true);
                        button_multiplier_5.setClickable(true);
                        button_multiplier_10.setClickable(true);

                        button_save_resources.setClickable(true);

                        button_temperature_minus.setAlpha(1f);
                        button_temperature_plus.setAlpha(1f);
                        button_tfr_minus.setAlpha(1f);
                        button_tfr_plus.setAlpha(1f);
                        button_oxygen_minus.setAlpha(1f);
                        button_oxygen_plus.setAlpha(1f);
                        button_money_minus.setAlpha(1f);
                        button_money_plus.setAlpha(1f);
                        button_money_production_minus.setAlpha(1f);
                        button_money_production_plus.setAlpha(1f);
                        button_steel_minus.setAlpha(1f);
                        button_steel_plus.setAlpha(1f);
                        button_steel_production_minus.setAlpha(1f);
                        button_steel_production_plus.setAlpha(1f);
                        button_titanium_minus.setAlpha(1f);
                        button_titanium_plus.setAlpha(1f);
                        button_titanium_production_minus.setAlpha(1f);
                        button_titanium_production_plus.setAlpha(1f);
                        button_plants_minus.setAlpha(1f);
                        button_plants_plus.setAlpha(1f);
                        button_plants_production_minus.setAlpha(1f);
                        button_plants_production_plus.setAlpha(1f);
                        button_energy_minus.setAlpha(1f);
                        button_energy_plus.setAlpha(1f);
                        button_energy_production_minus.setAlpha(1f);
                        button_energy_production_plus.setAlpha(1f);
                        button_heat_minus.setAlpha(1f);
                        button_heat_plus.setAlpha(1f);
                        button_heat_production_minus.setAlpha(1f);
                        button_heat_production_plus.setAlpha(1f);


                        button_multiplier_1.setAlpha(1);
                        button_multiplier_5.setAlpha(1);
                        button_multiplier_10.setAlpha(1);

                        button_save_resources.setAlpha(1);
                        button_editcancel_resources.setText("Cancel");

                    }

                    else if (editmode) {

                        System.out.println("1");


                        editmode = false;

                        button_temperature_minus.setClickable(false);
                        button_temperature_plus.setClickable(false);
                        button_tfr_minus.setClickable(false);
                        button_tfr_plus.setClickable(false);
                        button_oxygen_minus.setClickable(false);
                        button_oxygen_plus.setClickable(false);
                        button_money_minus.setClickable(false);
                        button_money_plus.setClickable(false);
                        button_money_production_minus.setClickable(false);
                        button_money_production_plus.setClickable(false);
                        button_steel_minus.setClickable(false);
                        button_steel_plus.setClickable(false);
                        button_steel_production_minus.setClickable(false);
                        button_steel_production_plus.setClickable(false);
                        button_titanium_minus.setClickable(false);
                        button_titanium_plus.setClickable(false);
                        button_titanium_production_minus.setClickable(false);
                        button_titanium_production_plus.setClickable(false);
                        button_plants_minus.setClickable(false);
                        button_plants_plus.setClickable(false);
                        button_plants_production_minus.setClickable(false);
                        button_plants_production_plus.setClickable(false);
                        button_energy_minus.setClickable(false);
                        button_energy_plus.setClickable(false);
                        button_energy_production_minus.setClickable(false);
                        button_energy_production_plus.setClickable(false);
                        button_heat_minus.setClickable(false);
                        button_heat_plus.setClickable(false);
                        button_heat_production_minus.setClickable(false);
                        button_heat_production_plus.setClickable(false);

                        button_multiplier_1.setClickable(false);
                        button_multiplier_5.setClickable(false);
                        button_multiplier_10.setClickable(false);

                        button_save_resources.setClickable(false);

                        button_temperature_minus.setAlpha(0f);
                        button_temperature_plus.setAlpha(0f);
                        button_tfr_minus.setAlpha(0f);
                        button_tfr_plus.setAlpha(0f);
                        button_oxygen_minus.setAlpha(0f);
                        button_oxygen_plus.setAlpha(0f);
                        button_money_minus.setAlpha(0f);
                        button_money_plus.setAlpha(0f);
                        button_money_production_minus.setAlpha(0f);
                        button_money_production_plus.setAlpha(0f);
                        button_steel_minus.setAlpha(0f);
                        button_steel_plus.setAlpha(0f);
                        button_steel_production_minus.setAlpha(0f);
                        button_steel_production_plus.setAlpha(0f);
                        button_titanium_minus.setAlpha(0f);
                        button_titanium_plus.setAlpha(0f);
                        button_titanium_production_minus.setAlpha(0f);
                        button_titanium_production_plus.setAlpha(0f);
                        button_plants_minus.setAlpha(0f);
                        button_plants_plus.setAlpha(0f);
                        button_plants_production_minus.setAlpha(0f);
                        button_plants_production_plus.setAlpha(0f);
                        button_energy_minus.setAlpha(0f);
                        button_energy_plus.setAlpha(0f);
                        button_energy_production_minus.setAlpha(0f);
                        button_energy_production_plus.setAlpha(0f);
                        button_heat_minus.setAlpha(0f);
                        button_heat_plus.setAlpha(0f);
                        button_heat_production_minus.setAlpha(0f);
                        button_heat_production_plus.setAlpha(0f);


                        button_multiplier_1.setAlpha(0);
                        button_multiplier_5.setAlpha(0);
                        button_multiplier_10.setAlpha(0);

                        button_save_resources.setAlpha(0);
                        button_editcancel_resources.setText("Edit");



                    }



                }






            }


        };

        button_editcancel_resources.setOnClickListener(listener);



    }



}