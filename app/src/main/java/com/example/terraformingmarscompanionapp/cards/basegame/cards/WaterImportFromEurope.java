package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.CostEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

import java.util.ArrayList;
import java.util.Collections;

public final class WaterImportFromEurope extends Card implements MetadataAction {
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
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            if ((owner_player.getMoney() + owner_player.getTitanium() * (3 + owner_player.getTitaniumValueModifier()) < 8)) {
                return -2;
            }
            GameController.getInstance().addUiEvent(new CostEvent(new ArrayList<>(Collections.singletonList(Tag.SPACE)), 12));
            GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
            action_used = true;
            return 0;
        }
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
    public Boolean getActionUsed() {
        return action_used;
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        return true;
    }
}
