package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class UnitedNationsMarsInitiative extends Card implements ActionCard {
    public UnitedNationsMarsInitiative(Game game) {
        super("corporation");
        name = "UNMI";
        tags.add("earth");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEarthTag();
        player.changeMoney(40);
        player.setCorporation(this);
        owner_player = player;
    }

    @Override
    public boolean cardAction() {
        if (action_used | !owner_player.getRaisedTrThisGeneration() | owner_player.getMoney() < 3) {
            return false;
        } else {
            owner_player.changeMoney(-3);
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
