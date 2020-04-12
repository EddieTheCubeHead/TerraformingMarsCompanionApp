package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class MartianRails extends Card implements ActionCard {
    public MartianRails(Game game) {
        super("blue");
        name = "Martian rails";
        price = 13;
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        super.onPlay(player);
    }
    public boolean cardAction() {
        if (!owner_player.changeEnergy(-1) | action_used) {
            return false;
        } else {
            owner_player.changeMoney(owner_game.getCitiesOnMars());
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
