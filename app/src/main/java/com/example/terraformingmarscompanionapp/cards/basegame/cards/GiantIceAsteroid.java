package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

public final class GiantIceAsteroid extends Card {
    public GiantIceAsteroid(Game game) {
        super(Type.RED, game);
        name = "Giant ice asteroid";
        price = 36;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        Context context = GameController.getContext();
        Intent intent = new Intent(context, ChoiceDialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(ChoiceDialog.CARD_INTENT, this.getName());
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getPlayer(data).takePlants(6);
        }
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
