package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class EquatorialMagnetizer extends Card implements ActionCard {
    public EquatorialMagnetizer(Game game) {
        super(Type.BLUE, game);
        name = "Equatorial magnetizer";
        price = 11;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeEnergyProduction(-1);
        owner_player.changeTerraformingRating(1);
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
        return (action_used || (owner_player.getEnergyProduction() < 1));
    }
}
