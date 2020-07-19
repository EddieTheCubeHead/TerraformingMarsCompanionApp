package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;

public final class MartianRails extends Card implements ActionCard {
    public MartianRails(Game game) {
        super(Type.BLUE, game);
        name = "Martian rails";
        price = 13;
        tags.add(Tag.BUILDING);
    }

    public void cardAction() {
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.getResources().setEnergy(owner_player.getResources().getEnergy() - 1);
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() + owner_game.getCitiesOnMars());
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionValidity() {
        return (action_used || (owner_player.getResources().getEnergy() < 1));
    }
}
