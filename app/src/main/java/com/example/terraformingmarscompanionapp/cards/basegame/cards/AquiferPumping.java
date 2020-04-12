package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class AquiferPumping extends Card implements ActionCard {
    public AquiferPumping(Game game) {
        super("blue");
        name = "Aquifer pumping";
        price = 18;
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
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
            return true;
        }
        return false;
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
