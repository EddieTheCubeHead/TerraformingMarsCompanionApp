package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;

public final class DeimosDown extends Card {
    public DeimosDown() {
        super(Type.RED);
        name = "Deimos down";
        price = 31;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        if (data != 0) {
            Player target = GameController.getPlayer(data);
            target.getResources().setPlants(target.getResources().getPlants() - 8);
        }
        player.getResources().setSteel(player.getResources().getSteel() + 4);
        game.raiseTemperature(player);
        game.raiseTemperature(player);
        game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
