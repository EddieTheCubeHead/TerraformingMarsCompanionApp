package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.PlayerChoiceActivity;

public final class DeimosDown extends Card {
    public DeimosDown(Game game) {
        super(Type.RED, game);
        name = "Deimos down";
        price = 31;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getContext();
        Intent intent = new Intent(context, PlayerChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(PlayerChoiceActivity.CARD_INTENT, this.getName());
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getPlayer(data).takePlants(8);
        }
        player.changeSteel(4);
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
