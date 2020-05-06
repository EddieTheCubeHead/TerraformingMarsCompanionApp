package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class DevelopmentCenter extends Card implements ActionCard {
    public DevelopmentCenter(Game game) {
        super(Type.BLUE, game);
        name = "Development center";
        price = 11;
        tags.add(Tag.BUILDING);
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void cardAction() {
        ((InGameUI) GameController.getInstance().getContext()).cardDrawPrompt(1);
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeEnergy(-1);
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
        return (action_used || (owner_player.getEnergy() < 1));
    }

}
