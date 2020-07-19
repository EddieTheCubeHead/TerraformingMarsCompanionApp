package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
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
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new PromptEvent("Please look 4 top cards on draw pile and choose 2 of them"));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHandSize(2);
        super.playWithMetadata(player, data);
    }
}
