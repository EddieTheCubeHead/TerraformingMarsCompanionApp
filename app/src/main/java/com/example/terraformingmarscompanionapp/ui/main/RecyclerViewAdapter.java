package com.example.terraformingmarscompanionapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for filtering cards in different contexts
 */

//debug: uses unchecked or unsafe operations
//Note: Recompile with -Xlint:unchecked for details
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CardViewHolder> implements Filterable
{
    private ArrayList<CardView> card_view_list;
    private ArrayList<CardView> card_view_list_full;

    private OnCardListener on_card_listener;
    private OnCardLongListener on_card_long_listener;

    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        OnCardListener onCardListener;
        OnCardLongListener onCardLongListener;

        public TextView card_name_view;
        public ImageView requirement_view;
        public ImageView tag1_view;
        public ImageView tag2_view;
        public ImageView tag3_view;
        public ImageView tag4_view;

        //Making references to cards
        public CardViewHolder(@NonNull View card_inflated, OnCardListener onCardListener, OnCardLongListener onCardLongListener)
        {
            super(card_inflated);
            card_name_view = card_inflated.findViewById(R.id.card_name);
            requirement_view = card_inflated.findViewById(R.id.requirement);
            tag1_view = card_inflated.findViewById(R.id.tag1);
            tag2_view = card_inflated.findViewById(R.id.tag2);
            tag3_view = card_inflated.findViewById(R.id.tag3);
            tag4_view = card_inflated.findViewById(R.id.tag4);

            //Clicking
            card_inflated.setOnClickListener(this);
            this.onCardListener = onCardListener;
            //Long click
            card_inflated.setOnLongClickListener(this);
            this.onCardLongListener = onCardLongListener;
        }

        @Override
        public void onClick(View v) {
            onCardListener.onCardClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return onCardLongListener.onCardLongClick(getAdapterPosition());
        }
    }

    public interface OnCardListener
    {
        void onCardClick(int position);
    }

    public interface OnCardLongListener
    {
        boolean onCardLongClick(int position);
    }

    public RecyclerViewAdapter(ArrayList<CardView> card_view_list, OnCardListener onCardListener, OnCardLongListener onCardLongListener)
    {
        this.card_view_list = card_view_list;
        card_view_list_full = new ArrayList<>(card_view_list);

        this.on_card_listener = onCardListener;
        this.on_card_long_listener = onCardLongListener;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View card_inflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        CardViewHolder card_view_holder = new CardViewHolder(card_inflated, on_card_listener, on_card_long_listener);
        return card_view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int index)
    {
        CardView current_item = card_view_list.get(index);

        String card_name = current_item.getCardName();
        Integer requirement = current_item.getRequirement();
        Integer tag1 = current_item.getTag1();
        Integer tag2 = current_item.getTag2();
        Integer tag3 = current_item.getTag3();
        Integer tag4 = current_item.getTag4();

        holder.card_name_view.setText(card_name);
        holder.requirement_view.setImageResource(requirement);
        holder.tag1_view.setImageResource(tag1);
        holder.tag2_view.setImageResource(tag2);
        holder.tag3_view.setImageResource(tag3);
        holder.tag4_view.setImageResource(tag4);
    }

    private Filter filter = new Filter()
    {
        @Override
        protected FilterResults performFiltering(CharSequence search_string)
        {
            List<CardView> filtered_list = new ArrayList<>();
            FilterResults results = new FilterResults();

            //Filtering
            if (search_string == null || search_string.length() == 0)
            {
                filtered_list.addAll(card_view_list_full);
                results.values = filtered_list;
                return results;
            }

            String[] keywords = search_string.toString().toLowerCase().trim().split(" ");
            for (CardView card_view : card_view_list_full)
            {
                String card_name = card_view.getCardName();
                String regex = ".*";
                for (String word : keywords)
                {
                    regex += word + ".*";
                }

                if (card_name.toLowerCase().matches(regex))
                {
                    filtered_list.add(card_view);
                }
            }
            results.values = filtered_list;
            return results;
        }

        //Updating the cardview
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            try {
                card_view_list.clear();
                card_view_list.addAll((List) results.values);
                notifyDataSetChanged();
            } catch (Exception e) { System.out.println("Alex,debug: virhe recyclerviewadapterin publishresults"); }
        }
    };

    @Override
    public int getItemCount() { return card_view_list.size(); }


    public Filter getFilter() { return filter; }

}
