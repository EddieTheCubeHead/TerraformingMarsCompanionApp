package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class UnitedNationsMarsInitiative extends Card implements ActionCard {
    public UnitedNationsMarsInitiative(Game game) {
        super(Type.CORPORATION);
        name = "UNMI";
        tags.add(Tag.EARTH);
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

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
