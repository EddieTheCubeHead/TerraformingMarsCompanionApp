package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class IndustrialCenter extends Card implements ActionCard {
    public IndustrialCenter(Game game) {
        super(Type.BLUE, game);
        name = "Industrial center";
        price = 4;
        tags.add(Tag.BUILDING);
        //TODO City tile laudalla ja hexa vierestä vapaana
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Erikois laatan laittaminen
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeMoney(-7);
        owner_player.changeSteelProduction(1);
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return !action_used && owner_player.getMoney() >= 7;
    }

    @Override
    public void setActionToUsed() {

    }
}
