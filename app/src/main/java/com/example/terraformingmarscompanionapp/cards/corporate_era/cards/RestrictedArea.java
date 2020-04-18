package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;

import java.util.ArrayList;

public final class RestrictedArea extends Card implements ActionCard {
    public RestrictedArea(Game game) {
        super(Type.BLUE);
        name = "Restricted area";
        price = 11;
        tags.add(Tag.SCIENCE);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeRestrictedArea(player)) {
                break;
            } else {
                //TODO UI feedback ja prompti haluaako keskeyttää asettamisen
            }
        }
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data, ArrayList<TileEventPacket> tile_events, ArrayList<ResourceEventPacket> resource_events) {
        super.playWithMetadata(player, data, tile_events, resource_events);
    }

    @Override
    public boolean cardAction() {
        if (action_used | owner_player.getMoney() < 2) {
            return false;
        } else {
            //Todo UI-prompt ota kortti
            owner_player.changeMoney(-2);
            owner_player.changeHandSize(1);
            action_used = true;
            return true;
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
