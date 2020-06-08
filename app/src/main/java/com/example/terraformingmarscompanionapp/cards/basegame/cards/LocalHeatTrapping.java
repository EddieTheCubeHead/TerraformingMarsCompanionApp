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
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class LocalHeatTrapping extends Card {
    public LocalHeatTrapping(Game game) {
        super(Type.RED, game);
        name = "Local heat trapping";
        price = 1;
        tags.add(Tag.EVENT);
        requirements.setMinHeat(5);
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose resource to recieve:",
                new ArrayList<>(Arrays.asList("Plants (x4)", "Animals (x2)")), this, ChoiceDialog.USE_CASE.GENERAL));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (data != 0) {
            EventScheduler.addEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, 2, true));
        }
        super.onPlayServerHook(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHeat(-5);
        if (data == 0) {
            player.changePlants(4);
        }
        super.playWithMetadata(player, data);
    }
}
