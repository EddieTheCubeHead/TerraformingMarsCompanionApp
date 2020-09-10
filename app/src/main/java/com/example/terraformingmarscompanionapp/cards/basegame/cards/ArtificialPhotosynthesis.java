package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.dialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class ArtificialPhotosynthesis extends Card {
    public ArtificialPhotosynthesis() {
        super(Type.GREEN);
        name = "Artificial photosynthesis";
        price = 12;
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void initializePlayEvents(Player player) {
        ArrayList<String> choices = new ArrayList<>(Arrays.asList("Plants (x1)", "Energy (x2)"));

        EventScheduler.addEvent(new MetadataChoiceEvent("Choose which production to raise:", choices, this, ChoiceDialog.UseCase.GENERAL));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        if (data == 0) {
            production_box.setPlantsProduction(1);
        } else {
            production_box.setEnergyProduction(2);
        }
        super.playWithMetadata(player, data);
    }
}
