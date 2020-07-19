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

public final class NoctisCity extends Card {
    public NoctisCity(Game game) {
        super(Type.GREEN, game);
        name = "Noctis city";
        price = 18;
        tags.add(Tag.CITY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.NOCTIS, owner_game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(3);
        super.playWithMetadata(player, data);
    }
}
