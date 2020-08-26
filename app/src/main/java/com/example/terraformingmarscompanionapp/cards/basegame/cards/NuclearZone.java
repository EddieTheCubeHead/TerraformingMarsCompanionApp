package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class NuclearZone extends Card {
    public NuclearZone() {
        super(Type.GREEN);
        name = "Nuclear zone";
        price = 10;
        tags.add(Tag.EARTH);
        victory_points = -2;
        Card.game = game;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.NUCLEAR_ZONE, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.raiseTemperature(player);
        game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
