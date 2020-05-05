package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Predators extends ResourceCard implements MetadataAction {
    public Predators(Game game) {
        super(Type.BLUE, game);
        name = "Predators";
        price = 14;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(11);
        resource_type = ResourceType.ANIMAL;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            //TODO poista toiselta eläin
            resource_amount++;
            return 0;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        resource_amount++;
        return true;
    }
}
