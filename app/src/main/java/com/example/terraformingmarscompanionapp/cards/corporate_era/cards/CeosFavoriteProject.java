package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class CeosFavoriteProject extends Card {
    public CeosFavoriteProject() {
        super(Type.RED);
        name = "CEO's favorite project";
        price = 1;
        tags.add(Tag.EVENT);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.EXISTING, player, 1, true));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        super.playWithMetadata(player, data);
    }
}
