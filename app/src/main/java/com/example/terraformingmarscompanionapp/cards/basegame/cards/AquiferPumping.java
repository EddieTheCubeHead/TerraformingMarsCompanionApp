package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class AquiferPumping extends Card implements ActionCard {
    public AquiferPumping(Game game) {
        super(Type.BLUE);
        name = "Aquifer pumping";
        price = 18;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else if (owner_player != null) {
            //TODO implementoi tähän vie 8 rahana tai teräksenä
            while (true) {
                if (owner_game.tile_handler.placeOcean(owner_player)) {
                    break;
                } else {
                    //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
                }
            }
            action_used = true;
            return 0;
        }
        return -1;
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
