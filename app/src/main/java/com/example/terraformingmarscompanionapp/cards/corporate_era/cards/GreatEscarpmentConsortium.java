package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;

public final class GreatEscarpmentConsortium extends Card {
    public GreatEscarpmentConsortium() {
        super(Type.GREEN);
        name = "Great escarpment consortium";
        price = 6;
        requirements.setMinSteelProduction(1);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setStealSteelProduction(1);
        production_box.setSteelProduction(1);
        super.playWithMetadata(player, data);
    }
}
