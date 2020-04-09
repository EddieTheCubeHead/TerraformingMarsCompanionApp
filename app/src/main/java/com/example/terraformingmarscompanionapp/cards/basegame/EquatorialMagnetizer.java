package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EquatorialMagnetizer extends Card implements ActionCard {
    public EquatorialMagnetizer(Game game) {
        super("blue");
        name = "Equatorial magnetizer";
        price = 11;
        tags.add("building");
        owner_game = game;
    }

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

    public String getActionName() {
        return getName();
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
