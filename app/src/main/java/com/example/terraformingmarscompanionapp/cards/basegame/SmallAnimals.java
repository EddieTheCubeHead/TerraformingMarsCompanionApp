package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SmallAnimals extends ResourceCard implements ActionCard {
    public SmallAnimals(Game game) {
        super("blue");
        name = "Small animals";
        price = 6;
        tags.add("animal");
        requirements.put("min_oxygen", 6);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        //TODO toiselta pelaajalta kasvien poistaminen
        owner_player = player;
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            resource_amount++;
            action_used = true;
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
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
