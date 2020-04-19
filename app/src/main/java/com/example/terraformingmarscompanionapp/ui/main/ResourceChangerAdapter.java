package com.example.terraformingmarscompanionapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.GameController;

import java.util.ArrayList;

public class ResourceChangerAdapter extends RecyclerView.Adapter<ResourceChangerAdapter.ViewHolder>
{
    private GameController controller;

    private com.example.terraformingmarscompanionapp.cardSubclasses.Card card;
    
    private Boolean heat_is_money;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView resource_icon;
        public ImageButton button_plus1;
        public ImageButton button_minus1;
        public ImageButton button_plus5;
        public ImageButton button_minus5;

        public ViewHolder(@NonNull View item)
        {
            super(item);

            resource_icon = item.findViewById(R.id.array_resource_icon);

            button_plus1 = item.findViewById(R.id.button_plus1);
            button_minus1 = item.findViewById(R.id.button_minus1);
            button_plus5 = item.findViewById(R.id.button_plus5);
            button_minus5 = item.findViewById(R.id.button_minus5);
        }
    }

    public ResourceChangerAdapter(com.example.terraformingmarscompanionapp.cardSubclasses.Card card){
        controller = GameController.getInstance();
        heat_is_money = controller.getCurrentPlayer().getHeatIsMoney();

        //todo muiden resurssityyppien lisääminen
        //ehkä kannattaa muuttaa niin että kaikki tagit lisätään.

        this.card = card;


    }

    @NonNull
    @Override
    public ResourceChangerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View button_array = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_button_array, parent, false);

        ResourceChangerAdapter.ViewHolder button_array_holder
                = new ResourceChangerAdapter.ViewHolder(button_array);

        return button_array_holder;
    }



    //onbindviewholder asettaa resurssi-ikonin
    @Override
    public void onBindViewHolder(@NonNull ResourceChangerAdapter.ViewHolder holder, int index)
    {
        Integer resource_icon_drawable;
        switch (index)
        {
            case 0: resource_icon_drawable = R.drawable.ic_ph; break;
            case 1: resource_icon_drawable = R.drawable.ic_ph; break;
            case 2: resource_icon_drawable = R.drawable.ic_ph; break;
            case 3: resource_icon_drawable = R.drawable.ic_ph; break;
            case 4: resource_icon_drawable = R.drawable.ic_ph; break;
            case 5: resource_icon_drawable = R.drawable.ic_ph; break;
            default:
                System.err.println("ERROR IN HANDLING ICONS.");
                resource_icon_drawable = R.drawable.ic_ph;
                new Exception().printStackTrace();
                break;
        }


        holder.resource_icon.setImageResource(resource_icon_drawable);
    }


    @Override
    public int getItemCount() {
        int itemCount = 0;//credits aina
        /*
        for (Integer i : new Integer[] {credits, heat, titanium, steel})
            {if (i != null) {itemCount++;}}
         */

        return itemCount;
    }

    public void confirmPurchase()
    {
        //TODO tähän ostomekanismi
    }
}

