package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class WaterSplittingPlant extends Card implements ActionCard {
    public WaterSplittingPlant(Game game) {
        super(Type.BLUE, game);
        name = "Water splitting plant";
        price = 12;
        tags.add(Tag.BUILDING);
        requirements.setMinOceans(2);
        owner_game = game;
    }

    @Override
    public void cardAction() {
        defaultEvents(owner_player);
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeEnergy(-3);
        owner_game.raiseOxygen(owner_player);
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void setActionToUsed() {

    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used || (owner_player.getEnergy() < 3));
    }
}
