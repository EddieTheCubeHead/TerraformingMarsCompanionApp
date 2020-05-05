package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NitriteReducingBacteria extends ResourceCard implements MetadataAction {
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
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            Boolean add_microbe = true;
            //TODO booleanin kysyminen UI:lla
            if (add_microbe) {
                resource_amount++;
            } else if (resource_amount < 3) {
                resource_amount -= 3;
                owner_player.changeTerraformingRating(1);
            } else {
                return -1;
            }
            action_used = true;
            return add_microbe ? 0 : 1;
        }
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        if (action_used) {
            return false;
        } else {
            if (data == 0) {
                resource_amount++;
            } else if (resource_amount < 3) {
                resource_amount -= 3;
            } else {
                return false;
            }
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
