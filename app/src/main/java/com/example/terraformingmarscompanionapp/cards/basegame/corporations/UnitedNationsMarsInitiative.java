package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class UnitedNationsMarsInitiative extends Card implements ActionCard {
    public UnitedNationsMarsInitiative(Game game) {
        super(Type.CORPORATION, game);
        name = "UNMI";
        tags.add(Tag.EARTH);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(40);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        if (!owner_player.getRaisedTrThisGeneration() || owner_player.getMoney() < 3) {
            return;
        }
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeMoney(-3);
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
        return action_used;
    }
}
