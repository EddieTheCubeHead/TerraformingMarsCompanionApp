package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class Sabotage extends Card {
    public Sabotage(Game game) {
        super(Type.GREEN, game);
        name = "Sabotage";
        price = 1;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose which resource you want to sabotage",
                new ArrayList<>(Arrays.asList("Titanium (x3), Steel (x4), Money (x7)")), this, ChoiceDialog.USE_CASE.GENERAL));
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (data < 3) {
            ArrayList<String> player_names = new ArrayList<>();
            for (Player target : GameController.getPlayers()) {
                player_names.add(target.getName());
            }
            if (data == 0) {
                EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target:",
                        player_names, this, ChoiceDialog.USE_CASE.SABOTAGE_TITANIUM));
            } else if (data == 1) {
                EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target:",
                        player_names, this, ChoiceDialog.USE_CASE.SABOTAGE_STEEL));
            } else {
                EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target:",
                        player_names, this, ChoiceDialog.USE_CASE.SABOTAGE_MONEY));
            }
            EventScheduler.playNextEvent(GameController.getContext());

        } else {
            data -= 3;
            super.onPlayServerHook(player, data);
        }
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Player target;
        if (data > 0 && data < 6) {
            target = GameController.getPlayer(data);
            target.getResources().setTitanium(target.getResources().getTitanium() - 3);

        } else if (data >= 6 && data < 11) {
            data -= 5;
            target = GameController.getPlayer(data);
            target.getResources().setSteel(target.getResources().getSteel() - 4);

        } else if (data >= 11) {
            data -= 10;
            target = GameController.getPlayer(data);
            target.getResources().setMoney(target.getResources().getMoney() - 7);

        }
        super.playWithMetadata(player, data);
    }
}
