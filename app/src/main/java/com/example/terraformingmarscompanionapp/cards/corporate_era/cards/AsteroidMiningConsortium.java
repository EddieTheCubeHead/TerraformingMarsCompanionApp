package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

public final class AsteroidMiningConsortium extends Card {
    public AsteroidMiningConsortium(Game game) {
        super(Type.GREEN, game);
        name = "Asteroid mining consortium";
        price = 13;
        tags.add(Tag.JOVIAN);
        requirements.setMinEnergyTags(2);
        victory_points = 1;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, ChoiceDialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(ChoiceDialog.CARD_INTENT, this.getName());
        context.startActivity(intent);
    }

    @Override
    public void playWithMetadata(Player player, Integer data)
    {
        production_box.setStealTitaniumProduction(data);
        production_box.setTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, ChoiceDialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(ChoiceDialog.CARD_INTENT, this.getName());
        intent.putExtra(ChoiceDialog.USE_CASE, ChoiceDialog.PRODUCTION);
        context.startActivity(intent);
    }
}
