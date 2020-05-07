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

public final class AquiferPumping extends Card implements ActionCard {
    public AquiferPumping(Game game) {
        super(Type.BLUE, game);
        name = "Aquifer pumping";
        price = 18;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void cardAction() {
        GameController.getInstance().addUiEvent(new GhostCardCostEvent(owner_game.getDeck().get("Aquifer pumping ghost")));
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.getInstance().useAction();
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {}

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
