package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class EquatorialMagnetizer extends Card implements ActionCard {
    public EquatorialMagnetizer(Game game) {
        super(Type.BLUE, game);
        name = "Equatorial magnetizer";
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
        owner_player.changeEnergyProduction(-1);
        owner_player.changeTerraformingRating(1);
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
        return (action_used || (owner_player.getEnergyProduction() < 1));
    }
}
