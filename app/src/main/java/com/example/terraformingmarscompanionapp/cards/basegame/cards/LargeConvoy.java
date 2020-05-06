package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

public final class LargeConvoy extends Card {
    public LargeConvoy(Game game) {
        super(Type.RED, game);
        name = "Large convoy";
        price = 36;
        tags.add(Tag.SPACE);
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        victory_points = 2;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.getInstance().addUiEvent(new PromptEvent("Please draw 2 cards"));
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Choose which resource to recieve: ");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Plants (x5)");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Animal (x4)");
        context.startActivity(intent);
    }

    @Override
    public void playServerConnection(Player player, Integer data) {
        if (GameController.getInstance().getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getName(), player.getName(), data));
        }
        if (data == 1) {
            GameController.getInstance().addUiEvent(new ResourceEvent(ResourceCard.ResourceType.ANIMAL, player, 4, true));
        }
        playWithMetadata(player, data);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.changePlants(5);
        }
        player.changeHandSize(2);
        super.playWithMetadata(player, data);
    }
}
