package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;

public final class EquatorialMagnetizer extends Card implements ActionCard {
    public EquatorialMagnetizer(Game game) {
        super("blue");
        name = "Equatorial magnetizer";
        price = 11;
        tags.add("building");
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used | owner_player.getEnergyProduction() < 1) {
            return false;
        } else {
            owner_player.changeEnergyProduction(-1);
            owner_player.changeTerraformingRating(1);
            action_used = true;
            return true;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
