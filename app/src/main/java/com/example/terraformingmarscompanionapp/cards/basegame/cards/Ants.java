package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Ants extends ResourceCard implements ActionCard {
    public Ants(Game game) {
        super("blue");
        name = "Ants";
        price = 9;
        tags.add("microbe");
        requirements.setMinOxygen(4);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        super.onPlay(player);
        owner_game.update_manager.onVpCardPlayed(player);
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            //TODO poista resurssi toiselta kortilta
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
        owner_player.changeVictoryPoints(resource_amount/2);
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
