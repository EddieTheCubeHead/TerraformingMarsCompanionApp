package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class WaterImportFromEurope extends Card implements MetadataAction {
    public WaterImportFromEurope(Game game) {
        super(Type.BLUE);
        name = "Water import from europe";
        price = 25;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            //TODO implementoi ota 12 rahana/titaanina
            action_used = true;
            while (true) {
                if (owner_game.tile_handler.placeOcean(owner_player)) {
                    break;
                } else {
                    //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
                }
            }
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
