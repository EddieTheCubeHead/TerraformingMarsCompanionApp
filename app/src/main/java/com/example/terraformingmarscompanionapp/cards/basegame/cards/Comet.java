package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;

public final class Comet extends Card {
    public Comet(Game game) {
        super(Type.RED, game);
        name = "Comet";
        price = 21;
        tags.add(Tag.EVENT);
        tags.add(Tag.SPACE);
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new TileEvent(Placeable.OCEAN, owner_game));
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
        EventScheduler.playNextEvent(context);
    }


    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getPlayer(data).takePlants(3);
        }
        owner_game.raiseTemperature(player);
    }
}
