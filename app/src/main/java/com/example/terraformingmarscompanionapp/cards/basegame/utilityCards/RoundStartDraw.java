package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.IntegerDialogActivity;

public final class RoundStartDraw extends Card {
    public RoundStartDraw(Game game) {
        super(Type.OTHER, game);
        name = "Round start draw";
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, IntegerDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(IntegerDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(IntegerDialogActivity.INTEGER_MIN, 0);
        intent.putExtra(IntegerDialogActivity.INTEGER_MAX, GameController.getInstance().getGeneration() == 1 ? 10 : 4);
        intent.putExtra(IntegerDialogActivity.TITLE, player.getName() + " cards:");
        System.out.println("Calling intent");
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHandSize(data);
        player.takeMoney(data * 3);
        player.setDrewCardsThisGen(true);
        super.playWithMetadata(player, data);
    }
}
