package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class GiantIceAsteroid extends Card {
    public GiantIceAsteroid(Game game) {
        super(Type.RED, game);
        name = "Giant ice asteroid";
        price = 36;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, owner_game));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, owner_game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            Player target = GameController.getPlayer(data);
            target.getResources().setPlants(target.getResources().getPlants() - 6);
        }
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
