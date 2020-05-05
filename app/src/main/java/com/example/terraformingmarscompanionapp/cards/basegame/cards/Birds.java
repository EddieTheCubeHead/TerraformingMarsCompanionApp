package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.main.PlayerChoiceActivity;

public final class Birds extends ResourceCard implements ActionCard {
    public Birds(Game game) {
        super(Type.BLUE, game);
        name = "Birds";
        price = 10;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(13);
        resource_type = ResourceType.ANIMAL;
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
            GameController.getInstance().getPlayer(data).takePlantsProduction(2);
        }
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            resource_amount++;
            return 0;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
