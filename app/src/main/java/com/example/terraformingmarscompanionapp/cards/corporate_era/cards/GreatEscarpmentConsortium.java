package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.PlayerChoiceActivity;

public final class GreatEscarpmentConsortium extends Card {
    public GreatEscarpmentConsortium(Game game) {
        super(Type.GREEN, game);
        name = "Great escarpment consortium";
        price = 6;
        requirements.setMinSteelProduction(1);
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, PlayerChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(PlayerChoiceActivity.CARD_INTENT, this.getName());
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takeSteelProduction(1);
        }
        player.changeSteelProduction(1);
        super.playWithMetadata(player, data);
    }
}
