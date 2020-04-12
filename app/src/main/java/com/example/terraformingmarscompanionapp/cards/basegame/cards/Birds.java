package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Birds extends ResourceCard implements ActionCard {
    public Birds(Game game) {
        super("blue");
        name = "Birds";
        price = 10;
        tags.add("animal");
        requirements.put("min_oxygen", 13);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        //TODO vie toiselta 2 kasvintuotantoa
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            resource_amount++;
            return true;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
