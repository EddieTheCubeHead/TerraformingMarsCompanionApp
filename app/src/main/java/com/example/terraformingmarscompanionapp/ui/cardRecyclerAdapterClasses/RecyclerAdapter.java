package com.example.terraformingmarscompanionapp.ui.cardRecyclerAdapterClasses;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An adapter that filters and updates a recyclerview that contains cards.
 *
 * Obsolete after UI rework
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private ArrayList<Card> card_list = new ArrayList<>(GameController.getGame().getAllCards().values());

    private OnCardListener onCardListener;
    private OnCardLongListener onCardLongListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        OnCardListener on_card_listener;
        OnCardLongListener on_card_long_listener;

        public TextView card_credit_view;
        public TextView card_name_view;
        public ImageView requirement_view;
        public ImageView tag1_view;
        public ImageView tag2_view;
        public ImageView tag3_view;
        public ImageView tag4_view;

        public ImageView type_view_mid;
        public TextView token_view;

        public ViewHolder(@NonNull View card_inflated, OnCardListener onCardListener, OnCardLongListener onCardLongListener)
        {
            super(card_inflated);

            card_credit_view = card_inflated.findViewById(R.id.card_credit_text);
            card_name_view = card_inflated.findViewById(R.id.card_name);
            requirement_view = card_inflated.findViewById(R.id.requirement);
            tag1_view = card_inflated.findViewById(R.id.tag1);
            tag2_view = card_inflated.findViewById(R.id.tag2);
            tag3_view = card_inflated.findViewById(R.id.tag3);
            tag4_view = card_inflated.findViewById(R.id.tag4);


            // card typing is done by tinting an imageview
            type_view_mid = card_inflated.findViewById(R.id.type_image_mid);
            token_view = card_inflated.findViewById(R.id.token_text);

            // short click is implemented in the calling class
            card_inflated.setOnClickListener(this);
            this.on_card_listener = onCardListener;

            // same for the long click
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

    public interface OnCardListener {
        void onCardClick(int position);
    }

    public interface OnCardLongListener {
        boolean onCardLongClick(int position);
    }


    public RecyclerAdapter(OnCardListener onCardListener, OnCardLongListener onCardLongListener) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index)
    {
        Card card = card_list.get(index);

        String card_name = card.getName();
        Integer requirement = card.getRequirementInt();

        //setting the layout's resources at reload.
        ArrayList<Integer> tags = card.getTagIntegers();

        try {
            holder.tag1_view.setImageResource(tags.get(0));
            holder.tag2_view.setImageResource(tags.get(1));
            holder.tag3_view.setImageResource(tags.get(2));
            holder.tag4_view.setImageResource(tags.get(3));
        } catch (IndexOutOfBoundsException ignored) {}

        holder.card_name_view.setText(card_name);
        holder.card_credit_view.setText(card.getPrice().toString());
        holder.requirement_view.setImageResource(requirement);

        //type color
        Integer color;

        switch (card.getType())
        {
            case RED:     color = 0xffff2800;   break;
            case GREEN:   color = 0xff059c48; break;
            case BLUE:    color = 0xff6be1ff;  break;
            case PRELUDE: color = 0xffff66ff; break;
            case CORPORATION: color = 0xff222222; break;
            case AWARD:   color = 0xffff9900; break;
            case MILESTONE:
            case STANDARD_PROJECT: color = 0xffd4d4d4; break;
            default: color = 0x80000000; break;
        }

        holder.type_view_mid.setColorFilter(Color.argb(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color)));

        holder.token_view.setText(card.getResourceText());
    }

    private Filter play_search_filter = new Filter() {
        private List<Type> valid_types = Arrays.asList(Type.RED, Type.BLUE, Type.GREEN);

        @Override
        protected FilterResults performFiltering(CharSequence search_string)
        {
            ArrayList<Card> card_list_full = new ArrayList<>(GameController.getGame().getAllCards().values());

            List<Card> filtered_list = new ArrayList<>();
            FilterResults results = new FilterResults();

            String[] keywords = search_string.toString().toLowerCase().trim().split(" ");
            for (Card card : card_list_full) {
                // removing owned cards and non-valid typed cards from the buy menu
                if (card.getOwner() != null || !valid_types.contains(card.getType())) {
                    continue;
                }

                String card_name = card.getName();
                StringBuilder regex = new StringBuilder(".*");
                for (String word : keywords) {
                    regex.append(word).append(".*");
                }

                if (card_name.toLowerCase().matches(regex.toString())) {
                    filtered_list.add(card);
                }
            }
            results.values = filtered_list;
            return results;
        }

        //updates view
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

    private Filter special_filter = new Filter() {

        ArrayList<Type> valid_types = new ArrayList<>(Arrays.asList(Type.AWARD, Type.MILESTONE, Type.STANDARD_PROJECT));

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Card> card_list_full = new ArrayList<>(GameController.getGame().getAllCards().values());
            List<Card> filtered_list = new ArrayList<>();
            FilterResults results = new FilterResults();

            for (Card card : card_list_full) {
                if (valid_types.contains(card.getType())) {
                    filtered_list.add(card);
                }
            }

            results.values = filtered_list;
            return results;
        }

        //updates view
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

    // filters to GameController.displayplayer 's cards.
    private Filter played_filter = new Filter()
    {
        @Override
        protected FilterResults performFiltering(CharSequence search_string)
        {
            ArrayList<Card> card_list_full = new ArrayList<>(GameController.getGame().getAllCards().values());
            Player display_player = GameController.getDisplayPlayer();

            List<Card> filtered_list = new ArrayList<>();
            FilterResults results = new FilterResults();

            for (Card card : card_list_full)
            {
                if (card.getOwner() == null || !card.getOwner().getName().equals(display_player.getName())) {
                    continue;
                }

                Log.i("RecyclerAdapter", "Added a card to played filter, owner: " + display_player.getName() + " name: " + card.getName());
                filtered_list.add(card);
            }

            results.values = filtered_list;
            return results;
        }

        //updates view
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

    public ArrayList<Card> getCardList() {
        return card_list;
    }

    public Filter getFilter() {
        return play_search_filter;
    }

    public Filter getPlayedFilter() {
        return played_filter;
    }

    public Filter getSpecialFilter() {
        return special_filter;
    }

}
