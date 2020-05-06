package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class UndergroundDetonation extends Card implements ActionCard {
    public UndergroundDetonation(Game game) {
        super(Type.BLUE, game);
        name = "Underground detonation";
        price = 6;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeMoney(-10);
        owner_player.changeHeatProduction(2);
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
        return (action_used || (owner_player.getMoney() < 10));
    }
}
