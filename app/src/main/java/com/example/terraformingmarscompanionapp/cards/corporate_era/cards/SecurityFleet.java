package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SecurityFleet extends ResourceCard implements ActionCard {
    public SecurityFleet(Game game) {
        super(Type.BLUE);
        name = "Security fleet";
        price = 12;
        tags.add(Tag.SPACE);
        owner_game = game;
        resource_type = ResourceType.UNIQUE;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    @Override
    public void onGameEnd() {
        if (owner_player == null) {
            return;
        }
        owner_player.changeVictoryPoints(resource_amount);
    }

    @Override
    public boolean cardAction() {
        if (action_used | owner_player.getTitanium() < 1) {
            return false;
        } else {
            owner_player.changeTitanium(-1);
            resource_amount++;
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
