package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class BusinessContact extends Card {
    public BusinessContact(Game game) {
        super(Type.RED, game);
        name = "Business contact";
        price = 7;
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        GameController.getInstance().addUiEvent(new PromptEvent("Please look 4 top cards on draw pile and choose 2 of them"));
        super.playWithMetadata(player, data);
    }
}
