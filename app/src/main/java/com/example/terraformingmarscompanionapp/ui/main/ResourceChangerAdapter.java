package com.example.terraformingmarscompanionapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.GameController;


public class ResourceChangerAdapter extends RecyclerView.Adapter<ResourceChangerAdapter.ViewHolder>
{
    private GameController gameController;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(@NonNull View item) {
            super(item);
            //TODO tähän kaikki findviewbyid't itemistä
            //ideana että pitää kaiken näkyvän datan tässä.
            //todennäkösesti resurssitägi
        }
    }


    public ResourceChangerAdapter(GameController gameController){
        this.gameController = gameController;
    }

    @NonNull
    @Override
    public ResourceChangerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View button_array = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_button_array, parent, false);
        ResourceChangerAdapter.ViewHolder button_array_holder = new ResourceChangerAdapter.ViewHolder(button_array);

        //tässä voi asettaa
        button_array.findViewById(R.id.button_minus5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return button_array_holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ResourceChangerAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

