package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

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
        defaultEvents(owner_player);
        EventScheduler.playNextEvent(GameController.getContext());
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
}
