package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class Virus extends Card {
    public Virus(Game game) {
        super(Type.RED, game);
        name = "Virus";
        price = 1;
        tags.add(Tag.MICROBE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose resource to remove",
                new ArrayList<>(Arrays.asList("Animals (x2)", "Plants (x5)")), this, ChoiceDialog.USE_CASE.GENERAL));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (data == 0) {
            EventScheduler.addEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, -2));
            EventScheduler.playNextEvent(GameController.getContext());
            return;
        } else if (data == 1) {
            ArrayList<String> player_names = new ArrayList<>();
            for (Player target : GameController.getPlayers()) {
                player_names.add(target.getName());
            }
            EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target",
                    player_names, this, ChoiceDialog.USE_CASE.VIRUS));
            EventScheduler.playNextEvent(GameController.getContext());
            return;
        } else {
            data -= 2;
        }
        super.onPlayServerHook(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data > 0) {
            GameController.getPlayer(data).takePlants(5);
        }
        super.playWithMetadata(player, data);
    }
}
