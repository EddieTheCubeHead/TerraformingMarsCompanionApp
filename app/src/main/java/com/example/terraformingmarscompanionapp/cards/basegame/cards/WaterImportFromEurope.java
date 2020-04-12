package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;

public final class WaterImportFromEurope extends Card implements ActionCard {
    public WaterImportFromEurope(Game game) {
        super("blue");
        name = "Water import from europe";
        price = 25;
        tags.add("space");
        tags.add("jovian");
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            //TODO implementoi ota 12 rahana/titaanina
            action_used = true;
            while (true) {
                if (owner_game.tile_handler.placeOcean(owner_player)) {
                    break;
                } else {
                    //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
                }
            }
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
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
