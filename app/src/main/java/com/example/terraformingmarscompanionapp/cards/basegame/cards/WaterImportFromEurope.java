package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.GhostCardCostEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

import java.util.ArrayList;
import java.util.Collections;

public final class WaterImportFromEurope extends Card implements ActionCard {
    public WaterImportFromEurope(Game game) {
        super(Type.BLUE, game);
        name = "Water import from europe";
        price = 25;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        GameController.getInstance().addUiEvent(new GhostCardCostEvent(owner_game.getDeck().get("Water import from europe ghost")));
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.getInstance().useAction();
        actionServerHook(owner_player);
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used || ((owner_player.getMoney() + owner_player.getTitanium() * (3 + owner_player.getTitaniumValueModifier())) < 12));
    }

    @Override
    public void actionWithMetadata(Integer data) {}

    @Override
    public Boolean getActionRequiresWait() {
        return true;
    }
}
