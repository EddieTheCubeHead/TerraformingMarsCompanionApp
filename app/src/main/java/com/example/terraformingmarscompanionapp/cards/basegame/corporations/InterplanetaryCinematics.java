package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.EffectCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class InterplanetaryCinematics extends Card implements EffectCard {
    public InterplanetaryCinematics() {
        super(Type.CORPORATION);
        name = "Interplanetary cinematics";
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setSteel(20);
        player.getResources().setMoney(30);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardEffect(Player player) throws InvalidResourcesException {
        if (owner_player == null | owner_player != player) {
            return;
        }
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() + 2);
    }
}
