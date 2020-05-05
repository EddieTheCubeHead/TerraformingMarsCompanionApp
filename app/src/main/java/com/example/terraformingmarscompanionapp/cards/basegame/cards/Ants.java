package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;

public final class Ants extends ResourceCard implements MetadataAction {
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
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            GameController.getInstance().addUiEvent(new ResourceEvent(ResourceType.MICROBE, owner_game, -1));
            resource_amount++;
            return 0;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        resource_amount++;
        return true;
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
