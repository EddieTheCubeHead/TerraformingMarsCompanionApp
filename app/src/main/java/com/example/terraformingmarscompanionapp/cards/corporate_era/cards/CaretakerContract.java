package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class CaretakerContract extends Card implements ActionCard {
    public CaretakerContract(Game game) {
        super(Type.BLUE, game);
        name = "Caretaker contract";
        price = 3;
        requirements.setMinTemperature(0);
        owner_game = game;
    }

    @Override
    public void cardAction() {
            owner_player.changeTerraformingRating(1);
            owner_player.changeHeat(-8);
            action_used = true;
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeHeat(-8);
        owner_player.changeTerraformingRating(1);
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return null;
    }

    @Override
    public void setActionToUsed() {

    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
