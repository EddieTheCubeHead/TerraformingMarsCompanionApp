package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SpaceElevator extends Card implements ActionCard {
    public SpaceElevator(Game game) {
        super(Type.BLUE);
        name = "Space elevator";
        price = 27;
        tags.add(Tag.SPACE);
        tags.add(Tag.BUILDING);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public Integer cardAction() {
        if (action_used | owner_player.getSteel() < 1) {
            return -1;
        }
        else {
            owner_player.changeSteel(-1);
            owner_player.changeMoney(5);
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
}
