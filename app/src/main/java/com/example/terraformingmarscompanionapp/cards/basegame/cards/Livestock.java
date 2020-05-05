package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Livestock extends ResourceCard implements ActionCard {
    public Livestock(Game game) {
        super(Type.BLUE, game);
        name = "Livestock";
        price = 13;
        tags.add(Tag.ANIMAL);
        requirements.setMinOxygen(9);
        requirements.setMinPlantProduction(1);
        resource_type = ResourceType.ANIMAL;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlantsProduction(-1);
        player.changeMoneyProduction(2);
        super.playWithMetadata(player, data);
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            resource_amount++;
            action_used = true;
            return 0;
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

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }
}
