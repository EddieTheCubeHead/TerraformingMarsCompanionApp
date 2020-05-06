package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class MartianRails extends Card implements ActionCard {
    public MartianRails(Game game) {
        super(Type.BLUE, game);
        name = "Martian rails";
        price = 13;
        tags.add(Tag.BUILDING);
    }

    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeEnergy(-1);
        owner_player.changeMoney(owner_game.getCitiesOnMars());
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionValidity() {
        return (action_used || (owner_player.getEnergy() < 1));
    }
}
