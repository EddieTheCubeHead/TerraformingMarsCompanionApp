package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class LocalHeatTrapping extends Card {
    public LocalHeatTrapping() {
        super(Type.RED);
        name = "Local heat trapping";
        price = 1;
        tags.add(Tag.EVENT);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose resource to recieve:",
                new ArrayList<>(Arrays.asList("Plants (x4)", "Animals (x2)")), this, ChoiceDialog.UseCase.GENERAL));
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) throws InvalidResourcesException {
        if (data != 0) {
            EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.ANIMAL, player, 2, true));
        }
        super.onPlayServerHook(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setHeat(player.getResources().getHeat() - 5);
        if (data == 0) {
            player.getResources().setPlants(player.getResources().getPlants() + 4);
        }
        super.playWithMetadata(player, data);
    }
}
