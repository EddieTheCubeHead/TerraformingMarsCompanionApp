package com.example.terraformingmarscompanionapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    @Override public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
            )
    {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_resources, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                
            }


        };

    }

}
