package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class ImportedHydrogen extends Card {
    public ImportedHydrogen(Game game) {
        super(Type.RED, game);
        name = "Imported hydrogen";
        price = 16;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose resource to recieve:",
                new ArrayList<>(Arrays.asList("Plants (x3)", "Microbes (x3)", "Animals (x2)")), this, ChoiceDialog.USE_CASE.GENERAL));
        EventScheduler.addEvent(new TileEvent(Placeable.OCEAN, owner_game));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (data == 1) {
            EventScheduler.addEvent(new ResourceEvent(ResourceCard.ResourceType.MICROBE, player, 3));
        } else if (data == 2) {
            EventScheduler.addEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, 2));
        }
        super.onPlayServerHook(player, data);
    }


    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.changePlants(3);
        }
        super.finishPlay(player);
    }
}
