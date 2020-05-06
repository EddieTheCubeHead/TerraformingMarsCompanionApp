package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NitriteReducingBacteria extends ResourceCard implements ActionCard {
    public NitriteReducingBacteria(Game game) {
        super(Type.BLUE, game);
        name = "Nitrite reducing bacteria";
        price = 11;
        tags.add(Tag.MICROBE);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        resource_amount += 3;
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        //TODO boolean valinta UI
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else if (resource_amount < 3) {
            resource_amount -= 3;
        } else {
            owner_player.changeTerraformingRating(1);
        }
        GameController.getInstance().useAction();
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }
}
