package com.example.terraformingmarscompanionapp.cards.basegame.cards;

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
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;

import java.util.ArrayList;
import java.util.Arrays;

public final class LargeConvoy extends Card {
    public LargeConvoy(Game game) {
        super(Type.RED, game);
        name = "Large convoy";
        price = 36;
        tags.add(Tag.SPACE);
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        victory_points = 2;
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose resource to recieve:",
                new ArrayList<>(Arrays.asList("Plants (x5)", "Animal (x4)")), this, ChoiceDialog.UseCase.GENERAL));
        EventScheduler.addEvent(new PromptEvent("Please draw 2 cards"));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.OCEAN, owner_game));
    }

    @Override
    public void onPlayServerHook(Player player, Integer data) {
        if (GameController.getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getName(), player.getName(), data));
        }
        if (data == 1) {
            EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.ANIMAL, player, 4, true));
        }
        playWithMetadata(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.getResources().setPlants(player.getResources().getPlants() + 5);
        }
        player.changeHandSize(2);
        super.playWithMetadata(player, data);
    }
}
