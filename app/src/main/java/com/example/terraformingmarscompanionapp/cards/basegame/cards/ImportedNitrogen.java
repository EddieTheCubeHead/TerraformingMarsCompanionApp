package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;

public final class ImportedNitrogen extends Card {
    public ImportedNitrogen() {
        super(Type.RED);
        name = "Imported nitrogen";
        price = 23;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.MICROBE, player, 3, true));
        EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.ANIMAL, player, 2, true));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 1);
        player.getResources().setPlants(player.getResources().getPlants() + 4);
        super.playWithMetadata(player, data);
    }
}
