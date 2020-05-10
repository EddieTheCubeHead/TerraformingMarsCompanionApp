package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;
import com.example.terraformingmarscompanionapp.ui.main.PlayerChoiceActivity;

public final class ArtificialPhotosynthesis extends Card {
    public ArtificialPhotosynthesis(Game game) {
        super(Type.GREEN, game);
        name = "Artificial photosynthesis";
        price = 12;
        tags.add(Tag.SCIENCE);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Choose which production to raise:");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Plants (x1)");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Energy (x2)");
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            production_box.setPlantsProduction(1);
        } else {
            production_box.setEnergyProduction(2);
        }
        super.playWithMetadata(player, data);
    }
}
