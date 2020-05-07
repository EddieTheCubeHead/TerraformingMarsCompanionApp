package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.IntegerDialogActivity;

public final class StandardSellPatents extends StandardProject {
    public StandardSellPatents(Game game) {
        super(game);
        name = "Standard project: Sell patents";
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, IntegerDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(IntegerDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(IntegerDialogActivity.INTEGER_MIN, 0);
        intent.putExtra(IntegerDialogActivity.INTEGER_MAX, player.getHandSize());
        intent.putExtra(IntegerDialogActivity.TITLE, "Amount:");
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(data);
        player.changeHandSize(-data);
    }
}
