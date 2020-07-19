package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;

public final class Asteroid extends Card {
    public Asteroid(Game game) {
        super(Type.RED, game);
        name = "Asteroid";
        price = 14;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
           Player target = GameController.getPlayer(data);
           target.getResources().setPlants(target.getResources().getPlants() - 3);
        }
        owner_game.raiseTemperature(player);
        player.getResources().setTitanium(player.getResources().getTitanium() + 2);
        super.playWithMetadata(player, data);
    }
}
