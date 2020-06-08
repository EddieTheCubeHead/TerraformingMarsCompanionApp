package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class IndustrialCenter extends Card implements ActionCard {
    public IndustrialCenter(Game game) {
        super(Type.BLUE, game);
        name = "Industrial center";
        price = 4;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new TileEvent(Placeable.INDUSTRIAL_CENTER, owner_game));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeMoney(-7);
        owner_player.changeSteelProduction(1);
        EventScheduler.playNextEvent(GameController.getContext());
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
