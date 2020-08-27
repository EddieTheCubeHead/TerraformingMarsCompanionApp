package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class DomedCrater extends Card {
    public DomedCrater() {
        super(Type.GREEN);
        name = "Domed crater";
        price = 24;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMaxOxygen(7);
        victory_points = 1;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.CITY, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setPlants(player.getResources().getPlants() + 3);
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(3);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
