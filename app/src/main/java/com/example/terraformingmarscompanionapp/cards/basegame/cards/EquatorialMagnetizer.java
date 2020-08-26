package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;

public final class EquatorialMagnetizer extends Card implements ActionCard {
    public EquatorialMagnetizer() {
        super(Type.BLUE);
        name = "Equatorial magnetizer";
        price = 11;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) throws InvalidResourcesException {
        owner_player.getResources().setEnergyProduction(owner_player.getResources().getEnergyProduction() - 1);
        owner_player.getResources().setTerraformingRating(owner_player.getResources().getTerraformingRating() + 1);
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
        return (action_used || (owner_player.getResources().getEnergyProduction() < 1));
    }
}
