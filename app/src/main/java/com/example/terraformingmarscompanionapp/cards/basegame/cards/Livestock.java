package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Livestock extends ResourceCard implements ActionCard {
    public Livestock(Game game) {
        super(Type.BLUE, game);
        name = "Livestock";
        price = 13;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(9);
        requirements.setMinPlantProduction(1);
        resource_type = ResourceType.ANIMAL;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(-1);
        production_box.setMoneyProduction(2);
        super.playWithMetadata(player, data);
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
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }
}
