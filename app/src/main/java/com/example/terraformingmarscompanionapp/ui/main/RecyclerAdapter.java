package com.example.terraformingmarscompanionapp.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapteri joka hakusuodattaa ja pitää card tietoja.
 */

//debug: uses unchecked or unsafe operations
//Note: Recompile with -Xlint:unchecked for details
//TODO mieti jos cardviewn voi korjata vaan cardilla ja onko performanssimaksua
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable
{
    private ArrayList<CardView> card_view_list;
    private ArrayList<CardView> card_view_list_full;

    private OnCardListener on_card_listener;
    private OnCardLongListener on_card_long_listener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        OnCardListener onCardListener;
        OnCardLongListener onCardLongListener;

        //layout ei käytössä ehkä
        public TextView card_name_view;
        public ImageView requirement_view;
        public ImageView tag1_view;
        public ImageView tag2_view;
        public ImageView tag3_view;
        public ImageView tag4_view;

        //constructorissa tehdään kortille viittaukset
        public ViewHolder(@NonNull View card_view, OnCardListener onCardListener, OnCardLongListener onCardLongListener)
        {
            super(card_view);
            card_name_view = card_view.findViewById(R.id.card_name);
            requirement_view = card_view.findViewById(R.id.requirement);
            tag1_view = card_view.findViewById(R.id.tag1);
            tag2_view = card_view.findViewById(R.id.tag2);
            tag3_view = card_view.findViewById(R.id.tag3);
            tag4_view = card_view.findViewById(R.id.tag4);

            //klikki
            card_view.setOnClickListener(this);
            this.onCardListener = onCardListener;
            //pitkä klikki
            card_view.setOnLongClickListener(this);
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

    public RecyclerAdapter(ArrayList<CardView> card_view_list, OnCardListener onCardListener, OnCardLongListener onCardLongListener)
    {
        this.card_view_list = card_view_list;
        card_view_list_full = new ArrayList<>(card_view_list);

        this.on_card_listener = onCardListener;
        this.on_card_long_listener = onCardLongListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        ViewHolder card_view_holder = new ViewHolder(view, on_card_listener, on_card_long_listener);
        return card_view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index)
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

            //hakua varten clause
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

        //päivittää cardviewtä notifydatasetchangedillä
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            try {
                card_view_list.clear();
                card_view_list.addAll((List) results.values);
                notifyDataSetChanged();
            } catch (Exception e) { System.out.println("Alex,debug: virhe RecyclerAdapterin publishresults"); }
        }
    };

    @Override
    public int getItemCount() { return card_view_list.size(); }


    public Filter getFilter() { return filter; }

}
