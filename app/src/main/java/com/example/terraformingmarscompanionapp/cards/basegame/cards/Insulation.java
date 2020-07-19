package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataIntegerEvent;

public final class Insulation extends Card {
    public Insulation(Game game) {
        super(Type.GREEN, game);
        name = "Insulation";
        price = 2;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new MetadataIntegerEvent("Give production change amount:", 0, player.getResources().getHeatProduction(), this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(-data);
        production_box.setMoneyProduction(data);
        super.playWithMetadata(player, data);
    }
}
