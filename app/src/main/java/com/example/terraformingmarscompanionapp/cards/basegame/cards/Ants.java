package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

public final class Ants extends ResourceCard implements ActionCard {
    public Ants(Game game) {
        super(Type.BLUE, game);
        name = "Ants";
        price = 9;
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(4);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        GameController.getInstance().addUiEvent(new ResourceEvent(ResourceType.MICROBE, owner_player, -1));
        GameController.getInstance().useAction();
        actionServerHook(owner_player);
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public void actionWithMetadata(Integer data) {
        resource_amount++;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/2);
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }

    @Override
    public void setActionToUsed() {action_used = true;}
}
