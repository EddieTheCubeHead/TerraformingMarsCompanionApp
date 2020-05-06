package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;

public final class LocalHeatTrapping extends Card {
    public LocalHeatTrapping(Game game) {
        super(Type.RED, game);
        name = "Local heat trapping";
        price = 1;
        tags.add(Tag.EVENT);
        requirements.setMinHeat(5);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Choose which resource to gain:");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Plants (x4)");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Animals (x2)");
        context.startActivity(intent);
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().addUiEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, 2, true));
        }
        super.playServerConnection(player, data);
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
