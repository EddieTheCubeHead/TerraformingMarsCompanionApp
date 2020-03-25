package com.example.terraformingmarscompanionapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.R;

import java.util.ArrayList;


//TODO jonkinlainen luokkarevision näistä
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder>
{
    private ArrayList<CardView> card_view_list;

    //sisältää kaiken visuaalisen datan kortista
    //ehkä hashmapviittauksilla jossain vaiheessa toiminta
    public static class CardViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView tag1_view;
        public ImageView tag2_view;
        public ImageView tag3_view;
        public TextView card_name_view;

        //constructorissa tehdään kortille viittaukset
        public CardViewHolder(@NonNull View card_view)
        {
            super(card_view);
            tag1_view = card_view.findViewById(R.id.tag1);
            tag2_view = card_view.findViewById(R.id.tag2);
            tag3_view = card_view.findViewById(R.id.tag3);
            card_name_view = card_view.findViewById(R.id.card_name);
        }
    }

    public RecyclerViewAdapter(ArrayList<CardView> card_view_list)
    {
        this.card_view_list = card_view_list;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        CardViewHolder card_view_holder = new CardViewHolder(view);
        return card_view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int index) {
        CardView current_item = card_view_list.get(index);
        int tag1 = current_item.getTag1();
        int tag2 = current_item.getTag2();
        int tag3 = current_item.getTag3();

        holder.tag1_view.setImageResource(tag1);
        holder.tag2_view.setImageResource(tag2);
        holder.tag3_view.setImageResource(tag3);
    }

    @Override
    public int getItemCount() {
        return card_view_list.size();
    }
}
