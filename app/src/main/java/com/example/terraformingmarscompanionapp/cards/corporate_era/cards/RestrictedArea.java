package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class RestrictedArea extends Card implements ActionCard {
    public RestrictedArea(Game game) {
        super(Type.BLUE);
        name = "Restricted area";
        price = 11;
        tags.add(Tag.SCIENCE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.RESTRICTED_AREA, owner_game));
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.playWithMetadata(player, data);
    }

    @Override
    public Integer cardAction() {
        if (action_used | owner_player.getMoney() < 2) {
            return -1;
        } else {
            //Todo UI-prompt ota kortti
            owner_player.changeMoney(-2);
            owner_player.changeHandSize(1);
            action_used = true;
            return 0;
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
