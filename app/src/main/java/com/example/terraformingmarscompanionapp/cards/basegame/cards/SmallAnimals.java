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

public final class SmallAnimals extends ResourceCard implements ActionCard {
    public SmallAnimals(Game game) {
        super(Type.BLUE, game);
        name = "Small animals";
        price = 6;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(6);
        resource_type = ResourceType.ANIMAL;
        wait_for_server = true;
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
        production_box.setStealPlantsProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    @Override
    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        resource_amount++;
        GameController.getInstance().useAction();
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }

    @Override
    public void playProductionBox() {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, PlayerChoiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(PlayerChoiceActivity.CARD_INTENT, this.getName());
        intent.putExtra(PlayerChoiceActivity.SPECIAL_CASE, PlayerChoiceActivity.CASE_PRODCUTION);
        context.startActivity(intent);
    }
}
