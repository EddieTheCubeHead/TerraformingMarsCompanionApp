package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Comet extends Card {
    public Comet(Game game) {
        super(Type.RED, game);
        name = "Comet";
        price = 21;
        tags.add(Tag.EVENT);
        tags.add(Tag.SPACE);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, owner_game));
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
    }


    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            Player target = GameController.getPlayer(data);
            target.getResources().setPlants(target.getResources().getPlants() - 3);
        }
        owner_game.raiseTemperature(player);
    }
}
