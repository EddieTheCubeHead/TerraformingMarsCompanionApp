package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class TechnologyDemostration extends Card {
    public TechnologyDemostration(Game game) {
        super(Type.RED, game);
        name = "Technology Demonstration";
        price = 5;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }



    @Override
    public void playWithMetadata(Player player, Integer data) {
        GameController.getInstance().addUiEvent(new PromptEvent("Please draw 2 cards"));
        super.playWithMetadata(player, data);
    }
}
