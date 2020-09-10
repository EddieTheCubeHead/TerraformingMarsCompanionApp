package com.example.terraformingmarscompanionapp.ui.cardRecyclerAdapterClasses;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.dialogues.CardCostDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A childclass of {@link CardParentRecyclerAdapter} for the UI responsible for displaying cards
 * when searching for a card to play.
 *
 * @author Eetu Asikainen, Aleksanteri Reijo
 * @version 0.3
 * @since 0.3
 */
public class CardBuyRecyclerAdapter extends CardParentRecyclerAdapter {
    private static final ArrayList<Type> VALID_TYPES = new ArrayList<>(Arrays.asList(Type.BLUE, Type.RED, Type.GREEN));

    @Override
    protected Boolean isValidCard(Card card) {
        return (VALID_TYPES.contains(card.getType()) && card.getOwner() == null);
    }

    @Override
    public void onCardClick(Card card) {
        try {
            GameController.saveGame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CardCostDialog.displayDialog(GameController.getContext(), card);
    }
}
