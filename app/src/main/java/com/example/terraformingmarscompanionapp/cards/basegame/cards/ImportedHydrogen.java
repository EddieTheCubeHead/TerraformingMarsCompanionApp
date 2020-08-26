package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class ImportedHydrogen extends Card {
    public ImportedHydrogen() {
        super(Type.RED);
        name = "Imported hydrogen";
        price = 16;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose resource to recieve:",
                new ArrayList<>(Arrays.asList("Plants (x3)", "Microbes (x3)", "Animals (x2)")), this, ChoiceDialog.UseCase.GENERAL));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, game));
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (data == 1) {
            EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.MICROBE, player, 3));
        } else if (data == 2) {
            EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.ANIMAL, player, 2));
        }
        super.onPlayServerHook(player, data);
    }


    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.getResources().setPlants(player.getResources().getPlants() + 3);
        }
        super.finishPlay(player);
    }
}
