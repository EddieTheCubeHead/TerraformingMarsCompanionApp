package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

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
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.addEvent(new PromptEvent("Please draw a card"));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.getResources().setEnergy(owner_player.getResources().getEnergy() - 1);
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
        return (action_used || (owner_player.getResources().getEnergy() < 1));
    }

}
