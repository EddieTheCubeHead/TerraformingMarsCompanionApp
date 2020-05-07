package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class Ironworks extends Card implements ActionCard {
    public Ironworks(Game game) {
        super(Type.BLUE, game);
        name = "Ironworks";
        price = 11;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        GameController.getInstance().useAction();
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeEnergy(-4);
        owner_game.raiseOxygen(owner_player);
        owner_player.changeSteel(1);
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
        return (action_used || (owner_player.getEnergy() < 4));
    }
}
