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

public final class EonChasmaNationalPark extends Card {
    public EonChasmaNationalPark(Game game) {
        super(Type.GREEN, game);
        name = "Eon chasma national park";
        price = 16;
        tags.add(Tag.PLANT);
        tags.add(Tag.BUILDING);
        requirements.setMinTemperature(-12);
        victory_points = 1;

    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.ANIMAL, player, 1, true));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setPlants(player.getResources().getPlants() + 3);
        production_box.setMoneyProduction(2);
        super.playWithMetadata(player, data);
    }
}
