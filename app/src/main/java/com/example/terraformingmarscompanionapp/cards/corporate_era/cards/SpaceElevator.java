package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SpaceElevator extends Card implements ActionCard {
    public SpaceElevator(Game game) {
        super(Type.BLUE, game);
        name = "Space elevator";
        price = 27;
        tags.add(Tag.SPACE);
        tags.add(Tag.BUILDING);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setTitaniumProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeSteel(-1);
        owner_player.changeMoney(5);
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
        return (action_used || (owner_player.getSteel() < 1));
    }
}
