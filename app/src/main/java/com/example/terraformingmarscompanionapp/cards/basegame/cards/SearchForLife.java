package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SearchForLife extends ResourceCard implements ActionCard {
    public SearchForLife(Game game) {
        super(Type.BLUE, game);
        name = "Search for life";
        price = 3;
        tags.add(Tag.SCIENCE);
        requirements.setMaxOxygen(6);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        if (owner_player.getMoney() < 1) {
            return;
        }
        //TODO boolean valinta UI
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeMoney(-1);
        if (data >= 0) {
            resource_amount++;
        }
        GameController.getInstance().useAction();
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public void onGameEnd() {
        if (resource_amount > 0) {
            owner_player.changeVictoryPoints(3);
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used || (owner_player.getMoney() < 1));
    }
}
