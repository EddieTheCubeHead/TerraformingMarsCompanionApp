package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ElectroCatapult extends Card implements ActionCard {
    public ElectroCatapult(Game game) {
        super(Type.BLUE, game);
        name = "Electro catapult";
        price = 17;
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(8);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    public void cardAction() {
        //TODO boolean valinta UI
    }


    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            owner_player.takePlants(1);
        } else {
            owner_player.takeSteel(1);
        }
        owner_player.changeMoney(7);
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionValidity() {
        return (action_used || (owner_player.getSteel() < 1 && owner_player.getPlants() < 1));
    }
}
