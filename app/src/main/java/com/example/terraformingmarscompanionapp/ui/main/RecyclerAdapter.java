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

import com.example.terraformingmarscompanionapp.Card;
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
    private ArrayList<Card> card_list;
    private ArrayList<Card> card_list_full;

    private OnCardListener onCardListener;
    private OnCardLongListener onCardLongListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        OnCardListener on_card_listener;
        OnCardLongListener on_card_long_listener;

        //layout ei käytössä ehkä
        public TextView card_name_view;
        public ImageView requirement_view;
        public ImageView tag1_view;
        public ImageView tag2_view;
        public ImageView tag3_view;
        public ImageView tag4_view;

        //constructorissa tehdään kortille viittaukset
        public ViewHolder(@NonNull View card_inflated, OnCardListener onCardListener, OnCardLongListener onCardLongListener)
        {
            super(card_inflated);
            card_name_view = card_inflated.findViewById(R.id.card_name);
            requirement_view = card_inflated.findViewById(R.id.requirement);
            tag1_view = card_inflated.findViewById(R.id.tag1);
            tag2_view = card_inflated.findViewById(R.id.tag2);
            tag3_view = card_inflated.findViewById(R.id.tag3);
            tag4_view = card_inflated.findViewById(R.id.tag4);

            //klikkiominaisuus
            card_inflated.setOnClickListener(this);
            this.on_card_listener = onCardListener;
            //pitkä klikki
            card_inflated.setOnLongClickListener(this);
            this.on_card_long_listener = onCardLongListener;
        }

        @Override
        public void onClick(View v) {
            on_card_listener.onCardClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return on_card_long_listener.onCardLongClick(getAdapterPosition());
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

    public RecyclerAdapter(ArrayList<Card> card_list, OnCardListener onCardListener, OnCardLongListener onCardLongListener)
    {
        this.card_list = card_list;
        card_list_full = new ArrayList<>(card_list);

        this.onCardListener = onCardListener;
        this.onCardLongListener = onCardLongListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View card_inflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        ViewHolder card_view_holder = new ViewHolder(card_inflated, onCardListener, onCardLongListener);
        return card_view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index)
    {
        Card card = card_list.get(index);

        String card_name = card.getName();
        Integer requirement = card.getRequirementInt();

        Integer tag1, tag2, tag3, tag4;
        ArrayList<Integer> tags = card.getTagIntegers();
        try {
            holder.tag1_view.setImageResource(tags.get(0));
            holder.tag2_view.setImageResource(tags.get(1));
            holder.tag3_view.setImageResource(tags.get(2));
            holder.tag4_view.setImageResource(tags.get(3));

        } catch (IndexOutOfBoundsException e) {}

        holder.card_name_view.setText(card_name);
        holder.requirement_view.setImageResource(requirement);
    }

    private Filter filter = new Filter()
    {
        @Override
        protected FilterResults performFiltering(CharSequence search_string)
        {
            List<Card> filtered_list = new ArrayList<>();
            FilterResults results = new FilterResults();

            //hakua varten clause
            if (search_string == null || search_string.length() == 0)
            {
                filtered_list.addAll(card_list_full);
                results.values = filtered_list;
                return results;
            }

            String[] keywords = search_string.toString().toLowerCase().trim().split(" ");
            for (Card card : card_list_full)
            {
                String card_name = card.getName();
                String regex = ".*";
                for (String word : keywords)
                {
                    regex += word + ".*";
                }

                if (card_name.toLowerCase().matches(regex))
                {
                    filtered_list.add(card);
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
                card_list.clear();
                card_list.addAll((List) results.values);
                notifyDataSetChanged();
            } catch (Exception e) { System.out.println("Alex,debug: virhe RecyclerAdapterin publishresults"); }
        }
    };

    @Override
    public int getItemCount() { return card_list.size(); }

    public Filter getFilter() { return filter; }

}
