package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ElectroCatapult extends Card implements ActionCard {
    public ElectroCatapult(Game game) {
        super(Type.BLUE);
        name = "Electro catapult";
        price = 17;
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(8);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(-1);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    public Integer cardAction() {
        if (action_used) {
            return -1;
        }
        else {
            //TODO Valitse kasvi/teräs
            owner_player.changeMoney(7);
            action_used = true;
            return 0;
        }
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
