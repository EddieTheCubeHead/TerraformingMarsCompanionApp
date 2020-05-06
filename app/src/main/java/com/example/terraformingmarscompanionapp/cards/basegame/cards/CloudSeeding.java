package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.PlayerChoiceActivity;

public final class CloudSeeding extends Card {
    public CloudSeeding(Game game) {
        super(Type.GREEN, game);
        name = "Cloud seeding";
        price = 11;
        requirements.setMinOceans(3);
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
        production_box.setStealHeatProduction(1);
        production_box.setMoneyProduction(-1);
        production_box.setPlantsProduction(2);
        super.playWithMetadata(player, data);
    }
}
