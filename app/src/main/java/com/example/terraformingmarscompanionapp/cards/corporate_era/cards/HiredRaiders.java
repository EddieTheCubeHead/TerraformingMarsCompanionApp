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

public final class HiredRaiders extends Card {
    public HiredRaiders(Game game) {
        super(Type.RED, game);
        name = "Hired raiders";
        price = 1;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void initializePlayEvents(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose which resource to steal:",
                new ArrayList<>(Arrays.asList("Steel (x2)", "Money (x3)")), this, ChoiceDialog.USE_CASE.GENERAL));
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (data < 3) {
            ArrayList<String> player_names = new ArrayList<>();
            for (Player target : GameController.getPlayers()) {
                player_names.add(target.getName());
            }
            if (data != 0) {
                EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target:",
                        player_names, this, ChoiceDialog.USE_CASE.RAIDERS_STEEL));
            } else {
                EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target:",
                        player_names, this, ChoiceDialog.USE_CASE.RAIDERS_MONEY));
            }
            EventScheduler.playNextEvent(GameController.getContext());

        } else {
            data -= 3;
            super.onPlayServerHook(player, data);
        }
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer steal_amount;
        Player target;
        if (data > 0 && data < 6) {
            target = GameController.getPlayer(data);
            steal_amount = target.getResources().getSteel() > 2 ? 2 : target.getResources().getSteel();

            target.getResources().setSteel(target.getResources().getSteel() - steal_amount);
            player.getResources().setSteel(player.getResources().getSteel() + steal_amount);

        } else if (data >= 6) {
            data -= 5;
            target = GameController.getPlayer(data);
            steal_amount = target.getResources().getMoney() > 3 ? 3 : target.getResources().getMoney();

            target.getResources().setMoney(target.getResources().getMoney() - steal_amount);
            player.getResources().setMoney(player.getResources().getMoney() + steal_amount);
        }
        super.playWithMetadata(player, data);
    }
}
