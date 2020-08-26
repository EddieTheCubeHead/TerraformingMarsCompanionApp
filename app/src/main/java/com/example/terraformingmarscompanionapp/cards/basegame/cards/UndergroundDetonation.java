package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;

public final class UndergroundDetonation extends Card implements ActionCard {
    public UndergroundDetonation() {
        super(Type.BLUE);
        name = "Underground detonation";
        price = 6;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() - 10);
        owner_player.getResources().setHeatProduction(owner_player.getResources().getHeatProduction() + 2);
        EventScheduler.playNextEvent(GameController.getContext());
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
        return (action_used || (owner_player.getResources().getMoney() < 10));
    }
}
