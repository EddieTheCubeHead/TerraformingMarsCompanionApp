package com.example.terraformingmarscompanionapp.ui.cardRecyclerAdapterClasses;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.terraformingmarscompanionapp.R;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An abstract parent class to implement when creating card-based recyclerviews with different
 * filtering conditions
 * <p></p>
 * Original code for multi-purpose RecyclerAdapter displaying cards written by Aleksanteri. Later
 * modified by Eetu, breaking the monolithic class into subclasses and a parent class and cleaning
 * the code to better make use of the optimizations and changes implemented by Eetu later in development.
 *
 * @author Aleksanteri Reijo, Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public abstract class CardParentRecyclerAdapter extends RecyclerView.Adapter<CardParentRecyclerAdapter.CardViewHolder> implements Filterable {

    // A list of cards matching the given conditions
    protected ArrayList<Card> card_list;

    /**
     * Constructor
     */
    public CardParentRecyclerAdapter() {
        for (Card card : GameController.getGame().getDeck().values()) {
            if (isValidCard(card)) {
                card_list.add(card);
            }
        }
    }

    /**
     * @return {@link ArrayList} of {@link Card} representing a copy of the cards in the card list of the class
     */
    public ArrayList<Card> getCardList() {
        return new ArrayList<>(card_list);
    }

    /**
     * An abstract method to be implemented by childclasses for filtering the base set of cards while
     * the class specific filter handles subsequent, finer filtering.
     *
     * @param card {@link Card} which validity should be checked
     * @return {@link Boolean} representing whether the given card meets the requirements or not
     */
    protected abstract Boolean isValidCard(Card card);

    /**
     * ViewHolder class implemented inside the RecyclerAdapter class it interacts with, as per android
     * guidelines
     *
     * @author Aleksanteri Reijo, Eetu Asikainen
     */
    public static class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardParentRecyclerAdapter listener;

        private Card card;

        public TextView card_credit_view;
        public TextView card_name_view;
        public ImageView requirement_view;
        public ImageView tag1_view;
        public ImageView tag2_view;
        public ImageView tag3_view;
        public ImageView tag4_view;

        public ImageView type_view_mid;
        public TextView token_view;

        /**
         * Constructor
         *
         * @param card_inflated {@link View} that should get bound to this instance of ViewHolder
         */
        public CardViewHolder(@NonNull View card_inflated, CardParentRecyclerAdapter listener) {
            super(card_inflated);

            this.listener = listener;

            card_credit_view = card_inflated.findViewById(R.id.card_credit_text);
            card_name_view = card_inflated.findViewById(R.id.card_name);
            requirement_view = card_inflated.findViewById(R.id.requirement);
            tag1_view = card_inflated.findViewById(R.id.tag1);
            tag2_view = card_inflated.findViewById(R.id.tag2);
            tag3_view = card_inflated.findViewById(R.id.tag3);
            tag4_view = card_inflated.findViewById(R.id.tag4);


            // Card type is implied by tinting an imageview
            type_view_mid = card_inflated.findViewById(R.id.type_image_mid);
            token_view = card_inflated.findViewById(R.id.token_text);
        }

        void setCard(Card card) {
            this.card = card;
        }

        /**
         * Overriding onClick to present easy access to customising card click behavior in the childclasses
         * by modifing their implemention of {@link #onCardClick(Card)}.
         *
         * @param view {@link View} that was clicked
         */
        @Override
        public void onClick(View view) {
            listener.onCardClick(card);
        }

    }

    // Javadoc for params for this method basically copied from android studio documentation as I
    // didn't really have anything to add to it
    /**
     * @param parent {@link ViewGroup} into which the View will be added
     * @param viewType primitive integer representing the type of the new View
     * @return {@link CardViewHolder} representing the complete view holder created
     */
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View card_inflated = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        CardViewHolder card_view_holder = new CardViewHolder(card_inflated, this);
        return card_view_holder;
    }

    /**
     * @param holder {@link androidx.recyclerview.widget.RecyclerView.ViewHolder} to be bound into the UI
     * @param index primitive integer representing the index of the card being bound in the global card
     *              list
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int index) {
        Card card = card_list.get(index);
        holder.setCard(card);

        String card_name = card.getName();
        Integer requirement = card.getRequirementInt();

        // Setting the layout's resources at reload.
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

        // Type color
        int color;

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

    /**
     * Providing a common way to filter cards based on strings and regex, with child-specific behavior
     * being implemented in the {@link #isValidCard(Card)} method.
     */
    protected final Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence search_string)
        {
            List<Card> filtered_list = new ArrayList<>();
            FilterResults results = new FilterResults();

            String[] keywords = search_string.toString().toLowerCase().trim().split(" ");
            for (Card card : card_list) {

                if (!isValidCard(card)) {
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

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            try {
                card_list.clear();
                card_list.addAll((List) results.values);
                notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * An abstract method that defines behavior when clicking on a card in the RecyclerView created
     * by a childclass
     *
     * @param card {@link Card} that was represented by the ViewHolder clicked
     */
    public abstract void onCardClick(Card card);

    /**
     * An abstract method for returning a filter unique to the childclass
     *
     * @return {@link Filter} detailing how to display cards in view represented by child class.
     */
    public Filter getFilter() {
        return filter;
    }

    @Override
    public int getItemCount() { return card_list.size(); }
}
