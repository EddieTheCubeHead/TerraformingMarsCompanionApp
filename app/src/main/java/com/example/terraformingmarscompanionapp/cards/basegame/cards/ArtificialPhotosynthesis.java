package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class ArtificialPhotosynthesis extends Card {
    public ArtificialPhotosynthesis(Game game) {
        super(Type.GREEN, game);
        name = "Artificial photosynthesis";
        price = 12;
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void onPlay(Player player, Context context) {
        ArrayList<String> choices = new ArrayList<>(Arrays.asList("Plants (x1)", "Energy (x2)"));

        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose which production to raise:", choices, this, ChoiceDialog.USE_CASE.GENERAL));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            production_box.setPlantsProduction(1);
        } else {
            production_box.setEnergyProduction(2);
        }
        super.playWithMetadata(player, data);
    }
}
