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
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;

public final class ImportedHydrogen extends Card {
    public ImportedHydrogen(Game game) {
        super(Type.RED, game);
        name = "Imported hydrogen";
        price = 16;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Choose which resource to add:");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Plants (x3)");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Microbes (x3)");
        intent.putExtra(BooleanDialogActivity.EXTRA_TEXT_1, "Animals (x2)");
        context.startActivity(intent);
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        if (data != 0) {

        }
    }

    //TODO selvitä missä välissä mikrobit määritetään

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.changePlants(3);
        }
        super.onPlay(player);
    }
}
