package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class BusinessNetwork extends Card implements ActionCard {
    public BusinessNetwork(Game game) {
        super(Type.BLUE, game);
        name = "Business network";
        price = 4;
        tags.add(Tag.EARTH);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(-1);
    }

    @Override
    public void cardAction() {

    }

    @Override
    public void actionWithMetadata(Integer data) {
        GameController.getInstance().addUiEvent(new PromptEvent("Please draw a card"));
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used);
    }

    @Override
    public void setActionToUsed() {

    }

    @Override
    public Boolean getActionUsed() {
        return null;
    }
}
