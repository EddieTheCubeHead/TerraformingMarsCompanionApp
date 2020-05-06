package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.CostEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

import java.util.ArrayList;
import java.util.Collections;

public final class AquiferPumping extends Card implements ActionCard {
    public AquiferPumping(Game game) {
        super(Type.BLUE, game);
        name = "Aquifer pumping";
        price = 18;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        if (owner_player != null) {
            GameController.getInstance().addUiEvent(new CostEvent(new ArrayList<>(Collections.singletonList(Tag.BUILDING)), 8));
            GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
            actionServerHook(owner_player);
        }
    }

    @Override
    public void actionWithMetadata(Integer data) {
        GameController.getInstance().executeNextEvent();
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used || (owner_player.getMoney() + owner_player.getSteel() * (2 + owner_player.getSteelValueModifier()) < 8));
    }

    @Override
    public void setActionToUsed() {action_used = true;}
}
