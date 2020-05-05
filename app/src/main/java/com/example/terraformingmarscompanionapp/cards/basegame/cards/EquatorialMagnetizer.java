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
    public Integer cardAction() {
        if (action_used | owner_player.getEnergyProduction() < 1) {
            return -1;
        } else {
            owner_player.changeEnergyProduction(-1);
            owner_player.changeTerraformingRating(1);
            action_used = true;
            return 0;
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
