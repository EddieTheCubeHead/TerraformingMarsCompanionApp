package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.CostEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class AquiferPumping extends Card implements MetadataAction {
    public AquiferPumping(Game game) {
        super(Type.BLUE);
        name = "Aquifer pumping";
        price = 18;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else if (owner_player != null) {
            if ((owner_player.getMoney() + owner_player.getSteel() * (2 + owner_player.getSteelValueModifier()) < 8)) {
                return -2;
            }
            GameController.getInstance().addUiEvent(new CostEvent());
            GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
            action_used = true;
            return 0;
        }
        return -1;
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        return true;
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
