package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;
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
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose resource to remove",
                new ArrayList<>(Arrays.asList("Animals (x2)", "Plants (x5)")), this, ChoiceDialog.UseCase.GENERAL));
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (data == 0) {
            EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.ANIMAL, player, -2));
            EventScheduler.playNextEvent(GameController.getContext());
            return;
        } else if (data == 1) {
            ArrayList<String> player_names = new ArrayList<>();
            for (Player target : GameController.getPlayers()) {
                player_names.add(target.getName());
            }
            EventScheduler.addEvent(new MetadataChoiceEvent("Choose your target",
                    player_names, this, ChoiceDialog.UseCase.VIRUS));
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
            Player target = GameController.getPlayer(data);
            target.getResources().setPlants(target.getResources().getPlants() - 5);
        }
        super.playWithMetadata(player, data);
    }
}
