package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.playDialogues.IntegerDialog;

public final class Insulation extends Card {
    public Insulation(Game game) {
        super(Type.GREEN, game);
        name = "Insulation";
        price = 2;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getContext();
        Intent intent = new Intent(context, IntegerDialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(IntegerDialog.CARD_NAME, this.getName());
        intent.putExtra(IntegerDialog.INTEGER_MIN, 0);
        intent.putExtra(IntegerDialog.INTEGER_MAX, player.getHeatProduction());
        intent.putExtra(IntegerDialog.TITLE, "Amount:");
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(-data);
        production_box.setMoneyProduction(data);
        super.playWithMetadata(player, data);
    }
}
