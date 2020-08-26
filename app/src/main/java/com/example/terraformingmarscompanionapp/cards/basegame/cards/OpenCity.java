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

public final class OpenCity extends Card {
    public OpenCity() {
        super(Type.GREEN);
        name = "Open city";
        price = 23;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMinOxygen(12);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.CITY, game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(4);
        player.getResources().setPlants(player.getResources().getPlants() + 2);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
