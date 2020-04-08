package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Ants extends ResourceCard implements ActionCard {
    public Ants(Game game) {
        super("blue");
        name = "Ants";
        price = 9;
        tags.add("microbe");
        requirements.put("min_oxygen", 4);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        super.onPlay(player);
        owner_game.update_manager.onVpCardPlayed(player);
    }

    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            //TODO poista resurssi toiselta kortilta
            resource_amount++;
            return true;
        }
    }

    public String getActionName() {
        return getName();
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
